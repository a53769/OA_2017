package com.example.shixi_a.myapplication.tripReimburse;

import android.content.Intent;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

/**
 * Created by a5376 on 2017/7/24.
 */

public interface TripReimburseContract {
    interface View extends BaseView<Presenter> {



        void showReimbursement();

    }
    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode, Intent data);


        void submitTripReimburse(String startAddress, String endAddress, String trafficCost, String outTrafficCost, String time, String foodCost, String liveCost, String cost, String detail, String bills);
    }
}
