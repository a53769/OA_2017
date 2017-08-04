package com.example.shixi_a.myapplication.work.workAdministration.trafficReimburse;

import android.content.Intent;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

/**
 * Created by a5376 on 2017/7/28.
 */

public interface TrafficReimburseContract {

    interface View extends BaseView<Presenter> {

        void initAddress(String address);

        void showReimbursement();

        void initPerson(String custom);

        void InitView(String dttime, String addr, String incity_traffic_fee, String fee_total, String memo, String bill_num);
    }
    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode, Intent data);

        void submitTrafficReimburse(String startAddress, String trafficCost, String time, String cost, String detail, String bills);
    }
}
