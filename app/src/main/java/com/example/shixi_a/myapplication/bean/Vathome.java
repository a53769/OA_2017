package com.example.shixi_a.myapplication.bean;

/**
 * Created by Shixi-A on 2017/6/5.
 */

public class Vathome {
    public boolean rt;
    public String error;
    public String landing_server_time;
    public Storage storage;
    public String token;

    public boolean isRt() {
        return rt;
    }

    public void setRt(boolean rt) {
        this.rt = rt;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getLanding_server_time() {
        return landing_server_time;
    }

    public void setLanding_server_time(String landing_server_time) {
        this.landing_server_time = landing_server_time;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class Storage{
        public String host;
        public String access_key;
        public String access_secret;
        public String bucket;
    }
}
