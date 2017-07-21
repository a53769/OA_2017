package com.example.shixi_a.myapplication.bean;

/**
 * Created by Shixi-A on 2017/6/2.
 */

public class User {
    public String id;
    public String name;
    public String admin_name_show;
    public String email;
    public String mobile;
    public Storge storge;
    public String token;


    // default is no login
    private boolean isLogin = false;

    // single instance for login
    static private User user = null;

    static public User getInstance () {
        if (User.user == null) {
            User.user = new User();
        }
        return User.user;
    }

    //防止直接实例化
    public User() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdmin_name_show() {
        return admin_name_show;
    }

    public void setAdmin_name_show(String admin_name_show) {
        this.admin_name_show = admin_name_show;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Storge getStorge() {
        return storge;
    }

    public void setStorge(Storge storge) {
        this.storge = storge;
    }

    public static class Storge{
        String host;            //"s-api.yunvm.com"
        String access_key;      //"vangencdn"
        String access_secret;   //"oomNTBL5VLzqwYIOL0GtVmRA3m7KuoBEGK4coQFQ"
        String bucket;          //"vathome"

//        public Storge(String host, String access_key, String access_secret, String bucket) {
//            this.host = host;
//            this.access_key = access_key;
//            this.access_secret = access_secret;
//            this.bucket = bucket;
//        }
    }
}
