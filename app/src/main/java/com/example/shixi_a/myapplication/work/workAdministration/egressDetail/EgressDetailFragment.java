package com.example.shixi_a.myapplication.work.workAdministration.egressDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;

/**
 * Created by a5376 on 2017/7/18.
 */

public class EgressDetailFragment extends Fragment implements EgressDetailContract.View {

    private EgressDetailContract.Presenter mPresenter;

    private LinearLayout check;
    private TextView out_name;
    private TextView out_state;
    private TextView out_time;
    private TextView visit_name;
    private TextView out_addr;
    private TextView out_reason;
    private TextView check_addr;
    private TextView check_time;
    private TextView check_content;



    @Override
    public void setPresenter(EgressDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public EgressDetailFragment(){}

    public static EgressDetailFragment newInstance() {
        return new EgressDetailFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.out_detail_content, container, false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        ScrollView scrollView  = (ScrollView) root.findViewById(R.id.out_detail_sv);

        check = (LinearLayout) root.findViewById(R.id.check);
        out_name = (TextView) root.findViewById(R.id.out_person);
        out_state = (TextView) root.findViewById(R.id.out_state);
        out_time = (TextView) root.findViewById(R.id.out_time);
        visit_name = (TextView) root.findViewById(R.id.visit_person);
        out_addr = (TextView) root.findViewById(R.id.out_addr);
        out_reason = (TextView) root.findViewById(R.id.out_reason);
        check_addr = (TextView) root.findViewById(R.id.check_addr);
        check_time = (TextView) root.findViewById(R.id.check_time);
        check_content = (TextView) root.findViewById(R.id.check_content);

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
    public void InitView(String adminname, String status_show, String outTime, String username, String addr, String matter) {
        out_name.setText(adminname);
        out_state.setText(status_show);
        out_time.setText(outTime.subSequence(0,outTime.length()-3));
        if(username == null|| username.equals("")) {
            visit_name.setText("暂无");
        }else {
            visit_name.setText(username);
        }
        out_addr.setText(addr);
        out_reason.setText(matter);
    }

    @Override
    public void InitCheckView(String event_addr, String event_time, String event_content) {
        check.setVisibility(View.VISIBLE);
        check_addr.setText(event_addr);
        check_time.setText(event_time);
        check_content.setText(event_content);
    }
}
