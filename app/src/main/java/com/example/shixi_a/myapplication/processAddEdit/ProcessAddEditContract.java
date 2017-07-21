package com.example.shixi_a.myapplication.processAddEdit;

import android.content.Intent;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

/**
 * Created by a5376 on 2017/6/16.
 */

public interface ProcessAddEditContract {

    interface View extends BaseView<Presenter> {

        void setExecutor(String executor);//为执行人TextView赋值

        void showContacts();//跳转联系人

        void showProcesses();//结束当前返回到流程列表

        void setTitle(String title);

        void setContent(String description);

        void setEdit();

        void setTime(String estimate_stime, String estimate_etime);

        void setTime(String time);
    }
    interface Presenter extends BasePresenter {
        
        void submit(String title, String content, String etime);//提交流程

        void result(int requestCode, int resultCode, Intent data);//intent传参处理

        String getTime();
    }
}
