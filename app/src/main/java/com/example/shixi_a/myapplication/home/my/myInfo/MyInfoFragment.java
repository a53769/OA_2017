package com.example.shixi_a.myapplication.home.my.myInfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;

/**
 * Created by a5376 on 2017/8/17.
 */

public class MyInfoFragment extends Fragment implements MyInfoContract.View{

    private MyInfoContract.Presenter mPresenter;
    private TextView account;
    private TextView name;
    private TextView email;
    private TextView phone;
    private TextView inTime;

    public MyInfoFragment(){}

    public static MyInfoFragment newInstance() {
        return new MyInfoFragment();
    }
    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.my_info_content, container, false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        account = (TextView) root.findViewById(R.id.account);
        name = (TextView) root.findViewById(R.id.real_name);
        email = (TextView) root.findViewById(R.id.email);
        phone = (TextView) root.findViewById(R.id.phone);
        inTime = (TextView) root.findViewById(R.id.time);

        return root;
    }
    @Override
    public void InitView(String adminname, String real_name, String email, String phone, String in_time) {
        account.setText(adminname);
        name.setText(real_name);
        this.email.setText(email);
        this.phone.setText(phone);
        inTime.setText(in_time);
    }

    @Override
    public void setPresenter(MyInfoContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
