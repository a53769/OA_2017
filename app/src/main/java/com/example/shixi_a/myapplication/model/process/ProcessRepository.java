package com.example.shixi_a.myapplication.model.process;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.model.BaseModel;

import java.util.List;


/**
 * Created by Shixi-A on 2017/6/12.
 */

public class ProcessRepository extends BaseModel implements ProcessDataSource {
    @Override
    public void getProcesses(Context context, String taskId, GsonResponseHandler callback) {

        initRequest(context, "rows");

        /**
         * 选择传参
         */
        params.put("task_id",taskId);

        sendPostRequest(context, BASEURL + GETPROCESSLIST , params, callback);
    }

    @Override
    public void submitProcess(Context context, String taskId, String title, String content, List<String> user_id, String estime, String father_id, JsonResponseHandler callback) {

        initRequest(context, "add");

        params.put("task_id",taskId);
        params.put("title",title);
        params.put("description",content);
        params.put("estimate_etime",estime);
        params.put("father_id",father_id);
        params.put("attachments","");
        params.put("remind_min","");
        putArrayparams(user_id);

        sendPostRequest(context, BASEURL + ADDPROCESS, params, callback);

    }

    private void putArrayparams(List<String> user_id) {
        for(int i = 0; i < user_id.size(); i++){
            String temp = "user_ids[" + i + "]";
            params.put(temp,user_id.get(i));
        }
    }

    @Override
    public void delProcess(Context context, String step_id, RawResponseHandler callback) {
        initRequest(context,"delete");

        params.put("step_id",step_id);

        sendPostRequest(context, BASEURL + DELETEPROCESS , params, callback);
    }

    @Override
    public void editProcess(Context context, String tempId, String title, List<String> mList, String etime, String content, JsonResponseHandler callback) {
        initRequest(context, "edit");

        params.put("title",title);
        params.put("description",content);
        params.put("estimate_etime",etime);
        params.put("step_id",tempId);
        putArrayparams(mList);

        sendPostRequest(context, BASEURL + EDITPROCESS, params, callback);
    }
}
