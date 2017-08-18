package com.example.shixi_a.myapplication.home.work.workAdministration.applyout;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

/**
 * Created by a5376 on 2017/7/12.
 */

public interface ApplyOutContract {

    interface View extends BaseView<Presenter> {

        void showMessage(String msg);

        void showEgress();

        void initView(String out_time, String username, String addr, String matter);
    }
    interface Presenter extends BasePresenter {

        void askOut(String time, String person, String address, String reason);
    }
}
