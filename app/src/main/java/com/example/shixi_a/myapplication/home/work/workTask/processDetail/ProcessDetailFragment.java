package com.example.shixi_a.myapplication.home.work.workTask.processDetail;

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

public class ProcessDetailFragment extends Fragment implements ProcessDetailContract.View{

    private ProcessDetailContract.Presenter mPresenter;

    private TextView process_title;
    private TextView process_content;
    private TextView process_principal;
    private TextView start_time;
    private TextView end_time;

    public ProcessDetailFragment(){}

    public static ProcessDetailFragment newInstance() {
        return new ProcessDetailFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.process_detail_content, container, false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        process_title = (TextView) root.findViewById(R.id.Process_name);
        process_content = (TextView) root.findViewById(R.id.Process_content);
        process_principal = (TextView) root.findViewById(R.id.process_principal);
        start_time = (TextView) root.findViewById(R.id.process_stime);
        end_time = (TextView) root.findViewById(R.id.process_etime);


        return root;
    }

    @Override
    public void InitView(String title, String content, String s_time, String e_time, String principal) {
        process_title.setText(title);
        process_content.setText(content);
        process_principal.setText(principal);
        start_time .setText(s_time);
        end_time.setText(e_time);
    }

    @Override
    public void setPresenter(ProcessDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
