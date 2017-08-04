package com.example.shixi_a.myapplication.my.attendance;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.AttendanceState;

/**
 * Created by a5376 on 2017/7/7.
 */

public interface AttendanceContract {

    interface View extends BaseView<Presenter> {

        void initView(AttendanceState response);

        void setLoadingIndicator(final boolean active);
    }
    interface Presenter extends BasePresenter {

    }
}
