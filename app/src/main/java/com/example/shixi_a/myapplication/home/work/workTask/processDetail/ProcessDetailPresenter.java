package com.example.shixi_a.myapplication.home.work.workTask.processDetail;

import android.content.Context;

import com.example.shixi_a.myapplication.model.process.ProcessRepository;

import com.example.shixi_a.myapplication.bean.Process;

/**
 * Created by a5376 on 2017/8/17.
 */

public class ProcessDetailPresenter implements ProcessDetailContract.Presenter{
    private ProcessRepository mRepository;
    private ProcessDetailFragment mProcessDetailView;
    private Context context;
    private Process process;

    public ProcessDetailPresenter(Process process, ProcessRepository repository, ProcessDetailFragment processDetailFragment, Context context) {
        mRepository = repository;
        mProcessDetailView = processDetailFragment;
        mProcessDetailView.setPresenter(this);
        this.context = context;
        this.process = process;
    }

    @Override
    public void start() {
        mProcessDetailView.InitView(process.getTitle(),process.getDescription(),process.getEstimate_stime(),process.getEstimate_etime(),process.getUser_name());
    }
}
