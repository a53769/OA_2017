package com.example.shixi_a.myapplication.leaveDetail;

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
import com.example.shixi_a.myapplication.applyleave.ApplyLeaveActivity;
import com.example.shixi_a.myapplication.bean.Leave;
import com.example.shixi_a.myapplication.leaveLogs.LeaveLogsActivity;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;

/**
 * Created by a5376 on 2017/7/18.
 */

public class LeaveDetailFragment extends Fragment implements LeaveDetailContract.View {

    private LeaveDetailContract.Presenter mPresenter;

    private TextView person;
    private TextView state;
    private TextView type;
    private TextView start_time;
    private TextView end_time;
    private TextView is_personnel;
    private TextView turnover_person;
    private TextView reason;
    private LinearLayout deal;
    private LinearLayout offset;
    private Button button;
    private Button button1;

    @Override
    public void setPresenter(LeaveDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public LeaveDetailFragment(){}

    public static LeaveDetailFragment newInstance(){
        return new LeaveDetailFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.leave_detail_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        LinearLayout log = (LinearLayout) root.findViewById(R.id.leave_log);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showLeaveLogs();
            }
        });

        person = (TextView) root.findViewById(R.id.person);
        state = (TextView) root.findViewById(R.id.state);
        type = (TextView) root.findViewById(R.id.type);
        start_time = (TextView) root.findViewById(R.id.Stime);
        end_time = (TextView) root.findViewById(R.id.Etime);
        is_personnel = (TextView) root.findViewById(R.id.renshi_change);
        turnover_person = (TextView) root.findViewById(R.id.over_person);
        reason = (TextView) root.findViewById(R.id.leave_reason);

        offset = (LinearLayout) root.findViewById(R.id.offset);

        deal = (LinearLayout) root.findViewById(R.id.buttonView);
        button = (Button) deal.findViewById(R.id.button);
        button1 = (Button) deal.findViewById(R.id.button1);

        ScrollView scrollView = (ScrollView) root.findViewById(R.id.sv_leave_detail);

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
    public void showLeaveLogs(Leave logs) {
        Intent intent = new Intent(getActivity(),LeaveLogsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("LEAVE_LOGS",logs);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showSelfEdit() {
        deal.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.cancelLeave();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.editLeave();
            }
        });
    }

    @Override
    public void showHREdit() {
        deal.setVisibility(View.VISIBLE);
        button.setVisibility(View.GONE);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.editLeave();
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
    public void showLeaves() {
        getActivity().finish();
    }

    @Override
    public void showEditLeave(Leave leave) {
        Intent intent = new Intent(getActivity(),ApplyLeaveActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("leave", leave);
        intent.putExtras(bundle);
        startActivity(intent);
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
    public void showLeave(Leave leave) {
        person.setText(leave.getAdmin_name());
        state.setText(leave.getStatus_show());
        type.setText(leave.getSort_show());
        start_time.setText(leave.getOff_start());
        end_time.setText(leave.getOff_end());
        if(leave.getNeed_modify().equals("0")){
            is_personnel.setText("不需要");
        }else{
            is_personnel.setText("需要");
        }
        if(leave.getHandover_name() == null || leave.getHandover_name().equals("")){
            turnover_person.setText("无转接人");
        }else {
            turnover_person.setText(leave.getHandover_name());
        }
        reason.setText(leave.getReason());

        if(leave.getSort().equals("6")){
            offset.setVisibility(View.VISIBLE);
            TextView start = (TextView) offset.findViewById(R.id.OffStime);
            TextView end = (TextView) offset.findViewById(R.id.OffEtime);
            TextView content = (TextView) offset.findViewById(R.id.Offcontent);
            start.setText(leave.getOffset_start());
            end.setText(leave.getOffset_end());
            content.setText(leave.getOffset_memo());
        }
    }
}
