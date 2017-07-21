package com.example.shixi_a.myapplication.milestone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.task.TaskRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/17.
 */

public class MileStoneActivity extends AppCompatActivity {
    public static final int REQUEST_MILE_CODE = 5;

    private MileStonePresenter mPresenter;

    private TaskRepository mRepository;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milestone);

        String proId = getIntent().getStringExtra("pro_id");

        MileStoneFragment mileStoneFragment = (MileStoneFragment) getSupportFragmentManager().findFragmentById(R.id.milestone_container);
        if(mileStoneFragment == null){
            mileStoneFragment = MileStoneFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),mileStoneFragment,R.id.milestone_container);
        }

        mRepository = new TaskRepository();

        mPresenter = new MileStonePresenter(mRepository,mileStoneFragment,getApplicationContext(),proId);
    }
}
