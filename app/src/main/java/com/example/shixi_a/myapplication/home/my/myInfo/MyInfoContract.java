package com.example.shixi_a.myapplication.home.my.myInfo;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

/**
 * Created by a5376 on 2017/8/17.
 */

public interface MyInfoContract {
    interface View extends BaseView<Presenter> {

        void InitView(String adminname, String real_name, String email, String phone, String in_time);


    }
    interface Presenter extends BasePresenter {

    }
}

