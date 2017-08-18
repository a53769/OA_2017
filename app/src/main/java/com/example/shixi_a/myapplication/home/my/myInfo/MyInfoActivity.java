package com.example.shixi_a.myapplication.home.my.myInfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.UserInfo;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/8/17.
 */

public class MyInfoActivity extends AppCompatActivity {

    private MyInfoPresenter mPresenter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        UserInfo userInfo = (UserInfo) getIntent().getSerializableExtra("USER_INFO");

        MyInfoFragment userInfoDetailFragment = (MyInfoFragment) getSupportFragmentManager().findFragmentById(R.id.my_info_container);
        if(userInfoDetailFragment == null){
            userInfoDetailFragment = MyInfoFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),userInfoDetailFragment,R.id.my_info_container);
        }

        mPresenter = new MyInfoPresenter(userInfo,userInfoDetailFragment);
    }
}
