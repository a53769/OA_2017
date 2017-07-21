package com.example.shixi_a.myapplication.tempoUpdate;

import android.content.Context;

import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.util.ToastUtils;
import com.example.shixi_a.myapplication.model.task.TaskRepository;

/**
 * Created by a5376 on 2017/6/30.
 */

public class TempoUpdatePresent implements TempoUpdateContract.Presenter {

    private TempoUpdateContract.View mTempoView;

    private TaskRepository mRepository;

    public TempoUpdatePresent(TempoUpdateActivity tempoUpdateActivity) {
        mTempoView = tempoUpdateActivity;
    }

    @Override
    public void start() {
    }

    @Override
    public void saveChange(final Context context, String id, int progress, String memo) {
        mRepository = new TaskRepository();
        mRepository.updateTempo(context,id,String.valueOf(progress),memo, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                ToastUtils.showShort(context,"更新成功");
                mTempoView.showTaskDetail();
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"更新失败");
            }
        });
    }
}
