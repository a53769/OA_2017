package com.example.shixi_a.myapplication.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Shixi-A on 2017/6/5.
 */

public class Task implements Serializable{

    public String id;
    public String title;
    public String target_desc;
    public String exec_method;
    public String project_id;
    public String milestone_id;
    public String milestone_name;
    public String project_name;
    public List<String> target_types;
    public Map<String, String> target_type_shows;
    public String priority;
    public String priority_show;
    public String estimate_stime;
    public String memo;

    public String creater_name;
    public String create_time;

    public String status;

    public String step_tempo;

    public List<String> opts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTarget_desc() {
        return target_desc;
    }

    public void setTarget_desc(String target_desc) {
        this.target_desc = target_desc;
    }

    public String getExec_method() {
        return exec_method;
    }

    public void setExec_method(String exec_method) {
        this.exec_method = exec_method;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public List<String> getTarget_types() {
        return target_types;
    }

    public void setTarget_types(List<String> target_types) {
        this.target_types = target_types;
    }

    public Map<String, String> getTarget_type_shows() {
        return target_type_shows;
    }

    public void setTarget_type_shows(Map<String, String> target_type_shows) {
        this.target_type_shows = target_type_shows;
    }

    public String getMilestone_id() {
        return milestone_id;
    }

    public void setMilestone_id(String milestone_id) {
        this.milestone_id = milestone_id;
    }

    public String getMilestone_name() {
        return milestone_name;
    }

    public void setMilestone_name(String milestone_name) {
        this.milestone_name = milestone_name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPriority_show() {
        return priority_show;
    }

    public void setPriority_show(String priority_show) {
        this.priority_show = priority_show;
    }

    public String getEstimate_stime() {
        return estimate_stime;
    }

    public void setEstimate_stime(String estimate_stime) {
        this.estimate_stime = estimate_stime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreater_name() {
        return creater_name;
    }

    public void setCreater_name(String creater_name) {
        this.creater_name = creater_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStep_tempo() {
        return step_tempo;
    }

    public void setStep_tempo(String step_tempo) {
        this.step_tempo = step_tempo;
    }

    public List<String> getOpts() {
        return opts;
    }

    public void setOpts(List<String> opts) {
        this.opts = opts;
    }
}
