package com.example.shixi_a.myapplication.work.workAdministration.entertainmentReimburse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.GlobalApp;
import com.example.shixi_a.myapplication.bean.Reimbursement;
import com.example.shixi_a.myapplication.work.workAdministration.linkMan.LinkManActivity;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.work.workAdministration.trafficTool.TrafficToolActivity;
import com.example.shixi_a.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by a5376 on 2017/7/28.
 */

public class EntertainmentReimbursePresenter implements EntertainmentReimburseContract.Presenter{

    private SharedPreferences sp;

    private ReimbursementRepository mRepository;
    private EntertainmentReimburseFragment mTrafficView;
    private Context context;

    private String realId;

    private String outId;
    private String address;
    private String custom;

    private String toolId;
    private Reimbursement reimbursement;
    private boolean FIRST = true;



    @Override
    public void result(int requestCode, int resultCode, Intent data) {
        if(TrafficToolActivity.REQUEST_TOOL_CODE == requestCode && Activity.RESULT_OK == resultCode){
            toolId = data.getStringExtra("id");
            String name = data.getStringExtra("value");
            mTrafficView.setTraffic(name);
        }
        if(LinkManActivity.REQUEST_USERS_CODE == requestCode && Activity.RESULT_OK == resultCode){
            realId = data.getStringExtra("id");
            String name = data.getStringExtra("name");
            mTrafficView.setRealName(name);
        }
    }

    public EntertainmentReimbursePresenter(Reimbursement reimbursement, String custom, String out_id, String address, ReimbursementRepository repository, EntertainmentReimburseFragment entertainmentReimburseFragment, Context context) {
        mRepository = repository;
        mTrafficView = entertainmentReimburseFragment;
        mTrafficView.setPresenter(this);
        outId = out_id;
        this.context = context;
        this.address = address;
        this.custom = custom;
        this.reimbursement = reimbursement;
    }

    @Override
    public void start() {
        if(reimbursement != null){
            mTrafficView.InitView(reimbursement.getDttime(),reimbursement.getAddr(),reimbursement.getIncity_traffic_fee(),reimbursement.getFee_total(),reimbursement.getMemo(),reimbursement.getBill_num(),reimbursement.getServe_num());
            if(FIRST){
                mTrafficView.setRealName(reimbursement.getApplicant_name());
                mTrafficView.setTraffic(reimbursement.getIncify_traffic_show());
                realId = reimbursement.getApplicant_id();
                toolId = reimbursement.getIncity_traffic_by();
                FIRST = false;
            }
        }else{
            mTrafficView.initAddress(address);
            mTrafficView.initPerson(custom);
        }
        String name = GlobalApp.getInstance().getUserName();
        mTrafficView.setName(name);
    }

    @Override
    public void submitTrafficReimburse(String startAddress, String trafficCost, String time, String cost, String detail, String bills, String serveNum) {

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
        if (reimbursement == null) {
            mRepository.applyReimbursement2(context, realId, "3", outId, toolId, time, startAddress, trafficCost, cost, detail, bills, address, serveNum, new JsonResponseHandler() {
                @Override
                public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                    if (response.getBoolean("rt")) {
                        ToastUtils.showShort(context, "提交成功");
                        mTrafficView.showReimbursement();
                    } else {
                        ToastUtils.showShort(context, response.getString("error"));
                    }
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {

                }
            });
        }else{
            mRepository.editReimbursement2(context,reimbursement.getId(),realId,"3",toolId,time,startAddress,trafficCost,cost,detail,bills,serveNum, new JsonResponseHandler() {
                @Override
                public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                    if (response.getBoolean("rt")) {
                        ToastUtils.showShort(context, "修改成功");
                        mTrafficView.showReimbursement();
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
