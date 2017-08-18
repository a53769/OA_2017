package com.example.shixi_a.myapplication.home.work.workAdministration.leaveLogs;

import android.content.Context;

import com.example.shixi_a.myapplication.bean.Leave;
import com.example.shixi_a.myapplication.model.leave.LeaveRepository;

import java.util.List;

/**
 * Created by a5376 on 2017/7/20.
 */

public class LeaveLogsPresenter implements LeaveLogsContract.Presenter {
    private LeaveRepository mRepository;
    private LeaveLogsFragment mLeaveLogsView;
    private Context context;
    private List<Leave.Log> logs;

    public LeaveLogsPresenter(List<Leave.Log> logs, LeaveRepository repository, LeaveLogsFragment leaveLogsFragment, Context context) {
        mRepository = repository;
        mLeaveLogsView = leaveLogsFragment;
        mLeaveLogsView.setPresenter(this);
        this.context = context;
        this.logs = logs;
    }

    @Override
    public void start() {
        mLeaveLogsView.setLoadingIndicator(true);
        process(logs);
        mLeaveLogsView.setLoadingIndicator(false);
    }

    private void process(List<Leave.Log> logs) {
        mLeaveLogsView.showLogs(logs);
    }
}
