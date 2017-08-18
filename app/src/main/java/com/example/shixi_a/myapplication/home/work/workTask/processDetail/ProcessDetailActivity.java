package com.example.shixi_a.myapplication.home.work.workTask.processDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.process.ProcessRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;
import com.example.shixi_a.myapplication.bean.Process;

/**
 * Created by a5376 on 2017/8/17.
 */

public class ProcessDetailActivity extends AppCompatActivity {
    private ProcessRepository mRepository;
    private ProcessDetailPresenter mPresenter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_detail);

        Process process = (Process) getIntent().getSerializableExtra("DETAIL_PROCESS");

        ProcessDetailFragment processDetailFragment = (ProcessDetailFragment) getSupportFragmentManager().findFragmentById(R.id.process_detail_container);
        if(processDetailFragment == null){
            processDetailFragment = ProcessDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),processDetailFragment,R.id.process_detail_container);
        }

        mRepository = new ProcessRepository();//其实没必要用这个数据仓库的

        mPresenter = new ProcessDetailPresenter(process, mRepository,processDetailFragment,getApplicationContext());
    }
}
