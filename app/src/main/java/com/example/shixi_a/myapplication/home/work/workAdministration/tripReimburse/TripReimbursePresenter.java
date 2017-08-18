package com.example.shixi_a.myapplication.home.work.workAdministration.tripReimburse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.GlobalApp;
import com.example.shixi_a.myapplication.bean.Reimbursement;
import com.example.shixi_a.myapplication.home.work.workAdministration.linkMan.LinkManActivity;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.home.work.workAdministration.trafficTool.TrafficToolActivity;
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

    private boolean FIRST = true;

    private String realId;

    private String toolId;
    private Reimbursement reimbursement;


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

    public TripReimbursePresenter(Reimbursement reimbursement, ReimbursementRepository repository, TripReimburseFragment tripReimburseFragment, Context context) {
        mRepository = repository;
        mTripView = tripReimburseFragment;
        mTripView.setPresenter(this);
        this.reimbursement = reimbursement;
        this.context = context;

    }

    @Override
    public void start() {
        if(reimbursement != null){
            mTripView.InitView(reimbursement.getDttime(),
                    reimbursement.getAddr(),
                    reimbursement.getIncity_traffic_fee(),
                    reimbursement.getOutcity_traffic_show(),
                    reimbursement.getOutcity_traffic_fee(),
                    reimbursement.getBoarding_fee(),
                    reimbursement.getAccomdation_fee(),
                    reimbursement.getFee_total(),
                    reimbursement.getMemo(),
                    reimbursement.getBill_num());
            if(FIRST){
                mTripView.setRealName(reimbursement.getApplicant_name());
                mTripView.setTraffic(reimbursement.getIncify_traffic_show());
                realId = reimbursement.getApplicant_id();
                toolId = reimbursement.getOutcity_traffic_by();
                FIRST = false;
            }
        }
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
        if(reimbursement == null) {
            mRepository.applyReimbursement3(context, realId, "4", time, startAddress, endAddress, trafficCost, outTrafficCost, foodCost, liveCost, cost, detail, bills, toolId, new JsonResponseHandler() {
                @Override
                public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                    if (response.getBoolean("rt")) {
                        ToastUtils.showShort(context, "提交成功");
                        mTripView.showReimbursement();
                    } else {
                        ToastUtils.showShort(context, response.getString("error"));
                    }
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {

                }
            });
        }else{
            mRepository.editReimbursement3(context,reimbursement.getId(),realId,"4",time,startAddress,endAddress,trafficCost,outTrafficCost,foodCost,liveCost,cost,detail,bills,toolId, new JsonResponseHandler() {
                @Override
                public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                    if (response.getBoolean("rt")) {
                        ToastUtils.showShort(context, "提交成功");
                        mTripView.showReimbursement();
                    } else {
                        ToastUtils.showShort(context, response.getString("error"));
                    }
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {

                }
            });
        }
    }

}
