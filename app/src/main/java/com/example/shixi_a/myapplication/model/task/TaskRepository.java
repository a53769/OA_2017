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
import com.example.shixi_a.myapplication.model.BaseModel;

/**
 * Created by Shixi-A on 2017/6/5.
 */

public class TaskRepository extends BaseModel implements TaskDataSource {

    @Override
    public void getTasks(Context context, String user_id, String creater_id, String status, GsonResponseHandler callback) {

        initRequest(context, "rows");

        params.put("user_id", user_id);
        params.put("creater_id", creater_id);
        params.put("filter_status", status);
        params.put("page_num","1");
        params.put("per_page","50");
        params.put("priority","");
        params.put("project_id","");
        params.put("step_status","");
        params.put("target_type","");
        params.put("status","");
//        params.put("filter_status","");

        sendPostRequest(context, BASEURL + GETTASKLIST , params, callback);

    }

    @Override
    public void getTaskId(Context context, JsonResponseHandler callback) {
        initRequest(context , "add");

        sendPostRequest(context, BASEURL + ADDTASK, params, callback);
    }

    @Override
    public void getPrioritys(Context context, JsonResponseHandler callback) {
        initRequest(context, "priority");

        sendPostRequest(context, BASEURL + GETPRIORITY , params, callback);
    }//优先级的数据量太少没有必要新建实体类

    @Override
    public void submit(Context context,String task_id, String project_id, String title, String target_types, String stoneId,  String priority, String estimate_stime, String target_desc, String exec_method, String memo, JsonResponseHandler callback) {
        initRequest(context,"publish");
        params.put("task_id", task_id);
        params.put("project_id",project_id);
        params.put("milestone_id",stoneId);
        params.put("title",title);
        params.put("target_types[0]",target_types);
        params.put("priority",priority);
        params.put("estimate_stime",estimate_stime);
        params.put("target_desc",target_desc);
        params.put("exec_method",exec_method);
        params.put("memo",memo);

        sendPostRequest(context, BASEURL + UPTASK, params, callback);

    }

    @Override
    public void deleteTask(Context context, String id, RawResponseHandler callback) {
        initRequest(context,"cancel");

        params.put("task_id",id);

        sendPostRequest(context, BASEURL + DELETETASK, params, callback);
    }

    @Override
    public void editTask(Context context, String taskId, String proId, String title, String typeId, String priorityId, String goal, String method, String memo, RawResponseHandler callback) {
        initRequest(context,"edit");

        params.put("task_id", taskId);
        params.put("project_id",proId);
        params.put("title",title);
        params.put("target_types[0]",typeId);
        params.put("priority",priorityId);
        params.put("target_desc",goal);
        params.put("exec_method",method);
        params.put("memo",memo);

        sendPostRequest(context,BASEURL + EDITTASK,params,callback);
    }

    @Override
    public void updateTempo(Context context, String taskId, String progress, String memo, RawResponseHandler callback) {
        initRequest(context,"refresh");

        params.put("task_id",taskId);
        params.put("tempo",progress);
        params.put("log",memo);

        sendPostRequest(context,BASEURL + UPDATETASKSTATE,params,callback);
    }

    @Override
    public void getTask(Context context, String taskId, GsonResponseHandler<Task> callback) {
        initRequest(context,"row");

        params.put("task_id",taskId);

        sendPostRequest(context,BASEURL + GETTASKDETAIL ,params , callback);
    }

    @Override
    public void getLogs(Context context, String id, GsonResponseHandler<RowsNoPage<Logs>> callback) {
        initRequest(context,"logs");

        params.put("task_id", id);
        params.put("per_page","25");
        params.put("page_num","1");
        params.put("step_id","");

        sendPostRequest(context,BASEURL + GETTASKLOGLIST , params, callback);
    }

    @Override
    public void getAssessInfo(Context context, String id, GsonResponseHandler<Assessment> callback) {
        initRequest(context,"get_score");

        params.put("ref","task");
        params.put("ref_id",id);

        sendPostRequest(context,BASEURL + GETASSESSINFO,params, callback);
    }

    @Override
    public void dealTask(Context context, String id, String action, String turn_id, JsonResponseHandler callback) {
        initRequest(context,"deal");

        params.put("task_id",id);
        params.put("ac", action);
        params.put("turn_id",turn_id);

        params.put("memo","");
        sendPostRequest(context,BASEURL + HANDLEPROCESS,params,callback);

    }

    @Override
    public void acceptanceTask(Context context, String id, String action, String turnId, JsonResponseHandler callback) {
        initRequest(context,"acceptance");

        params.put("task_id",id);
        params.put("ac",action);
        params.put("turn_id",turnId);
        params.put("memo","");

        sendPostRequest(context,BASEURL + CHECKTASK,params ,callback);
    }

    @Override
    public void evaluateTask(Context context, String taskId, String s, String s1, String s2, String memo,RawResponseHandler callback) {
        initRequest(context,"evaluate");

        params.put("task_id",taskId);
        params.put("quality",s);
        params.put("efficient",s1);
        params.put("attitude",s2);
        params.put("memo",memo);

        sendPostRequest(context,BASEURL + ASSESSTASK ,params,callback);
    }

    @Override
    public void getMilestone(Context context, String proId, GsonResponseHandler<RowsNoPage<MileStone>> gsonResponseHandler) {
        initRequest(context,"rows");

        params.put("project_id",proId);
        params.put("per_page","25");
        params.put("page_num","1");

        sendPostRequest(context,BASEURL + GETMILESTONELIST, params, gsonResponseHandler);
    }
}
