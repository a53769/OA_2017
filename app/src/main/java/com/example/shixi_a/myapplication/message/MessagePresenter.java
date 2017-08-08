package com.example.shixi_a.myapplication.message;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.bean.Attendance;
import com.example.shixi_a.myapplication.bean.Message;
import com.example.shixi_a.myapplication.bean.Reimbursement;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.model.assist.AssistRepository;
import com.example.shixi_a.myapplication.model.attendance.AttendanceRepository;
import com.example.shixi_a.myapplication.model.message.MessageRepository;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.util.LogUtils;
import com.example.shixi_a.myapplication.util.ToastUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by a5376 on 2017/7/11.
 */

public class MessagePresenter implements MessageContract.Presenter {

    private MessageContract.View mMessageView;
    private MessageRepository messageRepository;
    private Context context;



    @Override
    public void result(int requestCode, int resultCode, Intent data) {
        if (requestCode == Fragment_message.REQUEST_CODE) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    sendQRcode(result);
//                    ToastUtils.showShort(context,result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtils.showShort(context,"解析失败");
                }
            }
        }
    }




    public MessagePresenter(MessageRepository mRepository, Fragment_message fragment_message, Context context) {

        mMessageView = fragment_message;
        fragment_message.setPresenter(this);
        messageRepository = mRepository;
        this.context = context;
    }

    @Override
    public void start() {
        loadMessage();
        mMessageView.setLoadingIndicator(false);
    }

    private void loadMessage() {
        messageRepository.getMessage(context, new GsonResponseHandler<RowsNoPage<Message>>() {
            @Override
            public void onSuccess(int statusCode, RowsNoPage<Message> response) {
                List<Message> messages;
                messages = response.rows;
                processMessage(messages);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                if (error_msg.equals("fail status=401")){
                    mMessageView.showLogin();
                }else if(error_msg.length()>10){
                    ToastUtils.showShort(context, "请检查网络连接");
                }else {
                    ToastUtils.showShort(context, "加载消息列表失败");
                }
            }
        });
    }



    private void processMessage(List<Message> messages) {
        if(messages.isEmpty()){
            ToastUtils.showShort(context,"列表为空");
        }
        mMessageView.showMessage(messages);
    }

    @Override
    public void getReimburseDetail(String id) {
        final ReimbursementRepository repository = new ReimbursementRepository();
        repository.getReimburse(context, id, new GsonResponseHandler<Reimbursement>() {
            @Override
            public void onSuccess(int statusCode, Reimbursement response) {
                if(response.isRt()) {
                    mMessageView.showReimburseDetail(response.getId(), response.getType());
                }else{
                    ToastUtils.showShort(context,response.getError());
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }

    private void sendQRcode(String result) {
        AssistRepository repository = new AssistRepository();
        repository.sendQRCode(context,result, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                if(response.getInt("rt") == 1) {
                    ToastUtils.showShort(context,"扫描成功");
                    checkOnWork();//检查上班打卡

                }else{
                    ToastUtils.showShort(context,response.getString("error"));
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }

    private void checkOnWork() {
        AttendanceRepository repository = new AttendanceRepository();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        String sort;

        String startStr = str + " 00:00:00";
        String endStr = str + " 23:59:59";
        sort = "1";
        repository.getAttendances(context, sort,startStr, endStr, new GsonResponseHandler<RowsNoPage<Attendance>>() {
            @Override
            public void onSuccess(int statusCode, RowsNoPage<Attendance> response) {
                List<Attendance> attendances = response.rows;

                if (attendances.size() == 0) {
                    //没打卡自动打
                    CheckOn();
                } else {
                    //打过了
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogUtils.v("获取失败");
            }
        });//上班
    }

    private void CheckOn() {

        LocationManager locationManager;
        String provider;

        double longitude = 0;
        double latitude = 0;
        double altitude = 0;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //获取当前可用的位置控制器
        List<String> list = locationManager.getProviders(true);

        if (list.contains(LocationManager.GPS_PROVIDER)) {
            //是否为GPS位置控制器
            provider = LocationManager.GPS_PROVIDER;
        } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
            //是否为网络位置控制器
            provider = LocationManager.NETWORK_PROVIDER;

        }  else {
            ToastUtils.showShort(context, "没有可用的位置提供器");
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {

            longitude = location.getLongitude();
            latitude = location.getLatitude();
            altitude = location.getAltitude();
        }

        AttendanceRepository repository = new AttendanceRepository();

        repository.AddAttendance(context,"1",String.valueOf(longitude),String.valueOf(latitude),String.valueOf(altitude), new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                ToastUtils.showShort(context,"打卡成功");
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                if(error_msg.length()>10){
                    ToastUtils.showShort(context, "请连接网络");
                }else {
                    ToastUtils.showShort(context, error_msg);
                }
            }
        });
    }

}
