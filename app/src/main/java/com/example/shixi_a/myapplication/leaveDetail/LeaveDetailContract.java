package com.example.shixi_a.myapplication.leaveDetail;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.Leave;

/**
 * Created by a5376 on 2017/7/18.
 */

public interface LeaveDetailContract {
    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(final boolean active);

        void showLeave(Leave leave);

        void showLeaveLogs(Leave logs);

        void showSelfEdit();

        void showHREdit();

        void showAudit();

        void showLeaves();

        void showEditLeave(Leave leave);
    }
    interface Presenter extends BasePresenter {

        void showLeaveLogs();

        void cancelLeave();

        void editLeave();


        void Audit(String s);
    }
}
