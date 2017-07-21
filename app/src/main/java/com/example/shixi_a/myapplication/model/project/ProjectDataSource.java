package com.example.shixi_a.myapplication.model.project;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;

/**
 * Created by Shixi-A on 2017/6/7.
 */

public interface ProjectDataSource {

    void getProjects(Context context, GsonResponseHandler callback);

    void editProject(Context context, String id, String description, RawResponseHandler callback);

    void addProject(Context context, String title, String description, RawResponseHandler callback);
}
