package com.example.shixi_a.myapplication;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDexApplication;

import com.example.shixi_a.myapplication.bean.Vathome;
import com.example.shixi_a.myapplication.service.DemoIntentService;
import com.example.shixi_a.myapplication.service.DemoPushService;
import com.igexin.sdk.PushManager;

/**
 * Created by Shixi-A on 2017/5/31.
 */

public class GlobalApp extends MultiDexApplication {

    private static GlobalApp instance;

    private SharedPreferences sp;//缓存用户名与密码实现自动登录
    private SharedPreferences.Editor editor;

    public String user_token;
    public String vathome_token;
    public String userName;

    public static Vathome vathome;

    public static GlobalApp getInstance(){
        return instance;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        //初始化推送Client
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        // DemoIntentService 为第三方自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);



        setUser_token(null); // 初始化全局变量
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        instance = this;
    }

    public String getUser_token() {
        if(user_token == null)
            return sp.getString("token","");
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public String getVathome_token() {
        if(user_token == null)
            return sp.getString("token2","");
        return vathome_token;
    }

    public void setVathome_token(String vathome_token) {
        this.vathome_token = vathome_token;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        if(userName==null)
            return sp.getString("name","");
        return userName;
    }

    public static Vathome getVathome() {
        return vathome;
    }

    public static void setVathome(Vathome vathome) {
        GlobalApp.vathome = vathome;
    }
}
