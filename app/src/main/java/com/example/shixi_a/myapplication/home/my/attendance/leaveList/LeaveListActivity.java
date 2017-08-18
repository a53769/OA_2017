package com.example.shixi_a.myapplication.home.my.attendance.leaveList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.AttendanceState;
import com.example.shixi_a.myapplication.model.leave.LeaveRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

import java.util.List;

/**
 * Created by a5376 on 2017/7/19.
 */

public class LeaveListActivity extends AppCompatActivity {
    private LeaveListPresenter mPresenter;
    private LeaveRepository mRepository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_info);

        AttendanceState attendanceState= (AttendanceState) getIntent().getSerializableExtra("attendanceState");
        List<AttendanceState.LeaveInfo> leaves = attendanceState.getOa_off_s();


        LeaveListFragment leaveListFragment = (LeaveListFragment) getSupportFragmentManager().findFragmentById(R.id.leave_info_container);
        if(leaveListFragment == null){
            leaveListFragment = LeaveListFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), leaveListFragment,R.id.leave_info_container);
        }

        mPresenter = new LeaveListPresenter(leaves, leaveListFragment);

    }

}
