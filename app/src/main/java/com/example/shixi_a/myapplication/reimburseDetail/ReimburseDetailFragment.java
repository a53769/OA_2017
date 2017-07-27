package com.example.shixi_a.myapplication.reimburseDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;

/**
 * Created by a5376 on 2017/7/27.
 */

public class ReimburseDetailFragment extends Fragment implements ReimburseDetailContract.View {

    private ReimburseDetailContract.Presenter mPresenter;

    private TextView status;
    private TextView person;
    private TextView real_person;
    private TextView type;
    private TextView cost_time;
    private TextView cost_detail;
    private TextView total_cost;
    private TextView bills;
    private TextView month_cost;
    private TextView custom;


    @Override
    public void setPresenter(ReimburseDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ReimburseDetailFragment(){}

    public static ReimburseDetailFragment newInstance() {
        return new ReimburseDetailFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.reimburse_detail_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });


        status = (TextView) root.findViewById(R.id.state);
        person = (TextView) root.findViewById(R.id.person);
        real_person= (TextView) root.findViewById(R.id.real_person);
        type = (TextView) root.findViewById(R.id.type);
        cost_time = (TextView) root.findViewById(R.id.time);
        cost_detail = (TextView) root.findViewById(R.id.cost_detail);
        total_cost = (TextView) root.findViewById(R.id.cost);
        bills = (TextView) root.findViewById(R.id.bills);
        month_cost = (TextView) root.findViewById(R.id.month_fee);
        custom = (TextView) root.findViewById(R.id.custom_name);



        ScrollView scrollView = (ScrollView) root.findViewById(R.id.sv_reimburse_detail);

        ScrollChildSwipeRefreshLayout swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        swipeRefreshLayout.setScrollUpChild(scrollView);

        swipeRefreshLayout.setOnRefreshListener(new android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {mPresenter.start();
            }
        });
        return root;
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void InitView(String status_show, String admin_name, String applicant_name, String type_show, String dttime, String memo, String fee_total, String bill_num, String sum_month) {
        status.setText(status_show);
        person.setText(admin_name);
        real_person.setText(applicant_name);
        type.setText(type_show);
        cost_time.setText(dttime);
        cost_detail.setText(memo);
        total_cost.setText(fee_total);
        bills.setText(bill_num);
        month_cost.setText(sum_month);
    }


}
