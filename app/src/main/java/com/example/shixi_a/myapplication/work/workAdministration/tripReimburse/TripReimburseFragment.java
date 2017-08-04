package com.example.shixi_a.myapplication.work.workAdministration.tripReimburse;

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

public class TripReimburseFragment extends Fragment implements TripReimburseContract.View{

    private TripReimburseContract.Presenter mPresenter;

    private TextView trafficTool;

    private TextView admin_name;

    private TextView real_name;

    private TextView time;

    private EditText end_address;

    private EditText start_address;

    private EditText in_traffic_cost;

    private EditText out_traffic_cost;

    private EditText food_cost;

    private EditText live_cost;

    private EditText cost;

    private EditText detail;

    private EditText bills;
    private String liveCost;

    public TripReimburseFragment(){}

    public static TripReimburseFragment newInstance() {
        return new TripReimburseFragment();
    }

    @Override
    public void setPresenter(TripReimburseContract.Presenter presenter) {
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
        View root = inflater.inflate(R.layout.trip_reimburse_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        admin_name = (TextView) root.findViewById(R.id.admin_name);

        real_name = (TextView) root.findViewById(R.id.real_person);

        time = (TextView) root.findViewById(R.id.cost_time);

        start_address = (EditText) root.findViewById(R.id.begin_address);

        end_address = (EditText) root.findViewById(R.id.end_address);

        in_traffic_cost = (EditText) root.findViewById(R.id.incity_traffic_cost);

        out_traffic_cost = (EditText) root.findViewById(R.id.outcity_traffic_cost);

        food_cost = (EditText) root.findViewById(R.id.food_cost);

        live_cost = (EditText) root.findViewById(R.id.food_cost);

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

        LinearLayout tool = (LinearLayout) root.findViewById(R.id.traffic_tool_lv);
        tool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTrafficTool();
            }
        });
        trafficTool = (TextView) tool.findViewById(R.id.outcity_traffic_tool);


        Button submit = (Button) root.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.submitTripReimburse(
                        getStartAddress(),
                        getEndAddress(),
                        getTrafficCost(),
                        getOutTrafficCost(),
                        getTime(),
                        getFoodCost(),
                        getLiveCost(),
                        getCost(),
                        getDetail(),
                        getBills());
            }
        });

        return root;
    }

    private void showTrafficTool() {
        Intent intent = new Intent(getActivity(), TrafficToolActivity.class);
        intent.putExtra("flag","out");
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
    public void showReimbursement() {
        getActivity().finish();
    }

    @Override
    public void InitView(String dttime, String addr, String incity_traffic_fee, String outcity_traffic_show, String outcity_traffic_fee, String boarding_fee, String accomdation_fee, String fee_total, String memo, String bill_num) {
        time.setText(dttime);

        in_traffic_cost .setText(incity_traffic_fee);
        out_traffic_cost.setText(outcity_traffic_fee);


        cost.setText(fee_total);
        detail.setText(memo);
        bills.setText(bill_num);

        String [] temp = null;
        temp = addr.split("-");

        start_address.setText(temp[0]);
        end_address.setText(temp[1]);

        food_cost.setText(boarding_fee);
        live_cost.setText(accomdation_fee);
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
        return in_traffic_cost.getText().toString().trim();
    }

    public String getStartAddress() {
        return start_address.getText().toString().trim();
    }

    public String getEndAddress() {
        return end_address.getText().toString().trim();
    }

    public String getOutTrafficCost() {
        return out_traffic_cost.getText().toString().trim();
    }

    public String getFoodCost() {
        return food_cost.getText().toString().trim();
    }

    public String getLiveCost() {
        return live_cost.getText().toString().trim();
    }
}
