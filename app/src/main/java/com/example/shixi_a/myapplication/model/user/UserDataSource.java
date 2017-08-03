package com.example.shixi_a.myapplication.model.user;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.bean.UserInfo;


/**
 * Created by Shixi-A on 2017/6/2.
 */

public interface UserDataSource {

    void logout(Context context, RawResponseHandler callback);

    void userLogin(Context context, String name, String pwd, String vcode, GsonResponseHandler callBack);

    void getUsers(Context context,GsonResponseHandler callback);

    void getUser(Context context, GsonResponseHandler<UserInfo> callback);

    void checkName(Context context, String name, JsonResponseHandler callback);

    void getVcode(Context context, String name, JsonResponseHandler jsonResponseHandler);
}
