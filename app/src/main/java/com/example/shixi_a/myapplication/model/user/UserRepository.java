package com.example.shixi_a.myapplication.model.user;


import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.myokhttp.util.RequestParams;
import com.example.shixi_a.myapplication.GlobalApp;
import com.example.shixi_a.myapplication.bean.UserInfo;
import com.example.shixi_a.myapplication.model.BaseModel;

import static com.example.shixi_a.myapplication.util.RequestUtils.getCode;
import static com.example.shixi_a.myapplication.util.RequestUtils.getTimeStamp;

/**
 * Created by Shixi-A on 2017/6/2.
 */

public class UserRepository extends BaseModel implements UserDataSource {


    @Override
    public void logout(Context context, RawResponseHandler callback) {

        String token = GlobalApp.getInstance().getUser_token();
        params = new RequestParams();
        params.put("token", token);

        sendPostRequest(context,BASE + LOGOUT,params,callback);


    }

    @Override
    public void userLogin(Context context, String name, String pwd, String vcode, GsonResponseHandler callBack) {

        String time = getTimeStamp();
        String code = getCode(name, pwd, time);

        RequestParams params = new RequestParams();
        params.put("admin_name", name);
        params.put("timestamp", time);
        params.put("code", code);
        params.put("vcode",vcode);

        sendPostRequest(context, BASE + LOGIN , params, callBack);
    }

    @Override
    public void getUsers(Context context, GsonResponseHandler callback) {
        InitRequest(context,"rows");
        sendPostRequest(context,BASE + GETLISTUSER,params,callback);

    }

    @Override
    public void getUser(Context context, GsonResponseHandler<UserInfo> callback) {
        initRequest2(context);
        params.put("admin_name",GlobalApp.getInstance().getUserName());

        sendPostRequest(context,BASE + USERINFO ,params,callback);
    }

    @Override
    public void checkName(Context context, String name, JsonResponseHandler callback) {
        RequestParams params = new RequestParams();
        params.put("opt","login_admin_check");
        params.put("login_admin",name);
        sendPostRequest(context,BASE + LOGINCHECK,params,callback);
    }

    @Override
    public void getVcode(Context context, String name, JsonResponseHandler jsonResponseHandler) {
        RequestParams params = new RequestParams();
        params.put("opt","send_vcode");
        params.put("login_admin",name);
        sendPostRequest(context,BASE + LOGINCHECK,params,jsonResponseHandler);
    }


}
