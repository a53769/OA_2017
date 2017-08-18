package com.example.shixi_a.myapplication.home.contacts.userInfo;

import com.example.shixi_a.myapplication.bean.User;

/**
 * Created by a5376 on 2017/8/17.
 */

public class UserInfoPresenter implements UserInfoContract.Presenter {

    private UserInfoFragment mInfoView;
    private User user;



    public UserInfoPresenter(User userInfo, UserInfoFragment userInfoDetailFragment) {
        user = userInfo;
        mInfoView = userInfoDetailFragment;
        mInfoView.setPresenter(this);
    }

    @Override
    public void start() {
        mInfoView.InitView(user.getName(),user.getAdmin_name_show(),user.getEmail(),user.getMobile(),user.getJoin_date());

    }


}
