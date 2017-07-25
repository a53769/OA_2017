package com.example.shixi_a.myapplication.clock;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.bean.Attendance;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.model.attendance.AttendanceRepository;
import com.example.shixi_a.myapplication.util.LogUtils;
import com.example.shixi_a.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by a5376 on 2017/7/5.
 */

public class ClockPresenter implements ClockContract.Presenter {

    private ClockContract.View mClockView;
    private AttendanceRepository mRepository;
    private Context context;

    private String startStr;
    private String endStr;

    private LocationManager locationManager;
    private String provider;

    private double longitude = 0;
    private double latitude = 0;
    private double altitude = 0;

    private static boolean Flag = false;


    public ClockPresenter(AttendanceRepository mAttendanceRepository, ClockFragment clockFragment, Context context) {

        mClockView = checkNotNull(clockFragment, "view cannot be null");

        mClockView.setPresenter(this);

        mRepository = checkNotNull(mAttendanceRepository, "repository cannot be null");

        this.context = context;
    }

    @Override
    public void start() {
        loadLocation();
        loadAttendance();
        mClockView.setLoadingIndicator(false);
    }

    private void loadLocation() {
        mRepository.getLocation(context, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                LogUtils.v("获取成功");
                String location = response.getString("local");
                mClockView.showWIFI(location);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }

    private void loadAttendance() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        String sort;
        startStr = str + " 00:00:00";
        endStr = str + " 23:59:59";
        sort = "2";
        mRepository.getAttendances(context, sort, startStr, endStr, new GsonResponseHandler<RowsNoPage<Attendance>>() {

            @Override
            public void onSuccess(int statusCode, RowsNoPage<Attendance> response) {
                List<Attendance> attendances = response.rows;

                if (attendances.size() == 0) {
                    Flag = false;
                } else {
                    Attendance attendance = attendances.get(0);
                    String time = attendance.getIn_time();
                    mClockView.hideClockOut(time);
                    Flag = true;
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogUtils.v("获取失败");
            }

        });//下班
        startStr = str + " 00:00:00";
        endStr = str + " 23:59:59";
        sort = "1";
        mRepository.getAttendances(context, sort,startStr, endStr, new GsonResponseHandler<RowsNoPage<Attendance>>() {
            @Override
            public void onSuccess(int statusCode, RowsNoPage<Attendance> response) {
                List<Attendance> attendances = response.rows;

                if (attendances.size() == 0) {
                    if(Flag){
                        mClockView.hideClockInWithUnCheck();
                    }
                } else {
                    Attendance attendance = attendances.get(0);
                    String time = attendance.getIn_time();
                    mClockView.hideClockIn(time);

                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogUtils.v("获取失败");
            }
        });//上班

    }

    @Override
    public void checkOn(String s) {
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
            ToastUtils.showShort(context, "请检查网路连接或开启网络定位权限");
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
            altitude = location.getAltitude();
        }

        mRepository.AddAttendance(context,s,String.valueOf(longitude),String.valueOf(latitude),String.valueOf(altitude), new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                ToastUtils.showShort(context,"打卡成功");
                start();
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,error_msg);
            }
        });
    }
}

