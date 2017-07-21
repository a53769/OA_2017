package com.example.shixi_a.myapplication.model.project;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.model.BaseModel;

/**
 * Created by Shixi-A on 2017/6/7.
 */

public class ProjectRepository extends BaseModel implements ProjectDataSource {
    @Override
    public void getProjects(Context context, GsonResponseHandler callback) {

        initRequest(context, "rows");

        /**
         * 选择传参
         */
        params.put("page_num","1");
        params.put("per_page","25");

        sendPostRequest(context, BASEURL + GETPROJECTLIST , params, callback);
    }

    @Override
    public void editProject(Context context, String id, String description, RawResponseHandler callback) {
        initRequest(context, "edit");

        params.put("project_id",id);
        params.put("description",description);

        sendPostRequest(context, BASEURL + EDITPROJECT, params, callback);
    }

    @Override
    public void addProject(Context context, String title, String description, RawResponseHandler callback) {
        initRequest(context ,"add");

        params.put("project_name",title);
        params.put("description",description);

        sendPostRequest(context, BASEURL + ADDPROJECT, params, callback);
    }

    public void deletProject(Context context, String id, RawResponseHandler callback) {
        initRequest(context, "del");
        params.put("project_id", id);

        sendPostRequest(context, BASEURL + DELETEPROJECT, params, callback);
    }
}
