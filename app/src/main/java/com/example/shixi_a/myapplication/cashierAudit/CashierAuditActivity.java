package com.example.shixi_a.myapplication.cashierAudit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/8/2.
 */

public class CashierAuditActivity extends AppCompatActivity {

    private ReimbursementRepository mRepository;
    private CashierAuditPresenter mPresenter;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cashier_audit);

        String reimburseId = getIntent().getStringExtra("reimburse_id");

        CashierAuditFragment cashierAuditFragment = (CashierAuditFragment) getSupportFragmentManager().findFragmentById(R.id.cashier_audit_container);
        if(cashierAuditFragment == null){
            cashierAuditFragment = CashierAuditFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),cashierAuditFragment,R.id.cashier_audit_container);
        }

        mRepository = new ReimbursementRepository();
        mPresenter = new CashierAuditPresenter(reimburseId,mRepository,cashierAuditFragment,getApplicationContext());

    }
}
