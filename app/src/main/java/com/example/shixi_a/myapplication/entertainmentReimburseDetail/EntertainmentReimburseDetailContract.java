package com.example.shixi_a.myapplication.entertainmentReimburseDetail;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.Reimbursement;

/**
 * Created by a5376 on 2017/7/27.
 */

public interface EntertainmentReimburseDetailContract {
    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(final boolean active);

        void InitView(String status_show, String admin_name, String applicant_name, String type_show, String dttime, String memo, String fee_total, String bill_num, String sum_month, String addr, String incity_traffic_show, String incity_traffic_fee, String serve_num);

        void showReimburseLog(Reimbursement reimbursement);

        void showEdit();

        void showAudit();

        void showFinanceAudit();

        void showCashierAudit();

        void showReimbursement();

        void showFinanceType(String reimburseId);

        void showCashier(String reimburseId);

        void hideButton();
    }
    interface Presenter extends BasePresenter {

        void showReimburseLog();

        void cancel();

        void edit();

        void Audit(String s);

        void FinanceAudit(String s);

        void CashierAudit(String s);
    }
}
