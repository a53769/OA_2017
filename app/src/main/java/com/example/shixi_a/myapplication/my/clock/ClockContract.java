package com.example.shixi_a.myapplication.my.clock;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

/**
 * Created by a5376 on 2017/7/5.
 */

public interface ClockContract {
    interface View extends BaseView<Presenter> {

        void showWIFI(String location);

        void hideClockIn(String time);

        void hideClockOut(String time);

        void setLoadingIndicator(final boolean active);

        void hideClockInWithUnCheck();

        void getPermission();
    }
    interface Presenter extends BasePresenter {

        void checkOn(String s);
    }
}
