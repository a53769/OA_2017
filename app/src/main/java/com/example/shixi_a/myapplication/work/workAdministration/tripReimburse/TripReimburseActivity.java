package com.example.shixi_a.myapplication.work.workAdministration.tripReimburse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.Reimbursement;
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

        TextView title = (TextView) findViewById(R.id.toolbarTitle);

        Reimbursement reimbursement = (Reimbursement) getIntent().getSerializableExtra("reimbursement");
        if(reimbursement != null){
            title.setText("编辑报销");
        }

        TripReimburseFragment tripReimburseFragment = (TripReimburseFragment) getSupportFragmentManager().findFragmentById(R.id.trip_reimburse_container);
        if (tripReimburseFragment == null){
            tripReimburseFragment = TripReimburseFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), tripReimburseFragment,R.id.trip_reimburse_container);
        }
        mRepository = new ReimbursementRepository();

        mPresenter = new TripReimbursePresenter( reimbursement,mRepository, tripReimburseFragment,getApplicationContext());
    }
}
