package com.example.shixi_a.myapplication.work.workAdministration.applyout;

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
import com.example.shixi_a.myapplication.util.ToastUtils;

import java.util.Calendar;
import java.util.Date;

import static com.example.shixi_a.myapplication.util.StringUtils.getDate;

/**
 * Created by a5376 on 2017/7/12.
 */

public class ApplyOutFragment extends Fragment implements ApplyOutContract.View {

    private ApplyOutContract.Presenter mPresenter;
    private LinearLayout lv_time;
    private TextView pickTime;
    private EditText person;
    private EditText address;
    private EditText msg;

    @Override
    public void setPresenter(ApplyOutContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ApplyOutFragment(){}

    public static ApplyOutFragment newInstance() {
        return new ApplyOutFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.apply_out_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        LinearLayout Time = (LinearLayout) root.findViewById(R.id.out_time);
        pickTime = (TextView) root.findViewById(R.id.time);
        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        person = (EditText) root.findViewById(R.id.user_name);
        address = (EditText) root.findViewById(R.id.addr);
        msg = (EditText) root.findViewById(R.id.matter);


        Button submit = (Button) root.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.askOut(getTime(),getPerson(),getAddress(),getReason());
            }
        });


        return root;
    }
    private void showTimePicker() {
        TimePickerView pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                pickTime.setText(getDate(date,"yyyy-MM-dd HH:mm:ss"));
            }
        }).setLabel("","月","日","时","分","秒").build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    public String getReason() {
        return msg.getText().toString().trim();
    }

    public String getAddress() {
        return address.getText().toString().trim();
    }

    public String getPerson() {
        return person.getText().toString().trim();
    }

    public String getTime() {
        return pickTime.getText().toString();
    }

    @Override
    public void showMessage(String msg) {
        ToastUtils.showShort(getContext(),msg);
    }

    @Override
    public void showEgress() {
        getActivity().finish();
    }

    @Override
    public void initView(String out_time, String username, String addr, String matter) {
        pickTime.setText(out_time);
        person.setText(username);
        person.setFocusable(false);
        person.setKeyListener(null);
        address.setText(addr);
        msg.setText(matter);
    }
}
