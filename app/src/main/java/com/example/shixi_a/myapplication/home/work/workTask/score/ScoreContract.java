package com.example.shixi_a.myapplication.home.work.workTask.score;

import android.content.Context;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

/**
 * Created by a5376 on 2017/6/30.
 */

public interface ScoreContract {
    interface Presenter extends BasePresenter {

        void saveChange(Context context, String taskId, float progress, float progress1, float progress2, String memo);
    }
    interface View extends BaseView<Presenter> {

        void showTasks();
    }
}
