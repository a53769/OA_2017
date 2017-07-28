package com.example.shixi_a.myapplication.trafficReimburse;

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

public class TrafficReimbursePresenter implements TrafficReimburseContract.Presenter{

    private SharedPreferences sp;

    private ReimbursementRepository mRepository;
    private TrafficReimburseFragment mTrafficView;
    private Context context;

    private String realId;

    private String outId;
    private String address;

    private String toolId;


    @Override
    public void start() {
//        sp = PreferenceManager.getDefaultSharedPreferences(context);
//        String name = sp.getString("name","");
        String name = GlobalApp.getInstance().getUserName();
        mTrafficView.setName(name);
        mTrafficView.initAddress(address);
    }

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

    public TrafficReimbursePresenter(String out_id, String address, ReimbursementRepository repository, TrafficReimburseFragment trafficReimburseFragment, Context context) {
        mRepository = repository;
        mTrafficView = trafficReimburseFragment;
        mTrafficView.setPresenter(this);
        outId = out_id;
        this.context = context;
        this.address = address;
    }

    @Override
    public void submitTrafficReimburse(String startAddress, String trafficCost, String time, String cost, String detail, String bills) {

        if(toolId == null){
            toolId = "";
        }
        if(realId == null){
            realId = "-1";
        }

        mRepository.applyReimbursement1(context,realId,"2",outId,toolId,time,startAddress,trafficCost,cost,detail,bills,address, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                if(response.getBoolean("rt")){
                    ToastUtils.showShort(context,"提交成功");
                    mTrafficView.showReimbursement();
                }else{
                    ToastUtils.showShort(context,response.getString("error"));
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }


}
