package com.example.shixi_a.myapplication.home.work.workAdministration.reimburseLog;

import android.content.Context;

import com.example.shixi_a.myapplication.bean.Reimbursement;

import java.util.List;

/**
 * Created by a5376 on 2017/7/31.
 */

public class ReimburseLogPresenter implements ReimburseLogContract.Presenter {
    private ReimburseLogFragment mReimburseLogView;
    private Context context;
    private List<Reimbursement.Log> logs;

    public ReimburseLogPresenter(List<Reimbursement.Log> logs, ReimburseLogFragment reimburseLogFragment, Context context) {
        mReimburseLogView = reimburseLogFragment;
        mReimburseLogView.setPresenter(this);
        this.logs = logs;
        this.context = context;
    }

    @Override
    public void start() {
        process(logs);
        mReimburseLogView.setLoadingIndicator(false);
    }

    private void process(List<Reimbursement.Log> logs) {
        mReimburseLogView.showLogs(logs);
    }
}
