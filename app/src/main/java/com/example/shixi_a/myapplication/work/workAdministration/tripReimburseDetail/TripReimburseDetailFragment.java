package com.example.shixi_a.myapplication.work.workAdministration.tripReimburseDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.Reimbursement;
import com.example.shixi_a.myapplication.work.workAdministration.cashierAudit.CashierAuditActivity;
import com.example.shixi_a.myapplication.work.workAdministration.financeAudit.FinanceAuditActivity;
import com.example.shixi_a.myapplication.work.workAdministration.reimburseLog.ReimburseLogActivity;
import com.example.shixi_a.myapplication.work.workAdministration.tripReimburse.TripReimburseActivity;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;

/**
 * Created by a5376 on 2017/7/27.
 */

public class TripReimburseDetailFragment extends Fragment implements TripReimburseDetailContract.View {

    private TripReimburseDetailContract.Presenter mPresenter;

    private TextView status;
    private TextView person;
    private TextView real_person;
    private TextView type;
    private TextView cost_time;
    private TextView cost_detail;
    private TextView total_cost;
    private TextView bills;
    private TextView month_cost;
    private TextView custom;//当前参数还没有初始化
    private TextView start;
//    private TextView end;
    private TextView transport;//市外交通工具
    private TextView incity_transport_cost;
    private TextView outcity_transport_cost;
    private TextView food_cost;
    private TextView live_cost;

    private LinearLayout deal;
    private Button button;
    private Button button1;



    @Override
    public void setPresenter(TripReimburseDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public TripReimburseDetailFragment(){}

    public static TripReimburseDetailFragment newInstance() {
        return new TripReimburseDetailFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.trip_reimburse_detail_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        LinearLayout log = (LinearLayout) root.findViewById(R.id.reimburse_log);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showReimburseLog();
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
        start = (TextView) root.findViewById(R.id.start_addr);
//        end = (TextView) root.findViewById(R.id.end_addr);
        transport = (TextView) root.findViewById(R.id.transport);
        incity_transport_cost = (TextView) root.findViewById(R.id.incity_transport_cost);
        outcity_transport_cost = (TextView) root.findViewById(R.id.outcity_transport_cost);
        food_cost = (TextView) root.findViewById(R.id.food_cost);
        live_cost = (TextView) root.findViewById(R.id.live_cost);

        deal = (LinearLayout) root.findViewById(R.id.buttonView);
        button = (Button) deal.findViewById(R.id.button);
        button1 = (Button) deal.findViewById(R.id.button1);

        ScrollView scrollView = (ScrollView) root.findViewById(R.id.sv_reimburse_detail);

        ScrollChildSwipeRefreshLayout swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        swipeRefreshLayout.setScrollUpChild(scrollView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
    public void InitView(String status_show, String admin_name, String applicant_name, String type_show, String dttime, String memo, String fee_total, String bill_num, String sum_month, String addr, String outcity_traffic_show, String incity_traffic_fee, String outcity_traffic_fee, String boarding_fee, String accomdation_fee) {
        status.setText(status_show);
        person.setText(admin_name);
        real_person.setText(applicant_name);
        type.setText(type_show);
        cost_time.setText(dttime);
        cost_detail.setText(memo);
        total_cost.setText(fee_total);
        bills.setText(bill_num);
        month_cost.setText(sum_month);
        start.setText(getReplace(addr));
        transport.setText(outcity_traffic_show);
        incity_transport_cost.setText(incity_traffic_fee);
        outcity_transport_cost.setText(outcity_traffic_fee);
        food_cost.setText(boarding_fee);
        live_cost.setText(accomdation_fee);
    }

    private String getReplace(String addr) {
        return addr.replace("-"," — ");
    }

    @Override
    public void showReimburseLog(Reimbursement reimbursement) {
        Intent intent = new Intent(getActivity(),ReimburseLogActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("REIMBURSE_LOGS",reimbursement);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showEdit() {
        deal.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.cancel();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.edit();
            }
        });
    }

    @Override
    public void showAudit() {
        deal.setVisibility(View.VISIBLE);
        button.setText("拒绝");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.Audit("0");
            }
        });
        button1.setText("通过");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.Audit("1");
            }
        });
    }

    @Override
    public void showFinanceAudit() {
        deal.setVisibility(View.VISIBLE);
        button.setText("拒绝");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.FinanceAudit("0");
            }
        });
        button1.setText("通过");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.FinanceAudit("1");
            }
        });
    }

    @Override
    public void showCashierAudit() {
        deal.setVisibility(View.VISIBLE);
        button.setText("拒绝");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.CashierAudit("0");
            }
        });
        button1.setText("通过");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.CashierAudit("1");
            }
        });
    }

    @Override
    public void showReimbursement() {
        getActivity().finish();
    }

    @Override
    public void showFinanceType(String reimburseId) {
        Intent intent = new Intent(getActivity(), FinanceAuditActivity.class);
        intent.putExtra("reimburse_id",reimburseId);
        startActivity(intent);
    }

    @Override
    public void showCashier(String reimburseId) {
        Intent intent = new Intent(getActivity(),CashierAuditActivity.class);
        intent.putExtra("reimburse_id",reimburseId);
        startActivity(intent);
    }

    @Override
    public void hideButton() {
        deal.setVisibility(View.GONE);
    }

    @Override
    public void showTripEdit(Reimbursement reimbursement) {
        Intent intent = new Intent(getActivity(), TripReimburseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("reimbursement", reimbursement);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
