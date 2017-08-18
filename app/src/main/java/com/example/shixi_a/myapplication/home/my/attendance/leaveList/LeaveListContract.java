package com.example.shixi_a.myapplication.home.my.attendance.leaveList;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.AttendanceState;

import java.util.List;

/**
 * Created by a5376 on 2017/7/20.
 */

public interface LeaveListContract {

    interface View extends BaseView<Presenter> {

        void showLogs(List<AttendanceState.LeaveInfo> leaves);
        void setLoadingIndicator(final boolean active);
    }
    interface Presenter extends BasePresenter {

    }
}
