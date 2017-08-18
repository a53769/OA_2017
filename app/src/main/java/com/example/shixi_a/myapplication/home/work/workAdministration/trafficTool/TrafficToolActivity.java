package com.example.shixi_a.myapplication.home.work.workAdministration.trafficTool;

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
 * Created by a5376 on 2017/7/28.
 */

public class TrafficToolActivity extends AppCompatActivity implements TrafficToolContract.View{

    public static int REQUEST_TOOL_CODE = 8;

    private ReimbursementRepository mRepository;
    private TrafficToolContract.Presenter mPresenter;

    private ListView listView;

    private String typeId;

    @Override
    public void setPresenter(TrafficToolContract.Presenter presenter) {
        mPresenter = presenter;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_tool);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.list_type);
//        listView.setAdapter(adapter);

        String flag = getIntent().getStringExtra("flag");
        mRepository = new ReimbursementRepository();

        mPresenter = new TrafficToolPresenter(flag,mRepository,this,getApplicationContext());

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
                        showTrafficReimburse(typeId,value);
                    }
                }

            }
        });
    }

    private void showTrafficReimburse(String typeId, String value) {
        Intent intent = new Intent();
        intent.putExtra("id", typeId);
        intent.putExtra("value", value);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }


}
