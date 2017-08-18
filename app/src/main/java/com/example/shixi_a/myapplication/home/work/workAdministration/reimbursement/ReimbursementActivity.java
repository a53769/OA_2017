package com.example.shixi_a.myapplication.home.work.workAdministration.reimbursement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/24.
 */

public class ReimbursementActivity extends AppCompatActivity {

    private ReimbursementRepository mRepository;
    private ReimbursementPresenter mPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reimbursement);

        ReimbursementFragment reimbursementFragment = (ReimbursementFragment) getSupportFragmentManager().findFragmentById(R.id.reimbursement_container);
        if(reimbursementFragment == null){
            reimbursementFragment = ReimbursementFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),reimbursementFragment,R.id.reimbursement_container);
        }

        mRepository = new ReimbursementRepository();
        mPresenter = new ReimbursementPresenter(mRepository,reimbursementFragment,getApplicationContext());

    }
}
