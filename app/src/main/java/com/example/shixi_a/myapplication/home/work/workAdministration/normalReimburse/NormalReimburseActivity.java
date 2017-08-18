package com.example.shixi_a.myapplication.home.work.workAdministration.normalReimburse;

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

public class NormalReimburseActivity extends AppCompatActivity {
    private NormalReimbursePresenter mPresenter;
    private ReimbursementRepository mRepository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_reimburse);

        TextView title = (TextView) findViewById(R.id.toolbarTitle);

        Reimbursement reimbursement = (Reimbursement) getIntent().getSerializableExtra("reimbursement");
        if(reimbursement != null){
            title.setText("编辑报销");
            //编辑功能被废弃 之后有时间把相关代码都删掉
        }

        String typeId = getIntent().getStringExtra("type");

        NormalReimburseFragment normalReimburseFragment = (NormalReimburseFragment) getSupportFragmentManager().findFragmentById(R.id.normal_reimburse_container);
        if (normalReimburseFragment == null){
            normalReimburseFragment = NormalReimburseFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),normalReimburseFragment,R.id.normal_reimburse_container);
        }
        mRepository = new ReimbursementRepository();

        mPresenter = new NormalReimbursePresenter( reimbursement,typeId,mRepository, normalReimburseFragment,getApplicationContext());
    }
}
