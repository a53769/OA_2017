package com.example.shixi_a.myapplication.checkOut;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

/**
 * Created by a5376 on 2017/7/14.
 */

public interface CheckOutContract {

    interface View extends BaseView<Presenter> {

        void initView(String out_time, String addr);

        void showMessage();

        void setAddress(String address);
    }
    interface Presenter extends BasePresenter {

        void checkOut(String memo);
    }
}
