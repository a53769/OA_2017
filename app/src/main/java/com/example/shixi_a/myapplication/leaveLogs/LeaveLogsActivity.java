package com.example.shixi_a.myapplication.leaveLogs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.Leave;
import com.example.shixi_a.myapplication.model.leave.LeaveRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

import java.util.List;

/**
 * Created by a5376 on 2017/7/19.
 */

public class LeaveLogsActivity extends AppCompatActivity {
    private LeaveLogsPresenter mPresenter;
    private LeaveRepository mRepository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_logs);

        Leave leave = (Leave) getIntent().getSerializableExtra("LEAVE_LOGS");
        List<Leave.Log> logs = leave.getLogs();

        LeaveLogsFragment leaveLogsFragment = (LeaveLogsFragment) getSupportFragmentManager().findFragmentById(R.id.leave_logs_container);
        if(leaveLogsFragment == null){
            leaveLogsFragment = LeaveLogsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),leaveLogsFragment,R.id.leave_logs_container);
        }

        mRepository = new LeaveRepository();
        mPresenter = new LeaveLogsPresenter(logs, mRepository, leaveLogsFragment, getApplicationContext());

    }

}
