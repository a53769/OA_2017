package com.example.shixi_a.myapplication.egress;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.egress.EgressRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/11.
 */

public class EgressActivity extends AppCompatActivity{

    private EgressPresenter mEgressPresenter;
    private EgressRepository mRepository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egress);

        EgressFragment egressFragment = (EgressFragment) getSupportFragmentManager().findFragmentById(R.id.out_container);
        if(egressFragment == null){
            egressFragment = EgressFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),egressFragment,R.id.out_container);
        }

        mRepository = new EgressRepository();

        mEgressPresenter = new EgressPresenter(mRepository, egressFragment,getApplicationContext());
    }

}
