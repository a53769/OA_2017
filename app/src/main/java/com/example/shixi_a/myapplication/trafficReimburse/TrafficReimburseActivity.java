package com.example.shixi_a.myapplication.trafficReimburse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/28.
 */

public class TrafficReimburseActivity extends AppCompatActivity {
    private ReimbursementRepository mRepository;
    private TrafficReimbursePresenter mPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_reimburse);

        String out_id = getIntent().getStringExtra("id");
        String address = getIntent().getStringExtra("addr");

        TrafficReimburseFragment trafficReimburseFragment = (TrafficReimburseFragment) getSupportFragmentManager().findFragmentById(R.id.traffic_reimburse_container);
        if(trafficReimburseFragment == null){
            trafficReimburseFragment = TrafficReimburseFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),trafficReimburseFragment,R.id.traffic_reimburse_container);
        }

        mRepository = new ReimbursementRepository();
        mPresenter = new TrafficReimbursePresenter(out_id,address,mRepository,trafficReimburseFragment,getApplicationContext());
    }

}
