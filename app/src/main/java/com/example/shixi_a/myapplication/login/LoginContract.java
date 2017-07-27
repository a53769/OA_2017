package com.example.shixi_a.myapplication.login;

import android.content.Context;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.Vathome;

/**
 * Created by Shixi-A on 2017/6/1.
 */

public interface LoginContract {


    interface Presenter extends BasePresenter {

        void login(Context context, String name, String password);
    }

    interface View extends BaseView<Presenter> {
        void showError(String msg);
        void setToken(String token);
        void showSucceed();

        void setToken2(String token);

        void setUsername();

        void setVathome(Vathome vathome);
    }
}

