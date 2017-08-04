package com.example.shixi_a.myapplication.work.workAdministration.leave;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.Leave;

import java.util.List;

/**
 * Created by a5376 on 2017/7/18.
 */

public interface LeaveContract {
    interface Presenter extends BasePresenter {

    }
    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(final boolean active);

        void showLeave(List<Leave> leaves);
    }
}
