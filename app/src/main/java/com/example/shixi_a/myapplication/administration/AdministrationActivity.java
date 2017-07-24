package com.example.shixi_a.myapplication.administration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.egress.EgressActivity;
import com.example.shixi_a.myapplication.leave.LeaveActivity;
import com.example.shixi_a.myapplication.util.ToastUtils;

/**
 * Created by a5376 on 2017/7/11.
 */

public class AdministrationActivity extends AppCompatActivity implements View.OnClickListener{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);

        LinearLayout ly1 = (LinearLayout) findViewById(R.id.qingjia);
        LinearLayout ly2 = (LinearLayout) findViewById(R.id.baoxiao);
        LinearLayout ly3 = (LinearLayout) findViewById(R.id.waichu);
        LinearLayout ly4 = (LinearLayout) findViewById(R.id.chuchai);
        ly1.setOnClickListener(this);
        ly2.setOnClickListener(this);
        ly3.setOnClickListener(this);
        ly4.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.qingjia:
                Intent intent1 = new Intent(this, LeaveActivity.class);
                startActivity(intent1);
//                ToastUtils.showShort(this,"请假");
                break;
            case R.id.baoxiao:
                ToastUtils.showShort(this,"报销");
                break;
            case R.id.waichu:
                Intent intent = new Intent(this, EgressActivity.class);
                startActivity(intent);
//                ToastUtils.showShort(this,"外出");
                break;
            case R.id.chuchai:
                ToastUtils.showShort(this,"出差");
                break;
            default:
                break;
        }
    }
    //填补更改
}
