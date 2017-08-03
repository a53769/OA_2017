package com.example.shixi_a.myapplication.tripReimburse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/24.
 */

public class TripReimburseActivity extends AppCompatActivity {


    private TripReimbursePresenter mPresenter;
    private ReimbursementRepository mRepository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_reimburse);

        TripReimburseFragment tripReimburseFragment = (TripReimburseFragment) getSupportFragmentManager().findFragmentById(R.id.trip_reimburse_container);
        if (tripReimburseFragment == null){
            tripReimburseFragment = TripReimburseFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), tripReimburseFragment,R.id.trip_reimburse_container);
        }
        mRepository = new ReimbursementRepository();

        mPresenter = new TripReimbursePresenter( mRepository, tripReimburseFragment,getApplicationContext());
    }
}
