package com.example.shixi_a.myapplication.checkOut;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.egress.EgressRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/14.
 */

public class CheckOutActivity extends AppCompatActivity{
    private CheckOutPresenter mPresenter;
    private EgressRepository mRepository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);


        String out_id = getIntent().getStringExtra("id");
        String out_name = getIntent().getStringExtra("name");

        CheckOutFragment  checkOutFragment = (CheckOutFragment) getSupportFragmentManager().findFragmentById(R.id.check_in_container);
        if(checkOutFragment == null){
            checkOutFragment = CheckOutFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),checkOutFragment,R.id.check_in_container);
        }

        mRepository = new EgressRepository();

        mPresenter = new CheckOutPresenter(out_id,out_name,mRepository,checkOutFragment,getApplicationContext());

    }
}
