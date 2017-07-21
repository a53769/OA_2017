package com.example.shixi_a.myapplication.leaveDetail;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Leave;
import com.example.shixi_a.myapplication.model.leave.LeaveRepository;
import com.example.shixi_a.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by a5376 on 2017/7/18.
 */

public class LeaveDetailPresenter implements LeaveDetailContract.Presenter {

    private LeaveRepository mRepository;
    private LeaveDetailFragment mLeaveDetailView;
    private String leaveId;
    private Context context;
    private Leave leave;

    public LeaveDetailPresenter(String leaveId, LeaveRepository repository, LeaveDetailFragment leaveDetailFragment, Context context) {
        mRepository = repository;
        mLeaveDetailView = leaveDetailFragment;
        mLeaveDetailView.setPresenter(this);
        this.leaveId = leaveId;
        this.context = context;
    }

    @Override
    public void start() {
        loadLeave();
        mLeaveDetailView.setLoadingIndicator(false);
    }

    private void loadLeave() {
        mRepository.getLeave(context,leaveId, new GsonResponseHandler<Leave>() {
            @Override
            public void onSuccess(int statusCode, Leave response) {
                leave = response;
                process(leave);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"获取失败");
            }
        });
    }

    private void process(Leave leave) {
        mLeaveDetailView.showLeave(leave);
        switch (leave.getOpt()){
            case "edit"://自己修改
                mLeaveDetailView.showSelfEdit();
                break;
            case "hr_edit"://人事修改
                mLeaveDetailView.showHREdit();
                break;
            case "audit"://审批
                mLeaveDetailView.showAudit();
                break;
            default:
                break;
        }
    }

    @Override
    public void showLeaveLogs() {
        mLeaveDetailView.showLeaveLogs(leave);
    }

    @Override
    public void cancelLeave() {
        mRepository.cancelLeave(context,leaveId, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                if(response.getBoolean("rt")){
                    ToastUtils.showShort(context,"取消成功");
                    mLeaveDetailView.showLeaves();
                }else{
                    ToastUtils.showShort(context,response.getString("error"));
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"取消失败");
            }
        });
    }

    @Override
    public void editLeave() {
// TODO: 2017/7/20 跳转编辑界面 需要传参 编辑类型 leave对象
        mLeaveDetailView.showEditLeave(leave);
    }

    @Override
    public void Audit(String s) {
        mRepository.AuditLeave(context,leaveId,leave.getStatus(),s, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                if(response.getBoolean("rt")){
                    ToastUtils.showShort(context,"审批成功");
                    mLeaveDetailView.showLeaves();
                }else{
                    ToastUtils.showShort(context,response.getString("error"));
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"审批失败");
            }
        });
    }


}

