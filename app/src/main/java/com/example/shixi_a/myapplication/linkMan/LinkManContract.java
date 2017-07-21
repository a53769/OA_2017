package com.example.shixi_a.myapplication.linkMan;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.User;

import java.util.List;

/**
 * Created by a5376 on 2017/7/21.
 */

public interface LinkManContract {
    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(final boolean active);

        void showUsers(List<User> users);
    }
    interface Presenter extends BasePresenter {


    }
}
