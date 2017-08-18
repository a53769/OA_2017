package com.example.shixi_a.myapplication.home.work.workTask.milestone;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.shixi_a.myapplication.bean.MileStone;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.model.task.TaskRepository;

import java.util.List;

/**
 * Created by a5376 on 2017/7/17.
 */

public class MileStonePresenter implements MileStoneContract.Presenter {

    private TaskRepository mRepository;
    private MileStoneFragment mMileView;
    private Context context;
    private String proId;

    public MileStonePresenter(TaskRepository repository, MileStoneFragment mileStoneFragment, Context context, String projectId) {
        mRepository = repository;
        mMileView = mileStoneFragment;
        mMileView.setPresenter(this);
        this.context = context;
        proId = projectId;
    }

    @Override
    public void start() {
        loadMileStone();
    }

    private void loadMileStone() {
        mRepository.getMilestone(context, proId, new GsonResponseHandler<RowsNoPage<MileStone>>() {
            @Override
            public void onSuccess(int statusCode, RowsNoPage<MileStone> response) {
                List<MileStone> list = response.rows;
                process(list);
                mMileView.setLoadingIndicator(false);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }

    private void process(List<MileStone> list) {
        mMileView.showMilestones(list);
    }

    @Override
    public void selectedMileStone(MileStone clickedMileStone) {
        mMileView.showTaskAdd(clickedMileStone.getId(),clickedMileStone.getName());
    }
}
