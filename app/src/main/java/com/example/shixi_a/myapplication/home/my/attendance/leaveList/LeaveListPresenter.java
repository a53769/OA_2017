package com.example.shixi_a.myapplication.home.my.attendance.leaveList;

import com.example.shixi_a.myapplication.bean.AttendanceState;

import java.util.List;

/**
 * Created by a5376 on 2017/7/20.
 */

public class LeaveListPresenter implements LeaveListContract.Presenter {
    private LeaveListFragment mLeaveLogsView;

    private List<AttendanceState.LeaveInfo> leaves;



    public LeaveListPresenter(List<AttendanceState.LeaveInfo> leaves, LeaveListFragment leaveListFragment) {
        mLeaveLogsView = leaveListFragment;
        mLeaveLogsView.setPresenter(this);
        this.leaves = leaves;
    }

    @Override
    public void start() {
        mLeaveLogsView.setLoadingIndicator(true);
        process(leaves);
        mLeaveLogsView.setLoadingIndicator(false);
    }

    private void process(List<AttendanceState.LeaveInfo> leaves) {
        mLeaveLogsView.showLogs(leaves);
    }
}
