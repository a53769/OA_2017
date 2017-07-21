package com.example.shixi_a.myapplication.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.LauncherApps;
import android.preference.PreferenceManager;

import com.example.myokhttp.MyOkHttp;
import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.myokhttp.util.RequestParams;
import com.example.shixi_a.myapplication.GlobalApp;
import com.example.shixi_a.myapplication.util.LogUtils;

import java.util.Map;

import static com.example.shixi_a.myapplication.util.RequestUtils.getSecretKey;
import static com.example.shixi_a.myapplication.util.RequestUtils.getTimeStamp;

/**
 * Created by Shixi-A on 2017/6/2.
 */

public abstract class BaseModel implements IBaseModel {

    public SharedPreferences sp;//缓存用户名与密码实现自动登录
    public SharedPreferences.Editor editor;
    public RequestParams params;


    protected void initRequest(Context context, String opt){
        String token = GlobalApp.getInstance().getUser_token();
        if(token==null||token=="") {
            LogUtils.e("token 为空");
            sp = PreferenceManager.getDefaultSharedPreferences(context);
            sp.edit().putBoolean("main",false);

        }

        String time = getTimeStamp();
        String secret_key = getSecretKey(token, opt, time);

        params = new RequestParams();

        /**
         * 必要传参
         */
        params.put("token", token);
        params.put("opt", opt);
        params.put("timestamp",time);
        params.put("secret_key",secret_key);
        params.put("member_id","1");
    }

    protected void InitRequest(Context context, String opt) {
        String token = GlobalApp.getInstance().getVathome_token();
        if(token==null||token=="") {
            LogUtils.e("token 为空");
            sp = PreferenceManager.getDefaultSharedPreferences(context);
            sp.edit().putBoolean("main",false);

        }

        String time = getTimeStamp();
        String secret_key = getSecretKey(token, opt, time);

        params = new RequestParams();

        /**
         * 必要传参
         */
        params.put("token", token);
        params.put("timestamp",time);
        params.put("opt",opt);
        params.put("secret_key",secret_key);
        params.put("member_id","1");
    }

    protected void initRequest2(Context context) {
        String token = GlobalApp.getInstance().getVathome_token();
        if(token==null||token=="") {
            LogUtils.e("token 为空");
            sp = PreferenceManager.getDefaultSharedPreferences(context);
            sp.edit().putBoolean("main",false);

        }

        params = new RequestParams();

        /**
         * 必要传参
         */
        params.put("token", token);

    }

    /**
     * 发送http get 请求
     *
     * @param context
     * @param url
     * @param callback
     */
    protected void sendGetRequest(Context context, String url, LauncherApps.Callback callback)
    {
// HttpRequestUtil.getInstance().getRequest(context, getAbsUrl(url), callback);
    }


    /**
     * 发送http post 请求
     *
     * @param context
     * @param url
     * @param params
     * @param callback
     */
    protected void sendPostRequest(Context context, String url, RequestParams params, GsonResponseHandler callback)
    {
        MyOkHttp.get().post(context, url, params.toParams(), callback);
    }


    protected void sendPostRequest(Context context, String url, RequestParams params, JsonResponseHandler callback)
    {
        MyOkHttp.get().post(context, url, params.toParams(), callback);
    }

    protected void sendPostRequest(Context context, String url, Map<String,String> params, JsonResponseHandler callback)
    {
        MyOkHttp.get().post(context, url, params, callback);
    }
    /**
     *
     * 测试用
     */

    protected void sendPostRequest(Context context, String url, RequestParams params, RawResponseHandler callback)
    {
        MyOkHttp.get().post(context, url, params.toParams(), callback);
    }



}
