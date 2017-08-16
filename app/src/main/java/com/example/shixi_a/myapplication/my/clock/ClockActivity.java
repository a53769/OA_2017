package com.example.shixi_a.myapplication.my.clock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.attendance.AttendanceRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;


/**
 * Created by a5376 on 2017/7/5.
 */

public class ClockActivity extends AppCompatActivity {

    private ClockPresenter mClockPresenter;

    private AttendanceRepository mAttendanceRepository;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        ClockFragment clockFragment = (ClockFragment) getSupportFragmentManager().findFragmentById(R.id.clock_content);
        if(clockFragment == null){
            clockFragment = new ClockFragment().newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), clockFragment, R.id.clock_content);
        }

        mAttendanceRepository = new AttendanceRepository();

        mClockPresenter = new ClockPresenter(mAttendanceRepository, clockFragment,getApplicationContext());

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mClockPresenter.onDestroy();
    }

}
