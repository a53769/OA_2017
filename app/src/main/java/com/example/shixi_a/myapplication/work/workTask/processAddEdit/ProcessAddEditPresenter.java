package com.example.shixi_a.myapplication.work.workTask.processAddEdit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Process;
import com.example.shixi_a.myapplication.work.workTask.contacts.ContactsActivity;
import com.example.shixi_a.myapplication.model.process.ProcessRepository;
import com.example.shixi_a.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by a5376 on 2017/6/16.
 */

public class ProcessAddEditPresenter implements ProcessAddEditContract.Presenter {

    private String taskId;

    private List<String> mList;

    private ProcessRepository mProcessRepository;

    private ProcessAddEditContract.View mProcessAddView;

    private Context context;

    private String tempId;

    private Process mProcess;

    private String time;

    private boolean FIRST = true;

    public ProcessAddEditPresenter(Process process, @NonNull ProcessRepository processRepository, @NonNull ProcessAddEditFragment processAddEditFragment, Context context, String taskId, String tempId,String time){
        mProcessRepository = checkNotNull(processRepository, "processRepository cannot be null");

        mProcessAddView = checkNotNull(processAddEditFragment,"processAddEditFragment cannot be null");

        mProcessAddView.setPresenter(this);

        mProcess = process;

        this.context = context;

        this.taskId = taskId;

        this.tempId = tempId;

        this.time = time;
    }

    public ProcessAddEditPresenter(Process process, ProcessRepository processRepository, ProcessAddEditFragment processAddEditFragment, Context context, String taskId) {
        mProcessRepository = checkNotNull(processRepository, "processRepository cannot be null");

        mProcessAddView = checkNotNull(processAddEditFragment,"processAddEditFragment cannot be null");

        mProcessAddView.setPresenter(this);

        mProcess = process;

        this.context = context;

        this.taskId = taskId;
    }

    @Override
    public void start() {
        if(!isNewProcess()){
            onProcessLoad();
        }else{
            mProcessAddView.setTime(time);
        }
    }

    private void onProcessLoad() {
        mProcessAddView.setTitle(mProcess.getTitle());
        mProcessAddView.setContent(mProcess.getDescription());
        mProcessAddView.setTime(mProcess.getEstimate_stime(),mProcess.getEstimate_etime());
        mProcessAddView.setEdit();
        if(FIRST) {
            mProcessAddView.setExecutor(mProcess.getUser_name());
            mList = new ArrayList<>();
            mList.add(mProcess.getUser_id());
            FIRST = false;
        }
        tempId = mProcess.getStep_id();
    }

    @Override
    public void result(int requestCode, int resultCode, Intent data) {
        if (ContactsActivity.REQUEST_CONTACTS_CODE == requestCode && Activity.RESULT_OK == resultCode) {
            Bundle bundle = data.getExtras();
            mList = (List<String>) bundle.getSerializable("list");
            String name = data.getStringExtra("name");
            mProcessAddView.setExecutor(name);
        }
    }

    @Override
    public String getTime() {
        return time;
    }

    public boolean isNewProcess() {
        return mProcess == null;
    }
    @Override
    public void submit(String title, String content, String etime) {

        if(mList == null || mList.size() == 0){
            ToastUtils.showShort(context,"请选择执行人");
        }else if(isNewProcess()) {
            mProcessRepository.submitProcess(context, taskId, title, content, mList, etime, tempId, new JsonResponseHandler() {
                @Override
                public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                    if (response.getString("rt") == "false") {
                        ToastUtils.showShort(context, response.getString("error"));
                    } else {
                        tempId = response.getString("step_id");//已经从流程列表中获取，这里获取服务器返回可以进行继续添加的操作（暂时取消）
                        mProcessAddView.showProcesses();
                    }
                }
                @Override
                public void onFailure(int statusCode, String error_msg) {
                    ToastUtils.showLong(context, error_msg);
                }
            });
        }else{
            mProcessRepository.editProcess(context,mProcess.getStep_id(), title, mList, etime, content, new JsonResponseHandler() {
                @Override
                public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                    if(response.getBoolean("rt")){
                        ToastUtils.showShort(context, "修改成功");
                        mProcessAddView.showProcesses();
                    }else{
                        ToastUtils.showShort(context, response.getString("error"));
                    }
                }
                @Override
                public void onFailure(int statusCode, String error_msg) {
                    ToastUtils.showShort(context, "修改失败");
                }
            });
        }
    }


}
