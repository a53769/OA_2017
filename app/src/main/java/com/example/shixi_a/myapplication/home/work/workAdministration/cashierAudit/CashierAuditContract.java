package com.example.shixi_a.myapplication.home.work.workAdministration.cashierAudit;

import android.content.Intent;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

/**
 * Created by a5376 on 2017/8/2.
 */

public interface CashierAuditContract {
    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode, Intent data);

        void auditPass(String pay_time, String pay_fee, String memo);
    }
    interface View extends BaseView<Presenter> {

        void showPayment();

        void showReimburse();
    }
}
