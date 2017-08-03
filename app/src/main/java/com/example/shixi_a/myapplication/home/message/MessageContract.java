package com.example.shixi_a.myapplication.home.message;

import android.content.Intent;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.Message;

import java.util.List;

/**
 * Created by a5376 on 2017/7/11.
 */

public interface MessageContract {

    interface Presenter extends BasePresenter {

        void getReimburseDetail(String id);

        void result(int requestCode, int resultCode, Intent data);
    }
    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(final boolean active);

        void showMessage(List<Message> messages);

        void showReimburseDetail(String id, String type);
    }
}
