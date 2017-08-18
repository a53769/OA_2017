package com.example.shixi_a.myapplication.home.work.workTask.taskAddEdit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.bean.MileStone;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.bean.Task;
import com.example.shixi_a.myapplication.home.work.workTask.classify.ClassifysActivity;
import com.example.shixi_a.myapplication.home.work.workTask.milestone.MileStoneActivity;
import com.example.shixi_a.myapplication.model.task.TaskRepository;
import com.example.shixi_a.myapplication.home.work.workTask.projects.ProjectsActivity;
import com.example.shixi_a.myapplication.util.LogUtils;
import com.example.shixi_a.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by Shixi-A on 2017/6/9.
 */

public class TaskAddEditPresenter implements TaskAddEditContract.Presenter {

    private TaskAddEditContract.View mTaskAddView;
    private TaskRepository mTaskRepository;
    private String taskId;
    private String proId;
    private String typeId;
    private String priorityId;
    private String stoneId;
    private Context context;
    private Task mTask;

    private boolean FIRST = true;

    private Map<String, String> valueMap;

    public TaskAddEditPresenter(Task task, @Nullable TaskRepository taskRepository, @NonNull TaskAddEditContract.View addTaskView, Context context) {

        mTaskRepository = checkNotNull(taskRepository);
        mTaskAddView = checkNotNull(addTaskView);
        //mIsDataMissing = shouldLoadDataFromRepo;
        mTaskAddView.setPresenter(this);
        this.context = context;
        mTask = task;
    }
    @Override
    public void start() {
        if(isNewTask()) {
            loadTaskId();
        }else{
            loadTask();
        }
        loadPrioeitys();
    }

    public boolean isNewTask() {
        return mTask == null;
    }

    private void loadTask() {
        mTaskAddView.initView(mTask.getTitle(),mTask.getTarget_desc(),mTask.getExec_method(),
                mTask.getEstimate_stime(),mTask.getPriority_show(),mTask.getMemo());
        if(FIRST){
            mTaskAddView.setText( mTask.getProject_name(),getType(mTask.getTarget_type_shows()),mTask.getMilestone_name());
            proId = mTask.getProject_id();
            typeId = mTask.getTarget_types().get(0);
            stoneId = mTask.getMilestone_id();
            priorityId = mTask.getPriority();
            FIRST = false;
        }
        taskId = mTask.getId();
    }

    private String getType(Map<String, String> map) {
        Iterator<String> keyIter= map.keySet().iterator();
        String key;
        String value ;
        key = keyIter.next();
        value =  map.get(key);
        return value;
    }


    @Override
    public void result(int requestCode, int resultCode, Intent data) {
        // If a mTask was successfully added, show snackbar
        if (ProjectsActivity.REQUEST_PRO_CODE == requestCode && Activity.RESULT_OK == resultCode) {
            String proName = data.getStringExtra("name");
            proId = data.getStringExtra("id");
            mTaskAddView.setProName(proName);
        }
        else if (ClassifysActivity.REQUEST_CLA_CODE == requestCode && Activity.RESULT_OK == resultCode) {
            String claName = data.getStringExtra("name");
            typeId = data.getStringExtra("id");
            mTaskAddView.setClaName(claName);
        }else if(MileStoneActivity.REQUEST_MILE_CODE == requestCode && Activity.RESULT_OK == resultCode){
            String stoneName = data.getStringExtra("name");
            stoneId = data.getStringExtra("id");
            mTaskAddView.setStoneName(stoneName);
        }
    }

    private void loadPrioeitys() {
        mTaskRepository.getPrioritys(context, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                JSONObject jsonObject ;
                try {
                    jsonObject = new JSONObject(response.getString("rows"));

                    Iterator<String> keyIter= jsonObject.keys();
                    String key;
                    String value ;
                    valueMap = new LinkedHashMap<String, String>();
                    while (keyIter.hasNext()) {
                        key = keyIter.next();
                        value = (String) jsonObject.get(key);
                        valueMap.put(key, value);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    LogUtils.e(e.toString());
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogUtils.e("获取优先级列表失败");
            }
        });
    }

    private void loadTaskId() {
        mTaskRepository.getTaskId(context, new JsonResponseHandler() {

            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    taskId = response.getString("task_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                LogUtils.v("Monitor:","获取ID成功   " + taskId);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogUtils.v("Monitor:","获取ID失败");
            }
        });
    }

    @Override
    public void showProjects() {
        mTaskAddView.showProjects();
    }

    @Override
    public void showPrioritys() {
        mTaskAddView.showPrioritys(valueMap);
    }

    @Override
    public void showClassifys() {
        mTaskAddView.showClassifys();
    }

    @Override
    public void showProcesses() {
        mTaskAddView.showProcesses(taskId);
    }

    @Override
    public void submit(String title, String goal, String method, String stime, String memo) {
        if(proId == null || proId.length() == 0){
            ToastUtils.showShort(context,"请选择项目");
        }else if(typeId == null || typeId.length() == 0){
            ToastUtils.showShort(context,"请选择分类");
        } else if(priorityId == null){
            ToastUtils.showShort(context,"请选择优先级");
        }else if(stoneId == null) {
            ToastUtils.showShort(context,"请选择里程碑");
        }else if(isNewTask()) {
            mTaskRepository.submit(context, taskId, proId, title, typeId,stoneId
                    , priorityId, stime, goal, method, memo, new JsonResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                            if(response.getString("rt").equals("false")) {
                                ToastUtils.showShort(context, response.getString("error"));
                            }else{
                                ToastUtils.showShort(context,"创建成功");
                                mTaskAddView.showTasks();
                            }
                        }
                        @Override
                        public void onFailure(int statusCode, String error_msg) {
                            ToastUtils.showShort(context, "创建失败");
                        }
                    });
        }else{
            if(priorityId == null)
                priorityId = mTask.getPriority();
            mTaskRepository.editTask(context,taskId,proId,title,typeId,priorityId,goal,method,memo, new RawResponseHandler() {
                @Override
                public void onSuccess(int statusCode, String response) {
                    ToastUtils.showShort(context,"修改成功");
                    mTaskAddView.showTasks();
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    ToastUtils.showShort(context,"修改失败");
                }
            });
        }
    }

    @Override
    public void showMilestone() {
        if(proId == null){
            ToastUtils.showShort(context,"请先选择项目");
        }else {
            mTaskRepository.getMilestone(context,proId, new GsonResponseHandler<RowsNoPage<MileStone>>() {
                @Override
                public void onSuccess(int statusCode, RowsNoPage<MileStone> response) {
                    if(response.rows.size()==0){
                        ToastUtils.showShort(context,"该项没有里程碑");
                        stoneId = "";
                    }else{
                        mTaskAddView.showMileStone(proId);
                    }
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    ToastUtils.showShort(context,"获取失败");
                }
            });
        }
    }

    @Override
    public void setPriority(String priorityId) {
        this.priorityId = priorityId;
    }


}
