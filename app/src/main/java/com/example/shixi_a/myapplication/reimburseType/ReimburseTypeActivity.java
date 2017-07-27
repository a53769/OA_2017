package com.example.shixi_a.myapplication.reimburseType;

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
import com.example.shixi_a.myapplication.normalReimburse.NormalReimburseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by a5376 on 2017/7/24.
 */

public class ReimburseTypeActivity extends AppCompatActivity implements ReimburseTypeContract.View{

    private ReimburseTypeContract.Presenter mPresenter;
    private ReimbursementRepository mRepository;
    private ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reimburse_type);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.reimburse_type_list);

        mRepository = new ReimbursementRepository();

        mPresenter = new ReimburseTypePresenter(mRepository,this,getApplicationContext());

        mPresenter.start();

    }

    @Override
    public void setPresenter(ReimburseTypeContract.Presenter presenter) {
        mPresenter = presenter;
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
                        String typeId = (String) entry.getKey();

                        showApplyReimbursement(typeId);
                    }
                }
            }
        });
    }

    private void showApplyReimbursement(String typeId) {
        switch (typeId){
            case "1":
                showNormalReimburse("1");
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                showNormalReimburse("5");
                break;
            case "6":
                showNormalReimburse("6");
                break;
            case "7":
                showNormalReimburse("7");
                break;
            default:
                break;
        }

    }

    private void showNormalReimburse(String typeId) {
        Intent intent = new Intent(this, NormalReimburseActivity.class);
        intent.putExtra("type",typeId);
        startActivity(intent);
        finish();
    }
}
