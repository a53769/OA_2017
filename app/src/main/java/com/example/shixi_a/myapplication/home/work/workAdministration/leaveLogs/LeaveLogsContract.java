package com.example.shixi_a.myapplication.home.work.workAdministration.leaveLogs;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.Leave;

import java.util.List;

/**
 * Created by a5376 on 2017/7/20.
 */

public interface LeaveLogsContract {

    interface View extends BaseView<Presenter> {

        void showLogs(List<Leave.Log> logs);
        void setLoadingIndicator(final boolean active);
    }
    interface Presenter extends BasePresenter {

    }
}
