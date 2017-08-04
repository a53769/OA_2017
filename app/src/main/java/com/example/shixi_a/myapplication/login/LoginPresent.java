package com.example.shixi_a.myapplication.login;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.GlobalApp;
import com.example.shixi_a.myapplication.bean.Result;
import com.example.shixi_a.myapplication.bean.Vathome;
import com.example.shixi_a.myapplication.model.user.UserRepository;
import com.example.shixi_a.myapplication.util.LogUtils;
import com.example.shixi_a.myapplication.util.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Shixi-A on 2017/6/1.
 */

public class LoginPresent implements LoginContract.Presenter {

    private LoginContract.View mLoginView;

    private UserRepository mUserRepository;

    public static boolean FLAG = false;
    private GlobalApp app;

    public LoginPresent(LoginContract.View mLoginView)
    {
        this.mLoginView = mLoginView;
        this.mUserRepository = new UserRepository();
    }

    @Override
    public void login(Context context, String name, String password, String vcode) {
        if (StringUtils.isEmpty(name))
        {
            mLoginView.showError("请输入账号");
            return;
        }

        if (StringUtils.isEmpty(password))
        {
            mLoginView.showError("请输入密码");
            return;
        }

        if(StringUtils.isEmpty(vcode)){
            mLoginView.showError("请输入验证码");
            return;
        }
        mUserRepository.userLogin(context, name, password,vcode, new GsonResponseHandler<Result<Vathome>>() {
            @Override
            public void onSuccess(int statusCode, Result<Vathome> response) {
                mLoginView.setToken(response.vathome.token);
                mLoginView.setToken2(response.token);
                mLoginView.setUsername();
                mLoginView.setVathome(response.vathome);
                mLoginView.showSucceed();
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                if (error_msg.equals("fail status=401")){
                    mLoginView.showError("用户名或密码错误");
                }else {
                    mLoginView.showError(error_msg);
                }
            }
        });

    }

    @Override
    public void checkName(Context context, String name) {
        if (StringUtils.isEmpty(name))
        {
            mLoginView.showError("请输入账号");
            return;
        }
        mUserRepository.checkName(context,name, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                if(response.getBoolean("rt")){
                    FLAG = true;
                }else{
                    mLoginView.showError("您的后台OA帐户个人资料不完整,请赶紧完善才能登录哦!!!");
                    FLAG = false;
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }

    @Override
    public void getVcode(Context context, String name) {
        mUserRepository.getVcode(context,name, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) throws JSONException {

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }

    @Override
    public void start() {

    }
}
