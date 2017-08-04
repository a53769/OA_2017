package com.example.shixi_a.myapplication.work.workAdministration.reimburseLog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.Reimbursement;
import com.example.shixi_a.myapplication.util.ActivityUtils;

import java.util.List;

/**
 * Created by a5376 on 2017/7/31.
 */

public class ReimburseLogActivity extends AppCompatActivity {

    private ReimburseLogPresenter mPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reimburse_logs);

        Reimbursement reimbursement = (Reimbursement) getIntent().getSerializableExtra("REIMBURSE_LOGS");
        List<Reimbursement.Log> logs = reimbursement.getLogs();

        ReimburseLogFragment reimburseLogFragment = (ReimburseLogFragment) getSupportFragmentManager().findFragmentById(R.id.reimburse_logs_container);
        if(reimburseLogFragment == null){
            reimburseLogFragment = ReimburseLogFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),reimburseLogFragment,R.id.reimburse_logs_container);
        }

        mPresenter = new ReimburseLogPresenter(logs,reimburseLogFragment,getApplicationContext());
    }

}
