package com.example.shixi_a.myapplication.attendance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.attendance.AttendanceRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/7.
 */

public class AttendanceActivity extends AppCompatActivity{

    private AttendancePresenter mPresenter;
    private AttendanceRepository mRepository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        AttendanceFragment attendanceFragment = (AttendanceFragment) getSupportFragmentManager().findFragmentById(R.id.attendance_container);

        if(attendanceFragment == null){
            attendanceFragment = new AttendanceFragment().newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), attendanceFragment ,R.id.attendance_container);
        }
        mRepository = new AttendanceRepository();
        mPresenter = new AttendancePresenter(mRepository, attendanceFragment, getApplicationContext());

    }
}
