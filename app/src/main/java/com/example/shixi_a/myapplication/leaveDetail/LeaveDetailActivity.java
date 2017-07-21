package com.example.shixi_a.myapplication.leaveDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.leave.LeaveRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/18.
 */

public class LeaveDetailActivity extends AppCompatActivity {

    private LeaveRepository mRepository;
    private LeaveDetailPresenter mPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_detail);

        String leaveId = getIntent().getStringExtra("id");

        LeaveDetailFragment leaveDetailFragment = (LeaveDetailFragment) getSupportFragmentManager().findFragmentById(R.id.leave_detail_container);
        if(leaveDetailFragment == null){
            leaveDetailFragment = LeaveDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),leaveDetailFragment,R.id.leave_detail_container);
        }

        mRepository = new LeaveRepository();

        mPresenter = new LeaveDetailPresenter(leaveId,mRepository,leaveDetailFragment,getApplicationContext());
    }

}
