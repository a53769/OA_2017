package com.example.shixi_a.myapplication.home.work.workTask.processDetail;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

/**
 * Created by a5376 on 2017/8/17.
 */

public interface ProcessDetailContract {
    interface View extends BaseView<Presenter> {

        void InitView(String title, String content, String s_time, String e_time, String principal);


    }
    interface Presenter extends BasePresenter {

    }
}
