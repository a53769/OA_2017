package com.example.shixi_a.myapplication.bean;

import java.io.Serializable;

/**
 * Created by Shixi-A on 2017/6/8.
 */

public class Classify implements Serializable {
    public String id;
    public String target_name;
    public String memo;
    public String in_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTarget_name() {
        return target_name;
    }

    public void setTarget_name(String target_name) {
        this.target_name = target_name;
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
