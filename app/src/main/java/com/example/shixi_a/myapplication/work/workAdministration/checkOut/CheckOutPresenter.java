package com.example.shixi_a.myapplication.work.workAdministration.checkOut;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.bean.Egress;
import com.example.shixi_a.myapplication.model.assist.AssistRepository;
import com.example.shixi_a.myapplication.model.egress.EgressRepository;
import com.example.shixi_a.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by a5376 on 2017/7/14.
 */

public class CheckOutPresenter implements CheckOutContract.Presenter {
    private EgressRepository mRepository;
    private CheckOutContract.View mCheckOutView;
    private Context context;
    private String outId;
    private String tabname;

    private LocationManager locationManager;
    private String provider;

    private String longitude = "0";
    private String latitude = "0";
    private String altitude = "0";

    private String address;


    public CheckOutPresenter( String id,String name, EgressRepository repository, CheckOutFragment checkOutFragment, Context context) {
        outId = id;
        tabname = name;
        mRepository = repository;
        mCheckOutView = checkOutFragment;
        mCheckOutView.setPresenter(this);
        this.context = context;
    }

    @Override
    public void start() {
//        loadEgress();
        loadLocation();
    }

    private void loadLocation() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //获取当前可用的位置控制器
        List<String> list = locationManager.getProviders(true);

        if (list.contains(LocationManager.GPS_PROVIDER)) {
            //是否为GPS位置控制器
            provider = LocationManager.GPS_PROVIDER;
        } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
            //是否为网络位置控制器
            provider = LocationManager.NETWORK_PROVIDER;

        } else {
            mCheckOutView.showToast("请检查网络连接并开启定位权限");

//            ToastUtils.showShort(context, "请检查网路连接并开启定位权限");
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            showLocation(location);
        }
        locationManager.requestLocationUpdates(provider, 3000, 50, locationListener);
    }


    private void showLocation(Location location) {
        longitude = location.getLongitude()+"";
        latitude = location.getLatitude()+"";
        altitude = location.getAltitude()+"";//必要时替换成String.valueOf()

        AssistRepository repository = new AssistRepository();
        repository.getLocation(context,latitude,longitude, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                response = response.replace("renderReverse&&renderReverse","");
                response = response.replace("(","");
                response = response.replace(")","");
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    JSONObject rt = jsonObject.getJSONObject("result");
                    address = rt.getString("formatted_address");
                    mCheckOutView.setAddress(address);
                } catch (JSONException e) {
                    ToastUtils.showShort(context,"获取地理位置失败");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"获取地理位置失败");
            }
        });
    }


    LocationListener locationListener =  new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {
            //如果位置发生变化,重新显示
            showLocation(location);
        }
    };


    void onDestroy() {
        if(locationManager!=null){
            //移除监听器
            locationManager.removeUpdates(locationListener);
        }
    }

    //目前没啥用
    private void loadEgress() {
        mRepository.getEgressDetail(context,outId, new GsonResponseHandler<Egress>() {
            @Override
            public void onSuccess(int statusCode, Egress response) {
                Egress egress = response;
                process(egress);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"初始化失败");
            }
        });
    }
    //这个也没有
    private void process(Egress egress) {
        mCheckOutView.initView(egress.getOut_time(),egress.getAddr());
    }

    @Override
    public void checkOut(String memo) {
        if(address == null)
            address = "";
        mRepository.checkOut(context,memo, longitude,latitude,altitude,address, tabname,outId,new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                if(response.getBoolean("rt")){
                    ToastUtils.showShort(context,"提交成功");
                    mCheckOutView.showMessage();
                }else{
                    ToastUtils.showShort(context,response.getString("error"));
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"提交失败");
            }
        });
    }
}
