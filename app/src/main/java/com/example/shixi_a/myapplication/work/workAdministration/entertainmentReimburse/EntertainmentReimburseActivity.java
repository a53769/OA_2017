package com.example.shixi_a.myapplication.work.workAdministration.entertainmentReimburse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.Reimbursement;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by a5376 on 2017/7/28.
 */

public class EntertainmentReimburseActivity extends AppCompatActivity {
    private ReimbursementRepository mRepository;
    private EntertainmentReimbursePresenter mPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment_reimburse);

        TextView title = (TextView) findViewById(R.id.toolbarTitle);

        String out_id = getIntent().getStringExtra("id");
        String address = getIntent().getStringExtra("addr");
        String custom = getIntent().getStringExtra("person");

        Reimbursement reimbursement = (Reimbursement) getIntent().getSerializableExtra("reimbursement");
        if(reimbursement != null){
            title.setText("编辑报销");
        }

        EntertainmentReimburseFragment entertainmentReimburseFragment = (EntertainmentReimburseFragment) getSupportFragmentManager().findFragmentById(R.id.entertainment_reimburse_container);
        if(entertainmentReimburseFragment == null){
            entertainmentReimburseFragment = EntertainmentReimburseFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), entertainmentReimburseFragment,R.id.entertainment_reimburse_container);
        }

        mRepository = new ReimbursementRepository();
        mPresenter = new EntertainmentReimbursePresenter(reimbursement,custom,out_id,address,mRepository, entertainmentReimburseFragment,getApplicationContext());
    }

}
