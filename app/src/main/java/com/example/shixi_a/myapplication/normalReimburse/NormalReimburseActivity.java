package com.example.shixi_a.myapplication.normalReimburse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/24.
 */

public class NormalReimburseActivity extends AppCompatActivity {
    private NormalReimbursePresenter mPresenter;
    private ReimbursementRepository mRepository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_reimburse);

//        TextView title = (TextView) findViewById(R.id.toolbarTitle);
//
//        Leave leave = (Leave) getIntent().getSerializableExtra("leave");
//        if(leave != null){
//            title.setText("编辑请假");
//        }

        String typeId = getIntent().getStringExtra("type");

        NormalReimburseFragment normalReimburseFragment = (NormalReimburseFragment) getSupportFragmentManager().findFragmentById(R.id.normal_reimburse_container);
        if (normalReimburseFragment == null){
            normalReimburseFragment = NormalReimburseFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),normalReimburseFragment,R.id.normal_reimburse_container);
        }
        mRepository = new ReimbursementRepository();

        mPresenter = new NormalReimbursePresenter( typeId,mRepository, normalReimburseFragment,getApplicationContext());
    }
}
