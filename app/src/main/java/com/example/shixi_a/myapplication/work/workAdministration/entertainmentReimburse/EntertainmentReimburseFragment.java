package com.example.shixi_a.myapplication.work.workAdministration.entertainmentReimburse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.work.workAdministration.linkMan.LinkManActivity;
import com.example.shixi_a.myapplication.work.workAdministration.trafficTool.TrafficToolActivity;

import java.util.Calendar;
import java.util.Date;

import static com.example.shixi_a.myapplication.util.StringUtils.getDate;

/**
 * Created by a5376 on 2017/7/28.
 */

public class EntertainmentReimburseFragment extends Fragment implements EntertainmentReimburseContract.View{

    private EntertainmentReimburseContract.Presenter mPresenter;

    private TextView address;

    private TextView trafficTool;

    private TextView admin_name;

    private TextView real_name;

    private TextView time;

    private TextView visit_custom;

    private EditText serve_num;

    private EditText start_address;

    private EditText traffic_cost;

    private EditText cost;

    private EditText detail;

    private EditText bills;

    public EntertainmentReimburseFragment(){}

    public static EntertainmentReimburseFragment newInstance() {
        return new EntertainmentReimburseFragment();
    }

    @Override
    public void setPresenter(EntertainmentReimburseContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode, data);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.entertainment_reimburse_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        address = (TextView) root.findViewById(R.id.end_addr);

        admin_name = (TextView) root.findViewById(R.id.admin_name);

        real_name = (TextView) root.findViewById(R.id.real_person);

        time = (TextView) root.findViewById(R.id.cost_time);

        visit_custom = (TextView) root.findViewById(R.id.visit_custom);

        serve_num = (EditText) root.findViewById(R.id.entertain_num);

        start_address = (EditText) root.findViewById(R.id.begin_address);

        traffic_cost = (EditText) root.findViewById(R.id.traffic_cost);

        cost = (EditText) root.findViewById(R.id.total_cost);

        detail = (EditText) root.findViewById(R.id.cost_detail);

        bills = (EditText) root.findViewById(R.id.bills_count);

        LinearLayout lv1 = (LinearLayout) root.findViewById(R.id.lv_real_person);

        LinearLayout lv2 = (LinearLayout) root.findViewById(R.id.lv_cost_time);

        lv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLinkMan();
            }
        });

        lv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimetPicker();
            }
        });

        LinearLayout tool = (LinearLayout) root.findViewById(R.id.lv_traffic_tool);
        tool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTrafficTool();
            }
        });
        trafficTool = (TextView) tool.findViewById(R.id.traffic_tool);


        Button submit = (Button) root.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.submitTrafficReimburse(getStartAddress(),getTrafficCost(),getTime(),getCost(),getDetail(),getBills(),getServeNum());
            }
        });

        return root;
    }

    private void showTrafficTool() {
        Intent intent = new Intent(getActivity(), TrafficToolActivity.class);
        startActivityForResult(intent, TrafficToolActivity.REQUEST_TOOL_CODE);
    }

    private void showLinkMan() {
        Intent intent = new Intent(getActivity(), LinkManActivity.class);
        startActivityForResult(intent, LinkManActivity.REQUEST_USERS_CODE);
    }

    private void showTimetPicker() {
        TimePickerView pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                time.setText(getDate(date,"yyyy-MM-dd"));
            }
        }).setLabel("","月","日","时","分","秒").build();
        pvTime.setDate(Calendar.getInstance());
        pvTime.show();
    }

    @Override
    public void initAddress(String address) {
        this.address.setText(address);
    }

    @Override
    public void showReimbursement() {
        getActivity().finish();
    }

    @Override
    public void initPerson(String custom) {
        visit_custom.setText(custom);
    }

    public void setTraffic(String traffic) {
        trafficTool.setText(traffic);
    }

    public void setName(String name) {
        admin_name.setText(name);
    }

    public void setRealName(String realName) {
        real_name.setText(realName);
    }

    public String getTime() {
        return time.getText().toString();
    }

    public String getCost() {
        return cost.getText().toString().trim();
    }

    public String getDetail() {
        return detail.getText().toString().trim();
    }

    public String getBills() {
        return bills.getText().toString().trim();
    }


    public String getTrafficCost() {
        return traffic_cost.getText().toString().trim();
    }

    public String getStartAddress() {
        return start_address.getText().toString().trim();
    }

    public String getServeNum() {
        return serve_num.getText().toString().trim();
    }
}
