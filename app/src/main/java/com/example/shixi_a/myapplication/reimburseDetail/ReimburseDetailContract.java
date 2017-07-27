package com.example.shixi_a.myapplication.reimburseDetail;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

/**
 * Created by a5376 on 2017/7/27.
 */

public interface ReimburseDetailContract {
    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(final boolean active);


        void InitView(String status_show, String admin_name, String applicant_name, String type_show, String dttime, String memo, String fee_total, String bill_num, String sum_month);
    }
    interface Presenter extends BasePresenter {


    }
}
