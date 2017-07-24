package com.example.shixi_a.myapplication.reimbursement;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.Reimbursement;

import java.util.List;

/**
 * Created by a5376 on 2017/7/24.
 */

public interface ReimbursementContract {
    interface Presenter extends BasePresenter {

    }
    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(final boolean active);

        void showReimburses(List<Reimbursement> list);
    }
}
