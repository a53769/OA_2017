package com.example.shixi_a.myapplication.model.task;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.bean.Assessment;
import com.example.shixi_a.myapplication.bean.Logs;
import com.example.shixi_a.myapplication.bean.MileStone;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.bean.Task;

/**
 * Created by Shixi-A on 2017/6/5.
 */

public interface TaskDataSource {

    void getTasks(Context context, String user_id, String create_id, String status, GsonResponseHandler callback);//获取任务列表

    void getTaskId(Context context, JsonResponseHandler callback);//获取任务ID

    void getPrioritys(Context context, JsonResponseHandler callback);

    void deleteTask(Context context, String id, RawResponseHandler callback);

    void submit(Context context, String taskId, String task_id, String project_id, String title, String target_types, String priority, String estimate_stime, String target_desc, String exec_method, String memo, JsonResponseHandler callback);

    void editTask(Context context, String taskId, String proId, String title, String typeId, String priorityId, String goal, String method, String memo, RawResponseHandler rawResponseHandler);

    void updateTempo(Context context, String s, String progress, String taskId, RawResponseHandler callback);

    void getTask(Context context, String taskId, GsonResponseHandler<Task> gsonResponseHandler);

    void getLogs(Context context, String id, GsonResponseHandler<RowsNoPage<Logs>> callback);

    void getAssessInfo(Context context, String id, GsonResponseHandler<Assessment> callback);

    void dealTask(Context context, String id, String action,String turn_id, RawResponseHandler rawResponseHandler);

    void acceptanceTask(Context context, String id, String action, String turnId, RawResponseHandler rawResponseHandler);

    void evaluateTask(Context context, String taskId, String s, String s1, String s2, String memo, RawResponseHandler rawResponseHandler);

    void getMilestone(Context context, String proId, GsonResponseHandler<RowsNoPage<MileStone>> gsonResponseHandler);
}
