package com.example.shixi_a.myapplication.home.work.workAdministration.financeAudit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/8/1.
 */

public class FinanceAuditActivity extends AppCompatActivity {
    private ReimbursementRepository mRepository;
    private FinanceAuditPresenter mPresenter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_audit);

        String reimburseId = getIntent().getStringExtra("reimburse_id");

        FinanceAuditFragment financeAuditFragment = (FinanceAuditFragment) getSupportFragmentManager().findFragmentById(R.id.finance_audit_container);
        if(financeAuditFragment == null){
            financeAuditFragment = FinanceAuditFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),financeAuditFragment,R.id.finance_audit_container);
        }

        mRepository = new ReimbursementRepository();
        mPresenter = new FinanceAuditPresenter(reimburseId,mRepository,financeAuditFragment,getApplicationContext());
    }
}
