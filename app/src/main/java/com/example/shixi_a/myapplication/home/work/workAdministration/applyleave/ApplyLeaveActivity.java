package com.example.shixi_a.myapplication.home.work.workAdministration.applyleave;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.Leave;
import com.example.shixi_a.myapplication.model.leave.LeaveRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/18.
 */

public class ApplyLeaveActivity extends AppCompatActivity {

    private ApplyLeavePresenter mPresenter;

    private LeaveRepository mRepository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_leave);

        TextView title = (TextView) findViewById(R.id.toolbarTitle);

        Leave leave = (Leave) getIntent().getSerializableExtra("leave");

        if(leave != null){
            title.setText("编辑请假");
        }

        ApplyLeaveFragment applyLeaveFragment = (ApplyLeaveFragment) getSupportFragmentManager().findFragmentById(R.id.apply_leave_container);
        if (applyLeaveFragment == null){
            applyLeaveFragment = ApplyLeaveFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),applyLeaveFragment,R.id.apply_leave_container);
        }
        mRepository = new LeaveRepository();

        mPresenter = new ApplyLeavePresenter(leave, mRepository, applyLeaveFragment,getApplicationContext());
    }
}
