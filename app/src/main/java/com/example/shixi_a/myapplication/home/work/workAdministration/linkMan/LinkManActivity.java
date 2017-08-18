package com.example.shixi_a.myapplication.home.work.workAdministration.linkMan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.user.UserRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/21.
 * 唉。。。Linkin park的主唱去世了TAT
 * Skin to bone,steel to rust
 * Ashe to ashes dust to dust
 */

public class LinkManActivity extends AppCompatActivity {
    public static final int REQUEST_USERS_CODE = 7;

    private UserRepository mRepository;

    private LinkManPresenter mPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_man);

        LinkManFragment linkManFragment = (LinkManFragment) getSupportFragmentManager().findFragmentById(R.id.hand_over_container);
        if(linkManFragment == null){
            linkManFragment = LinkManFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),linkManFragment,R.id.hand_over_container);
        }

        mRepository = new UserRepository();

        mPresenter = new LinkManPresenter(mRepository,linkManFragment,getApplicationContext());

    }
}
