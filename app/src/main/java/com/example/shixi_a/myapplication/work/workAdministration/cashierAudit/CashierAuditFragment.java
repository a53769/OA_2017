package com.example.shixi_a.myapplication.work.workAdministration.cashierAudit;

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
import com.example.shixi_a.myapplication.work.workAdministration.payment.PaymentActivity;

import java.util.Calendar;
import java.util.Date;

import static com.example.shixi_a.myapplication.util.StringUtils.getDate;

/**
 * Created by a5376 on 2017/8/2.
 */

public class CashierAuditFragment extends Fragment implements CashierAuditContract.View {
    private CashierAuditContract.Presenter mPresenter;

    private TextView pay_way;
    private TextView pay_date;
    private EditText extra_cost;
    private EditText memo;

    public  CashierAuditFragment(){}

    public static CashierAuditFragment newInstance() {
        return new CashierAuditFragment();
    }
    @Override
    public void setPresenter(CashierAuditContract.Presenter presenter) {
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
        View root = inflater.inflate(R.layout.cashier_audit_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        LinearLayout payment = (LinearLayout) root.findViewById(R.id.lv_finance_type);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPayment();
            }
        });

        pay_way = (TextView) root.findViewById(R.id.payment);



        LinearLayout payday = (LinearLayout) root.findViewById(R.id.lv_pay_date);
        payday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });
        pay_date = (TextView) root.findViewById(R.id.pay_date);

        extra_cost = (EditText) root.findViewById(R.id.extra_cost);
        memo = (EditText) root.findViewById(R.id.finance_memo);

        Button button = (Button) root.findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.auditPass(getDay(),getCost(),getMemo());
            }
        });

        return root;
    }

    private void showTimePicker() {
        TimePickerView pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                pay_date.setText(getDate(date,"yyyy-MM-dd"));
            }
        }).setLabel("","月","日","时","分","秒").build();
        pvTime.setDate(Calendar.getInstance());
        pvTime.show();
    }

    @Override
    public void showPayment() {
        Intent intent = new Intent(getActivity(), PaymentActivity.class);
        startActivityForResult(intent,PaymentActivity.REQUEST_PAYMENT_ID);
    }

    @Override
    public void showReimburse() {
        getActivity().finish();
    }


    public String getDay() {
        return pay_date.getText().toString();
    }

    public String getCost() {
        return extra_cost.getText().toString().trim();
    }

    public String getMemo() {
        return memo.getText().toString().trim();
    }

    public void setName(String name) {
       pay_way.setText(name);
    }
}
