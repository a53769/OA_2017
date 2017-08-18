package com.example.shixi_a.myapplication.home.work.workAdministration.financeType;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/8/1.
 */

public class FinanceTypeActivity extends AppCompatActivity {
    public static final int REQUEST_TYPE_CODE = 8;

    private ReimbursementRepository mRepository;
    private FinanceTypeContract.Presenter mPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_type);



        FinanceTypeFragment financeTypeFragment = (FinanceTypeFragment) getSupportFragmentManager().findFragmentById(R.id.finance_type_container);
        if(financeTypeFragment == null){
            financeTypeFragment = FinanceTypeFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),financeTypeFragment,R.id.finance_type_container);
        }

        mRepository = new ReimbursementRepository();

        mPresenter = new FinanceTypePresenter(mRepository,financeTypeFragment,getApplicationContext());
    }



}
