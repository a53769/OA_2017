package com.example.shixi_a.myapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Shixi-A on 2017/6/12.
 */

public class Process implements Serializable{
    public String task_id;
    public String step_id;
    public String title;
    public String creater_name;
    public String user_id;
    public String user_name;
    public String estimate_stime;
    public String estimate_etime;
    public String actual_stime;
    public String actual_etime;
    public String remind_min;
    public String description;
    public String status_show;
    public String tempo;
    public int is_done;
    public List<String> opts;



    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getStep_id() {
        return step_id;
    }

    public void setStep_id(String step_id) {
        this.step_id = step_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreater_name() {
        return creater_name;
    }

    public void setCreater_name(String creater_name) {
        this.creater_name = creater_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEstimate_stime() {
        return estimate_stime;
    }

    public void setEstimate_stime(String estimate_stime) {
        this.estimate_stime = estimate_stime;
    }

    public String getEstimate_etime() {
        return estimate_etime;
    }

    public void setEstimate_etime(String estimate_etime) {
        this.estimate_etime = estimate_etime;
    }

    public String getActual_stime() {
        return actual_stime;
    }

    public void setActual_stime(String actual_stime) {
        this.actual_stime = actual_stime;
    }

    public String getActual_etime() {
        return actual_etime;
    }

    public void setActual_etime(String actual_etime) {
        this.actual_etime = actual_etime;
    }

    public String getRemind_min() {
        return remind_min;
    }

    public void setRemind_min(String remind_min) {
        this.remind_min = remind_min;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus_show() {
        return status_show;
    }

    public void setStatus_show(String status_show) {
        this.status_show = status_show;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public int getIs_done() {
        return is_done;
    }

    public void setIs_done(int is_done) {
        this.is_done = is_done;
    }

    public List<String> getOpts() {
        return opts;
    }

    public void setOpts(List<String> opts) {
        this.opts = opts;
    }
}
