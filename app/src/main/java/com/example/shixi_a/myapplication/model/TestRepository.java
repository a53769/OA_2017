package com.example.shixi_a.myapplication.model;

import android.content.Context;
import android.preference.PreferenceManager;

import com.example.myokhttp.response.RawResponseHandler;
import com.example.myokhttp.util.RequestParams;
import com.example.shixi_a.myapplication.GlobalApp;
import com.example.shixi_a.myapplication.util.LogUtils;

import static com.example.shixi_a.myapplication.util.RequestUtils.getSecretKey;
import static com.example.shixi_a.myapplication.util.RequestUtils.getTimeStamp;

/**
 * Created by Shixi-A on 2017/6/8.
 */

public class TestRepository extends BaseModel{

    public void goTest(Context context, RawResponseHandler callBack) {

        String token = GlobalApp.getInstance().getUser_token();
        if(token==null||token=="") {
            LogUtils.e("token 为空");
            sp = PreferenceManager.getDefaultSharedPreferences(context);
            sp.edit().putBoolean("main",false);

        }

        String opt = "users";
        String time = getTimeStamp();
        String secret_key = getSecretKey(token, opt, time);

        RequestParams params = new RequestParams();

        /**
         * 必要传参
         */
        params.put("token", token);
        params.put("opt", opt);
        params.put("timestamp",time);
        params.put("secret_key",secret_key);

        /**
         * 选择传参
         */
        params.put("member_id","1");


        sendPostRequest(context, BASEURL + GETUPARTANDUSERLIST , params, callBack);
    }
}
