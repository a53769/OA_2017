package com.example.shixi_a.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.NotificationCompat;

import com.example.shixi_a.myapplication.bean.Vathome;
import com.example.shixi_a.myapplication.home.MainActivity;
import com.example.shixi_a.myapplication.service.DemoIntentService;
import com.example.shixi_a.myapplication.service.DemoPushService;
import com.igexin.sdk.PushManager;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

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

    public static int count = 0;

    public Vathome vathome;

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

        //初始化Google Zxing框架扫描二维码
        ZXingLibrary.initDisplayOpinion(this);

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

    public String getVathomeRt() {
        if(vathome == null)
            return sp.getString("vathomert","0");
        return String.valueOf(vathome.rt);
    }

    public Vathome getVathome(){
        return this.vathome;
    }

    public void setVathome(Vathome vathome) {
        this.vathome = vathome;
    }

    public void send(String data, String id) {
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("通知")//设置通知栏标题
                .setContentText(data)
                .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL)) //设置通知栏点击意图
//	            .setNumber(number) //设置通知集合的数量
                .setTicker("您有新的消息，请注意查收")
                .setFullScreenIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL),false)
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setPriority(Notification.PRIORITY_MAX) //设置该通知优先级
                .setVisibility(Notification.VISIBILITY_PUBLIC)
//                .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                .setSmallIcon(R.drawable.push);//设置通知小ICON

        Notification notification = mBuilder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        nm.notify(count++,notification);
    }

    public PendingIntent getDefalutIntent(int flags){
        Intent intent = new Intent(this, MainActivity.class);
        return PendingIntent.getActivity(this, 1, intent, flags);
    }
}
