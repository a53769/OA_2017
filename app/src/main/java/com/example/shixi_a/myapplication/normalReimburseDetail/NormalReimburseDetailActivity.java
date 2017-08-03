package com.example.shixi_a.myapplication.normalReimburseDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/27.
 */

public class NormalReimburseDetailActivity extends AppCompatActivity {
    private ReimbursementRepository mRepository;

    private NormalReimburseDetailPresenter mPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reimburse_detail);

        String reimburseId = getIntent().getStringExtra("id");

        NormalReimburseDetailFragment detailFragment = (NormalReimburseDetailFragment) getSupportFragmentManager().findFragmentById(R.id.reimburse_detail_container);
        if(detailFragment == null){
            detailFragment = NormalReimburseDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),detailFragment,R.id.reimburse_detail_container);
        }

        mRepository = new ReimbursementRepository();

        mPresenter = new NormalReimburseDetailPresenter(reimburseId,mRepository,detailFragment,getApplicationContext());

    }

}
