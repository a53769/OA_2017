package com.example.shixi_a.myapplication.applyleave;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Leave;
import com.example.shixi_a.myapplication.leaveType.LeaveTypeActivity;
import com.example.shixi_a.myapplication.linkMan.LinkManActivity;
import com.example.shixi_a.myapplication.model.leave.LeaveRepository;
import com.example.shixi_a.myapplication.util.LogUtils;
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


    @Override
    public void result(int requestCode, int resultCode, Intent data) {
        if (LeaveTypeActivity.REQUEST_TYPE_CODE == requestCode && Activity.RESULT_OK == resultCode) {
            String typeName = data.getStringExtra("value");
            typeId = data.getStringExtra("id");
            mApplyLeaveView.setTypeName(typeName);
        }
        if(LinkManActivity.REQUEST_USERS_CODE == requestCode && Activity.RESULT_OK == resultCode){
            handeoverId = data.getStringExtra("id");
            String handeover = data.getStringExtra("name");
            mApplyLeaveView.setHandOver(handeover);
        }
        LogUtils.v("我是result");
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
            if(leave.getOpt().equals("edit")&& FIRST){
                mApplyLeaveView.initView(leave.getSort_show(),leave.getHandover_name());
                typeId = leave.getSort();
                handeoverId = leave.getHandover_aid();
                FIRST = false;
            }else if(leave.getOpt().equals("hr_edit")){
                mApplyLeaveView.coverClick();
            }
        }
        LogUtils.v("我是start");
    }



    @Override
    public void submitLeave(String stime, String etime, String is_handle, String reason) {
        if (is_handle == null)
            is_handle = "";
        if (handeoverId == null)
            handeoverId = "";

        if(leave == null) {
            mRepository.applyLeave(context, typeId, stime, etime, is_handle, handeoverId, reason, new JsonResponseHandler() {
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
        }else if(leave.getOpt().equals("hr_edit")){
            String opt = "1";
            mRepository.editHRLeave(context,leaveId,opt,stime,etime, new JsonResponseHandler() {
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
        }else if(leave.getOpt().equals("edit")){
            mRepository.editLeave(context, leaveId,typeId, stime, etime, is_handle, handeoverId, reason, new JsonResponseHandler() {
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
