package com.example.shixi_a.myapplication.normalReimburse;

import android.content.Intent;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

/**
 * Created by a5376 on 2017/7/24.
 */

public interface NormalReimburseContract {
    interface View extends BaseView<Presenter> {

        void showReimburse();

        void InitView(String dttime, String fee_total, String memo, String bill_num);
    }
    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode, Intent data);

        void applyReimbursement(String time, String cost, String detail, String bills);
    }
}
