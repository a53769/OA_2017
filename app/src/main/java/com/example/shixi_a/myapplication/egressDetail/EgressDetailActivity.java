package com.example.shixi_a.myapplication.egressDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.Egress;
import com.example.shixi_a.myapplication.model.egress.EgressRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/18.
 */

public class EgressDetailActivity extends AppCompatActivity {
    private EgressRepository mRepository;
    private EgressDetailPresenter mPresenter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egress_detail);

        Egress egress = (Egress) getIntent().getSerializableExtra("DETAIL_EGRESS");

        EgressDetailFragment egressDetailFragment = (EgressDetailFragment) getSupportFragmentManager().findFragmentById(R.id.out_detail_container);
        if(egressDetailFragment == null){
            egressDetailFragment = EgressDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),egressDetailFragment,R.id.out_detail_container);
        }

        mRepository = new EgressRepository();

        mPresenter = new EgressDetailPresenter(egress, mRepository,egressDetailFragment,getApplicationContext());
    }
}
