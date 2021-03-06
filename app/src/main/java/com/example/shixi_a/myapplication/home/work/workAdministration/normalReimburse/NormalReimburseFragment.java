package com.example.shixi_a.myapplication.home.work.workAdministration.normalReimburse;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.example.shixi_a.myapplication.home.work.workAdministration.linkMan.LinkManActivity;

import java.util.Calendar;
import java.util.Date;

import static com.example.shixi_a.myapplication.util.StringUtils.getDate;

/**
 * Created by a5376 on 2017/7/24.
 */

public class NormalReimburseFragment extends Fragment implements NormalReimburseContract.View {

    private NormalReimburseContract.Presenter mPresenter;

    private TextView admin_name;

    private TextView real_name;

    private TextView time;

    private EditText cost;

    private EditText detail;

    private EditText bills;


    public NormalReimburseFragment(){}

    public static NormalReimburseFragment newInstance() {
        return new NormalReimburseFragment();
    }

    @Override
    public void setPresenter(NormalReimburseContract.Presenter presenter) {
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
        View root = inflater.inflate(R.layout.normal_reimburse_content, container, false);

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

        Button submit = (Button) root.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDailog();
            }
        });

        return root;
    }

    private void showDailog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("一旦提交就不能修改,你确定此报销填写无误吗？如果是,请点击确定,否则请进行修改。");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.applyReimbursement(getTime(),getCost(),getDetail(),getBills());
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
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

    @Override
    public void showReimburse() {
        getActivity().finish();
    }

    @Override
    public void InitView(String dttime, String fee_total, String memo, String bill_num) {
        time.setText(dttime);
        cost.setText(fee_total);
        detail.setText(memo);
        bills.setText(bill_num);
    }
}
