package com.example.shixi_a.myapplication.bean;

import java.io.Serializable;

/**
 * Created by Shixi-A on 2017/6/7.
 */

public class Project implements Serializable {

    public String id;
    public String project_name;//项目名称
    public String description;
    public String in_time;//创建时间
    public String user_name;//用户姓名

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIn_time() {
        return in_time;
    }

    public void setIn_time(String in_time) {
        this.in_time = in_time;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
