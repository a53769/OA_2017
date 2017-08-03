package com.example.shixi_a.myapplication.payment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by a5376 on 2017/8/2.
 */

public class PaymentActivity extends AppCompatActivity implements PaymentContract.View{

    public static final int REQUEST_PAYMENT_ID = 12;

    private ReimbursementRepository mRepository;
    private PaymentContract.Presenter mPresenter;

    private ListView listView;

    private String typeId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.list_type);
        mRepository = new ReimbursementRepository();
        mPresenter = new PaymentPresenter(mRepository,this,getApplicationContext());

        mPresenter.start();
    }


    @Override
    public void showTypes(final Map<String, String> valueMap) {
        List list = new ArrayList(valueMap.values());
        final String[] datas = (String[]) list.toArray(new String[list.size()]);
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.simple_list_item, datas));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = datas[position];
                for(Map.Entry entry:valueMap.entrySet()){
                    if(value.equals(entry.getValue())) {
                        typeId = (String) entry.getKey();
                        showCashierAudit(typeId,value);
                    }
                }

            }
        });
    }

    @Override
    public void setPresenter(PaymentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void showCashierAudit(String typeId, String value) {
        Intent intent = new Intent();
        intent.putExtra("id", typeId);
        intent.putExtra("value", value);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
