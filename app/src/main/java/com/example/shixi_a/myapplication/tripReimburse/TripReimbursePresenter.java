package com.example.shixi_a.myapplication.tripReimburse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.GlobalApp;
import com.example.shixi_a.myapplication.linkMan.LinkManActivity;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.trafficTool.TrafficToolActivity;
import com.example.shixi_a.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by a5376 on 2017/7/28.
 */

public class TripReimbursePresenter implements TripReimburseContract.Presenter{

    private SharedPreferences sp;

    private ReimbursementRepository mRepository;
    private TripReimburseFragment mTripView;
    private Context context;

    private String realId;

    private String toolId;


    @Override
    public void result(int requestCode, int resultCode, Intent data) {
        if(TrafficToolActivity.REQUEST_TOOL_CODE == requestCode && Activity.RESULT_OK == resultCode){
            toolId = data.getStringExtra("id");
            String name = data.getStringExtra("value");
            mTripView.setTraffic(name);
        }
        if(LinkManActivity.REQUEST_USERS_CODE == requestCode && Activity.RESULT_OK == resultCode){
            realId = data.getStringExtra("id");
            String name = data.getStringExtra("name");
            mTripView.setRealName(name);
        }
    }

    public TripReimbursePresenter( ReimbursementRepository repository, TripReimburseFragment tripReimburseFragment, Context context) {
        mRepository = repository;
        mTripView = tripReimburseFragment;
        mTripView.setPresenter(this);

        this.context = context;

    }

    @Override
    public void start() {
        String name = GlobalApp.getInstance().getUserName();
        mTripView.setName(name);
    }

    @Override
    public void submitTripReimburse(String startAddress, String endAddress, String trafficCost, String outTrafficCost, String time, String foodCost, String liveCost, String cost, String detail, String bills) {
        if(toolId == null){
            toolId = "";
        }
        if(realId == null){
            realId = "-1";
        }
        if(time == null|| time.equals("")){
            ToastUtils.showShort(context,"请选择时间");
            return;
        }

        mRepository.applyReimbursement3(context,realId,"4",time,startAddress,endAddress,trafficCost,outTrafficCost,foodCost,liveCost,cost,detail,bills,toolId, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                if(response.getBoolean("rt")){
                    ToastUtils.showShort(context,"提交成功");
                    mTripView.showReimbursement();
                }else{
                    ToastUtils.showShort(context,response.getString("error"));
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }
//    @Override
//    public void submitTripReimburse(String startAddress, String trafficCost, String time, String cost, String detail, String bills) {
//
//        if(toolId == null){
//            toolId = "";
//        }
//        if(realId == null){
//            realId = "-1";
//        }
//        if(time == null|| time.equals("")){
//            ToastUtils.showShort(context,"请选择时间");
//            return;
//        }
//
////        mRepository.applyReimbursement2(context,realId,"3",outId,toolId,time,startAddress,trafficCost,cost,detail,bills,address,serveNum, new JsonResponseHandler() {
////            @Override
////            public void onSuccess(int statusCode, JSONObject response) throws JSONException {
////                if(response.getBoolean("rt")){
////                    ToastUtils.showShort(context,"提交成功");
////                    mTripView.showReimbursement();
////                }else{
////                    ToastUtils.showShort(context,response.getString("error"));
////                }
////            }
////
////            @Override
////            public void onFailure(int statusCode, String error_msg) {
////
////            }
////        });
//    }


}
