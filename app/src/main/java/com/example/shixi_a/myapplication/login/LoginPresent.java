package com.example.shixi_a.myapplication.login;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.shixi_a.myapplication.GlobalApp;
import com.example.shixi_a.myapplication.bean.Result;
import com.example.shixi_a.myapplication.bean.Vathome;
import com.example.shixi_a.myapplication.model.user.UserRepository;
import com.example.shixi_a.myapplication.util.LogUtils;
import com.example.shixi_a.myapplication.util.StringUtils;

import static android.content.ContentValues.TAG;

/**
 * Created by Shixi-A on 2017/6/1.
 */

public class LoginPresent implements LoginContract.Presenter {

    private LoginContract.View mLoginView;

    private UserRepository mUserRepository;

    private GlobalApp app;

    public LoginPresent(LoginContract.View mLoginView)
    {
        this.mLoginView = mLoginView;
        this.mUserRepository = new UserRepository();
    }

    @Override
    public void login(Context context, String name, String password) {
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

        mUserRepository.userLogin(context, name, password, new GsonResponseHandler<Result<Vathome>>() {
            @Override
            public void onSuccess(int statusCode, Result<Vathome> response) {

                LogUtils.v(TAG, statusCode + " " + response.vathome.token);

                mLoginView.setToken(response.vathome.token);
                mLoginView.setToken2(response.token);
                mLoginView.setUsername();
                LogUtils.v("token", response.token);
                mLoginView.showSucceed();
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogUtils.v(TAG, statusCode + " " + error_msg);
                mLoginView.showError("用户名或密码不正确");
            }
        });

    }

    @Override
    public void start() {

    }
}
