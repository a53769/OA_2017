package com.example.shixi_a.myapplication.home.work.workAdministration.reimburseLog;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.Reimbursement;

import java.util.List;

/**
 * Created by a5376 on 2017/7/31.
 */

public interface ReimburseLogContract {
    interface View extends BaseView<Presenter> {

        void showLogs(List<Reimbursement.Log> logs);
        void setLoadingIndicator(final boolean active);
    }
    interface Presenter extends BasePresenter {

    }
}
