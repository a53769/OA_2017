package com.example.shixi_a.myapplication.home.work.workTask.taskDetail;

import android.content.Intent;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.Assessment;
import com.example.shixi_a.myapplication.bean.Logs;

import java.util.List;

/**
 * Created by a5376 on 2017/6/28.
 */

public interface TaskDetailContract {

    interface View extends BaseView<Presenter> {

        void initView(String Title, String goal, String exec_method, String creater_name, String create_time, String status );

        void showProcess(String id);

        void setLoadingIndicator(final boolean active);

        void setLogs(List<Logs> logs);

        void showAssess(Assessment assess);

        void showNoAccessView();

        void showNoAssessView();

        void showDoing();

        void showUpdateTempo(String taskId, String tempo);

        void showNoScoreView();

        void showScore(String taskId);

        void showTasks();

        void refuseProcess(String id);

        void showNoOpt();
    }
    interface Presenter extends BasePresenter {

        void showProcess();

        void loadLog();

        void loadTask();

        void showUpdateTempo();

        void dealTask(String turnover);

        void acceptanceTask(String pass);

        void showScore();

        void result(int requestCode, int resultCode, Intent data);

        String getTempo();

        void refuseProcess();

        String getStatus();
    }

}
