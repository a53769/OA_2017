package com.example.shixi_a.myapplication.checkOut;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;

import static com.example.shixi_a.myapplication.util.TimeUtil.getCurrentTime;

/**
 * Created by a5376 on 2017/7/14.
 */

public class CheckOutFragment extends Fragment implements CheckOutContract.View {

    private CheckOutContract.Presenter mPresenter;

    private TextView time;

    private TextView address;

    private EditText memo;

    @Override
    public void setPresenter(CheckOutContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public CheckOutFragment(){}

    public static CheckOutFragment newInstance() {
        return new CheckOutFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.check_in_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        time = (TextView) root.findViewById(R.id.check_out_time);
        time.setText(getCurrentTime());
        address = (TextView) root.findViewById(R.id.check_out_addr);


        memo = (EditText) root.findViewById(R.id.memo);

        Button submit = (Button) root.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.checkOut(getMemo());
            }
        });

        return root;
    }

    @Override
    public void initView(String out_time, String addr) {
        time.setText(out_time);
        address.setText(addr);
    }

    @Override
    public void showMessage() {
        getActivity().finish();
    }

    @Override
    public void setAddress(String addr) {
        address.setText(addr);
    }


    public String getMemo() {
        return memo.getText().toString().trim();
    }

}
