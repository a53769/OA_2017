package com.example.shixi_a.myapplication.work.workAdministration.financeDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/8/1.
 */

public class FinanceDetailActivity extends AppCompatActivity {
    public static final int REQUEST_TYPE_CODE = 11;

    private ReimbursementRepository mRepository;
    private FinanceDetailContract.Presenter mPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_detail);

        String typeId = getIntent().getStringExtra("type_id");

        FinanceDetailFragment financeDetailFragment = (FinanceDetailFragment) getSupportFragmentManager().findFragmentById(R.id.finance_detail_container);
        if(financeDetailFragment == null){
            financeDetailFragment = FinanceDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), financeDetailFragment,R.id.finance_detail_container);
        }

        mRepository = new ReimbursementRepository();

        mPresenter = new FinanceDetailPresenter(typeId,mRepository, financeDetailFragment,getApplicationContext());
    }



}
