package com.example.shixi_a.myapplication.home.contacts.userInfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.User;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/8/17.
 */

public class UserInfoActivity extends AppCompatActivity {

    private UserInfoPresenter mPresenter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        User userInfo = (User) getIntent().getSerializableExtra("USER_INFO");
//        String userInfo = getIntent().getStringExtra("name");

        UserInfoFragment userInfoDetailFragment = (UserInfoFragment) getSupportFragmentManager().findFragmentById(R.id.user_info_container);
        if(userInfoDetailFragment == null){
            userInfoDetailFragment = UserInfoFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),userInfoDetailFragment,R.id.user_info_container);
        }

        mPresenter = new UserInfoPresenter(userInfo,userInfoDetailFragment);
    }
}
