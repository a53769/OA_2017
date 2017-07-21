package com.example.shixi_a.myapplication.taskDetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.contacts.ContactsActivity;
import com.example.shixi_a.myapplication.process.ProcessActivity;
import com.example.shixi_a.myapplication.util.LogUtils;
import com.example.shixi_a.myapplication.util.ToastUtils;
import com.example.shixi_a.myapplication.bean.Assessment;
import com.example.shixi_a.myapplication.bean.Logs;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.bean.Task;
import com.example.shixi_a.myapplication.model.task.TaskRepository;

import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by a5376 on 2017/6/28.
 */

public class TaskDetailPresenter implements TaskDetailContract.Presenter{

    private TaskRepository mRepository;
    private TaskDetailFragment mTaskDetailView;
    private Context context;
    private Task task;
    private String userId="";//转接人ID
    private String stepId = "";//拒绝步骤
    private List<String> mList;


    public TaskDetailPresenter(Task task, TaskRepository repository, TaskDetailFragment taskDetailFragment, Context context) {

        mRepository = repository;

        mTaskDetailView = taskDetailFragment;

        mTaskDetailView.setPresenter(this);

        this.context = context;

        this.task = checkNotNull(task);
    }

    @Override
    public void result(int requestCode, int resultCode, Intent data) {
        if (ContactsActivity.REQUEST_CONTACTS_CODE == requestCode && Activity.RESULT_OK == resultCode) {
            Bundle bundle = data.getExtras();
            mList = (List<String>) bundle.getSerializable("list");
            userId = mList.get(0);
            dealTask("turnover");
            mTaskDetailView.showTasks();
        }
        if(ProcessActivity.REQUEST_PROCESS_CODE == requestCode && Activity.RESULT_OK == resultCode){
            stepId = data.getStringExtra("step_id");
            acceptanceTask("refuse");
            mTaskDetailView.showTasks();
        }
    }

    @Override
    public String getTempo() {
        return task.getStep_tempo();
    }

    @Override
    public void refuseProcess() {
        mTaskDetailView.refuseProcess(task.getId());
    }

    @Override
    public void start() {
        mTaskDetailView.setLoadingIndicator(true);
        loadTask();
        loadLog();
        //换成switch应该效率高一点
        if(task.getStatus().equals("10")){//完成
            loadAssess();
        }else if(task.getStatus().equals("1")){//待接收
            mTaskDetailView.showNoAccessView();
        }else if(task.getStatus().equals("8")){//待验收
            mTaskDetailView.showNoAssessView();
        }else if(task.getStatus().equals("4")||task.getStatus().equals("3")){//进行中
            mTaskDetailView.showDoing();
        }else if(task.getStatus().equals("9")){
            mTaskDetailView.showNoScoreView();
        }
        mTaskDetailView.setLoadingIndicator(false);


    }

    private void loadAssess() {
        mRepository.getAssessInfo(context,task.getId(), new GsonResponseHandler<Assessment>() {
            @Override
            public void onSuccess(int statusCode, Assessment response) {
                Assessment assess = response;
                mTaskDetailView.showAssess(assess);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogUtils.v("加载失败");
            }
        });
    }

    @Override
    public void loadTask() {
        mTaskDetailView.initView(task.getTitle(),task.getTarget_desc(),task.getExec_method(),task.getCreater_name(),task.getCreate_time(),task.getStatus());
    }

    @Override
    public void dealTask(String action) {
        String tunrnId;
        if(action.equals("accept")){
            tunrnId = "";
        }else if(action.equals("refuse")){
            tunrnId = "0";
        }else{
            tunrnId = userId;
        }
        mRepository.dealTask(context,task.getId(),action, tunrnId,new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                ToastUtils.showShort(context,"操作成功");
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"操作失败");
            }
        });
    }

    @Override
    public void acceptanceTask(String action) {
        String turnId;
        if(action.equals("pass")){
            turnId = "0";
        }else{
            turnId = stepId;
        }
        mRepository.acceptanceTask(context,task.getId(),action,turnId, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                ToastUtils.showShort(context,"操作成功");
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"操作失败");
            }
        });
    }

    @Override
    public void showScore() {
        mTaskDetailView.showScore(task.getId());
    }

    @Override
    public void showUpdateTempo() {
        mTaskDetailView.showUpdateTempo(task.getId(),task.getStep_tempo());
    }

    @Override
    public void loadLog() {

        mRepository.getLogs(context, task.getId(), new GsonResponseHandler<RowsNoPage<Logs>>() {
            @Override
            public void onSuccess(int statusCode, RowsNoPage<Logs> response) {
                List<Logs> logs;
                logs = response.rows;
                processLogs(logs);
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"获取任务日志失败");
            }
        });
    }

    private void processLogs(List<Logs> logs) {
        mTaskDetailView.setLogs(logs);
    }

    @Override
    public void showProcess() {
        mTaskDetailView.showProcess(task.getId());
    }
}
