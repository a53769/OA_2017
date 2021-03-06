package com.example.shixi_a.myapplication.home.work.workAdministration.applyleave;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Leave;
import com.example.shixi_a.myapplication.home.work.workAdministration.leaveType.LeaveTypeActivity;
import com.example.shixi_a.myapplication.home.work.workAdministration.linkMan.LinkManActivity;
import com.example.shixi_a.myapplication.model.leave.LeaveRepository;
import com.example.shixi_a.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by a5376 on 2017/7/18.
 */

public class ApplyLeavePresenter implements ApplyLeaveContract.Presenter {

    private LeaveRepository mRepository;
    private ApplyLeaveFragment mApplyLeaveView;
    private Context context;

    private String typeId;
    private String handeoverId;

    private Leave leave;
    private String leaveId;
    private boolean FIRST = true;

    static String extraWorkStart = "";
    static String extraWorkEnd = "";
    static String extraWorkContent = "";


    @Override
    public void result(int requestCode, int resultCode, Intent data) {
        if (LeaveTypeActivity.REQUEST_TYPE_CODE == requestCode && Activity.RESULT_OK == resultCode) {
            String typeName = data.getStringExtra("value");
            typeId = data.getStringExtra("id");
            mApplyLeaveView.setTypeName(typeName);
            if(typeId.equals("6")){
                mApplyLeaveView.showOffset();
            }else{
                mApplyLeaveView.hideoffset();
            }
        }
        if(LinkManActivity.REQUEST_USERS_CODE == requestCode && Activity.RESULT_OK == resultCode){
            handeoverId = data.getStringExtra("id");
            String handeover = data.getStringExtra("name");
            mApplyLeaveView.setHandOver(handeover);
        }
    }

    public ApplyLeavePresenter(Leave leave, LeaveRepository repository, ApplyLeaveFragment applyLeaveFragment, Context context) {
        mRepository = repository;
        mApplyLeaveView = applyLeaveFragment;
        mApplyLeaveView.setPresenter(this);
        this.context = context;
        this.leave = leave;
    }

    @Override
    public void start() {//第二次加载会不能覆盖typeID
        if(leave != null){
            //分类ID和转接ID分离
            mApplyLeaveView.InitView( leave.getOff_start(), leave.getOff_end(), leave.getNeed_modify(), leave.getReason());
            leaveId = leave.getId();
            if(FIRST){
                mApplyLeaveView.initView(leave.getSort_show(),leave.getHandover_name());
                typeId = leave.getSort();
                handeoverId = leave.getHandover_aid();
                if(typeId.equals("6")){
                    mApplyLeaveView.showOffset();
                    mApplyLeaveView.initOffset(leave.getOff_start(),leave.getOffset_end(),leave.getOffset_memo());

                }
                FIRST = false;
            }
        }
    }



    @Override
    public void submitLeave(@NonNull String stime,@NonNull String etime, String is_handle,@NonNull String reason) {
        if (is_handle == null)
            is_handle = "";
        if (handeoverId == null)
            handeoverId = "";
        if(typeId == null){
            ToastUtils.showShort(context,"请选择请假类型");
            return;
        }

        if(reason.equals(""))
        {
            ToastUtils.showShort(context,"请填写请假原因");
            return;
        }


        if(leave == null) {
            mRepository.applyLeave(context, typeId, stime, etime, is_handle, handeoverId, reason, extraWorkStart,extraWorkEnd,extraWorkContent,new JsonResponseHandler() {

                @Override
                public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                    if (response.getBoolean("rt")) {
                        ToastUtils.showShort(context, "提交成功");
                        mApplyLeaveView.showLeaveDetail();
                    } else {
                        ToastUtils.showShort(context, response.getString("error"));
                    }
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    ToastUtils.showShort(context, "提交失败");
                }
            });
        }else {
            mRepository.editLeave(context, leaveId,typeId, stime, etime, is_handle, handeoverId, reason, extraWorkStart,extraWorkEnd,extraWorkContent,new JsonResponseHandler() {
                @Override
                public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                    if (response.getBoolean("rt")) {
                        ToastUtils.showShort(context, "修改成功");
                        mApplyLeaveView.showLeaveDetail();
                    } else {
                        ToastUtils.showShort(context, response.getString("error"));
                    }
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    ToastUtils.showShort(context, "修改失败");
                }
            });
        }
    }





}
