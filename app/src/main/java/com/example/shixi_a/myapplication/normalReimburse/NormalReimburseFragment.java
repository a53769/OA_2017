package com.example.shixi_a.myapplication.normalReimburse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shixi_a.myapplication.R;

/**
 * Created by a5376 on 2017/7/24.
 */

public class NormalReimburseFragment extends Fragment implements NormalReimburseContract.View {

    private NormalReimburseContract.Presenter mPresenter;

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

        return root;
    }
}
