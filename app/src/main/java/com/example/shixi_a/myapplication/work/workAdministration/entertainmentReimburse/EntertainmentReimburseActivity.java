package com.example.shixi_a.myapplication.work.workAdministration.entertainmentReimburse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/28.
 */

public class EntertainmentReimburseActivity extends AppCompatActivity {
    private ReimbursementRepository mRepository;
    private EntertainmentReimbursePresenter mPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment_reimburse);

        String out_id = getIntent().getStringExtra("id");
        String address = getIntent().getStringExtra("addr");
        String custom = getIntent().getStringExtra("person");

        EntertainmentReimburseFragment entertainmentReimburseFragment = (EntertainmentReimburseFragment) getSupportFragmentManager().findFragmentById(R.id.entertainment_reimburse_container);
        if(entertainmentReimburseFragment == null){
            entertainmentReimburseFragment = EntertainmentReimburseFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), entertainmentReimburseFragment,R.id.entertainment_reimburse_container);
        }

        mRepository = new ReimbursementRepository();
        mPresenter = new EntertainmentReimbursePresenter(custom,out_id,address,mRepository, entertainmentReimburseFragment,getApplicationContext());
    }

}
