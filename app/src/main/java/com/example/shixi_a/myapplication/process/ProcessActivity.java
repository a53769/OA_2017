package com.example.shixi_a.myapplication.process;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.util.ActivityUtils;
import com.example.shixi_a.myapplication.model.process.ProcessRepository;

/**
 * Created by Shixi-A on 2017/6/12.
 */

public class ProcessActivity extends AppCompatActivity {

    public static final String EXTRA_TASK_ID = "TASK_ID";

    public static final int REQUEST_PROCESS_CODE = 3;

    private ProcessPresenter mProcessPresenter;

    private ProcessRepository processRepository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
        String taskId = getIntent().getStringExtra(EXTRA_TASK_ID);
        String Stime = getIntent().getStringExtra("STime");

        ProcessFragment processFragment = (ProcessFragment) getSupportFragmentManager().findFragmentById(R.id.process_content);

        if(processFragment == null){
            processFragment = new ProcessFragment().newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), processFragment ,R.id.process_content );
        }
        processRepository = new ProcessRepository();
        mProcessPresenter = new ProcessPresenter(processRepository, processFragment, getApplicationContext(), taskId, Stime);

    }

}
