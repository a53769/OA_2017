package com.example.shixi_a.myapplication.home.my.myInfo;

import com.example.shixi_a.myapplication.bean.UserInfo;

/**
 * Created by a5376 on 2017/8/17.
 */

public class MyInfoPresenter implements MyInfoContract.Presenter {

    private MyInfoFragment mInfoView;

    private UserInfo.Row user;

    public MyInfoPresenter(UserInfo userInfo, MyInfoFragment userInfoDetailFragment) {
        user = userInfo.getRow();
        mInfoView = userInfoDetailFragment;
        mInfoView.setPresenter(this);
    }

    @Override
    public void start() {
        mInfoView.InitView(user.getName(),user.getReal_name(),user.getEmail(),user.getMobile(),user.getJoin_date());
    }
}
