package com.example.shixi_a.myapplication.bean;

/**
 * Created by a5376 on 2017/6/28.
 */

public class Logs {//这是任务日志 早知道写成内部类了
    String user_name;
    String action_show;
    String tempo;
    String memo;
    String in_time;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAction_show() {
        return action_show;
    }

    public void setAction_show(String action_show) {
        this.action_show = action_show;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getIn_time() {
        return in_time;
    }

    public void setIn_time(String in_time) {
        this.in_time = in_time;
    }
}
