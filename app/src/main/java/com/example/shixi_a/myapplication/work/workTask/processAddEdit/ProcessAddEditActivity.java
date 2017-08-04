package com.example.shixi_a.myapplication.work.workTask.processAddEdit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.example.shixi_a.myapplication.bean.Process;
import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.util.ActivityUtils;
import com.example.shixi_a.myapplication.model.process.ProcessRepository;

/**
 * Created by Shixi-A on 2017/6/12.
 */

public class ProcessAddEditActivity extends AppCompatActivity {


    private ProcessRepository mRepository;

    private ProcessAddEditPresenter mPresenter;

//    private boolean FIRST = true;

    private TextView process_title;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        process_title = (TextView) findViewById(R.id.process_add_title);

        Process process = (Process) getIntent().getSerializableExtra(ProcessAddEditFragment.ARGUMENT_EDIT_PROCESS);
        String taskId = getIntent().getStringExtra("taskId");
        String stepId = getIntent().getStringExtra("step_id");
        String time = getIntent().getStringExtra("time");

        setToolbarTitle(process);

        ProcessAddEditFragment mProcessAddEditFragment = (ProcessAddEditFragment) getSupportFragmentManager().findFragmentById(R.id.process_add_content);
        if(mProcessAddEditFragment == null){
            mProcessAddEditFragment = ProcessAddEditFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mProcessAddEditFragment, R.id.process_add_content);
        }

        mRepository = new ProcessRepository();

        if(process == null) {
            mPresenter = new ProcessAddEditPresenter(process,mRepository, mProcessAddEditFragment, getApplicationContext(), taskId, stepId,time);
        }
        else{
            mPresenter = new ProcessAddEditPresenter(process,mRepository, mProcessAddEditFragment, getApplicationContext(), taskId);
        }
    }

    public void setToolbarTitle(Process process) {
        if(process == null){
            process_title.setText("添加流程");
        } else{
            process_title.setText("编辑流程");
        }
    }
}
