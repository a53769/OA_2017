package com.example.shixi_a.myapplication.home.work.workTask.score;

import android.content.Context;

import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.util.ToastUtils;
import com.example.shixi_a.myapplication.model.task.TaskRepository;

/**
 * Created by a5376 on 2017/6/30.
 */

public class ScorePresenter implements ScoreContract.Presenter {

    private ScoreContract.View mScoreView;
    private TaskRepository mRepository;


    public ScorePresenter(ScoreActivity scoreActivity) {
        mScoreView = scoreActivity;
        mScoreView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void saveChange(final Context context, String taskId, float progress, float progress1, float progress2, String memo) {
        mRepository = new TaskRepository();

        mRepository.evaluateTask(context, taskId, String.valueOf(progress), String.valueOf(progress1), String.valueOf(progress2), memo, new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
                        ToastUtils.showShort(context,"操作成功");
                        mScoreView.showTasks();
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        ToastUtils.showShort(context,"操作失败");
                    }
                });
    }

}
