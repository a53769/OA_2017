package com.example.shixi_a.myapplication.work.workAdministration.checkOut;

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

        void showToast(String 请检查网络连接并开启定位权限);
    }
    interface Presenter extends BasePresenter {

        void checkOut(String memo);
    }
}
