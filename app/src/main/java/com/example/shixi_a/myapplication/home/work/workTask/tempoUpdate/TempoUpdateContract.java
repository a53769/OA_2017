package com.example.shixi_a.myapplication.home.work.workTask.tempoUpdate;

import android.content.Context;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

/**
 * Created by a5376 on 2017/6/30.
 */

public interface TempoUpdateContract {

    interface Presenter extends BasePresenter {

        void saveChange(Context context, String id, int progress, String memo);
    }
    interface View extends BaseView<Presenter> {

        void showTaskDetail();
    }
}
