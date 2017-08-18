package com.example.shixi_a.myapplication.home.work.workAdministration.leave;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Leave;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.model.leave.LeaveRepository;
import com.example.shixi_a.myapplication.util.ToastUtils;

import java.util.List;

/**
 * Created by a5376 on 2017/7/18.
 */

public class LeavePresenter implements LeaveContract.Presenter{
    private LeaveRepository mRepository;
    private LeaveFragment mLeaveView;
    private Context context;

    public LeavePresenter(LeaveRepository repository, LeaveFragment leaveFragment, Context context) {
        mRepository = repository;
        mLeaveView = leaveFragment;
        mLeaveView.setPresenter(this);
        this.context = context;
    }

    @Override
    public void start() {
        mLeaveView.setLoadingIndicator(true);
        loadLeave();
        mLeaveView.setLoadingIndicator(false);
    }

    private void loadLeave() {
        mRepository.getLeaves(context, new GsonResponseHandler<RowsNoPage<Leave>>() {
            @Override
            public void onSuccess(int statusCode, RowsNoPage<Leave> response) {
                List<Leave> leaves;
                leaves = response.rows;
                process(leaves);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }

    private void process(List<Leave> leaves) {
        if(leaves.isEmpty()){
            ToastUtils.showShort(context,"列表为空");
        }
        mLeaveView.showLeave(leaves);
    }
}
