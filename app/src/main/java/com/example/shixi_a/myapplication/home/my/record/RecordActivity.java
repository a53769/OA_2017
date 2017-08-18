package com.example.shixi_a.myapplication.home.my.record;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.attendance.AttendanceRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/7.
 */

public class RecordActivity extends AppCompatActivity {

    private RecordPresenter mRecordPresenter;

    private AttendanceRepository recordRepository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        RecordFragment recordFragment = (RecordFragment) getSupportFragmentManager().findFragmentById(R.id.record_container);

        if(recordFragment == null){
            recordFragment = RecordFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), recordFragment ,R.id.record_container );
        }

        recordRepository = new AttendanceRepository();

        mRecordPresenter = new RecordPresenter(recordRepository, recordFragment, getApplicationContext());

    }
}
