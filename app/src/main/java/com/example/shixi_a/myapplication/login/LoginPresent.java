package com.example.shixi_a.myapplication.login;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.shixi_a.myapplication.GlobalApp;
import com.example.shixi_a.myapplication.bean.Result;
import com.example.shixi_a.myapplication.bean.Vathome;
import com.example.shixi_a.myapplication.model.user.UserRepository;
import com.example.shixi_a.myapplication.util.StringUtils;

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
                    mLoginView.showError("请检查网络连接");
                }
            }
        });

    }

    @Override
    public void start() {

    }
}
