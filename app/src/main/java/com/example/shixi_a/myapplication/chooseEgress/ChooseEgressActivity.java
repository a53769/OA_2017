package com.example.shixi_a.myapplication.chooseEgress;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.egress.EgressRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/28.
 */

public class ChooseEgressActivity extends AppCompatActivity {
    private EgressRepository mRrepository;
    private ChooseEgressPresenter mPresenter;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_egress);

        ChooseEgressFragment chooseEgressFragment = (ChooseEgressFragment) getSupportFragmentManager().findFragmentById(R.id.choose_out_container);
        if(chooseEgressFragment == null){
            chooseEgressFragment = ChooseEgressFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),chooseEgressFragment,R.id.choose_out_container);
        }

        mRrepository = new EgressRepository();

        mPresenter = new ChooseEgressPresenter(mRrepository,chooseEgressFragment,getApplicationContext());

    }
}
