package com.example.shixi_a.myapplication.work.workAdministration.leave;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.leave.LeaveRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/18.
 */

public class LeaveActivity extends AppCompatActivity {

    private LeavePresenter mPresenter;
    private LeaveRepository mRepository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);

        LeaveFragment leaveFragment = (LeaveFragment) getSupportFragmentManager().findFragmentById(R.id.leave_container);
        if(leaveFragment == null){
            leaveFragment = LeaveFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),leaveFragment,R.id.leave_container);
        }

        mRepository = new LeaveRepository();

        mPresenter = new LeavePresenter(mRepository,leaveFragment,getApplicationContext());
    }
}
