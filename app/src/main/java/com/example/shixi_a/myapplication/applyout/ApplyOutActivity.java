package com.example.shixi_a.myapplication.applyout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.Egress;
import com.example.shixi_a.myapplication.model.egress.EgressRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/12.
 */

public class ApplyOutActivity extends AppCompatActivity{

    private ApplyOutPresenter mPresenter;
    private EgressRepository mRepository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_out);

        TextView title = (TextView) findViewById(R.id.toolbarTitle);

        Egress egress = (Egress) getIntent().getSerializableExtra("egress");
        if(egress !=null){
            title.setText("编辑外出申请");
        }

        ApplyOutFragment applyOutFragment = (ApplyOutFragment) getSupportFragmentManager().findFragmentById(R.id.apply_out_container);
        if (applyOutFragment == null){
            applyOutFragment = ApplyOutFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),applyOutFragment,R.id.apply_out_container);
        }
        mRepository = new EgressRepository();

        mPresenter = new ApplyOutPresenter(egress,mRepository, applyOutFragment,getApplicationContext());
    }

}
