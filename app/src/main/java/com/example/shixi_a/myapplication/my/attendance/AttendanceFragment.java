package com.example.shixi_a.myapplication.my.attendance;

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
import com.example.shixi_a.myapplication.bean.AttendanceState;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by a5376 on 2017/7/7.
 */

public class AttendanceFragment extends Fragment implements AttendanceContract.View {
    private AttendanceContract.Presenter mPresenter;

    private TextView out_count;
    private TextView off_c;
    private TextView off_d;
    private TextView local;
    private TextView other;
    private TextView need;
    private TextView late;
    private TextView early;

    @Override
    public void setPresenter(AttendanceContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public AttendanceFragment() {
    }

    public static AttendanceFragment newInstance() {
        return new AttendanceFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //获取根视图
        View root = inflater.inflate(R.layout.attendance_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        out_count = (TextView) root.findViewById(R.id.out_count);
        off_c = (TextView) root.findViewById(R.id.oa_aff_c);
        off_d = (TextView) root.findViewById(R.id.oa_off_d);
        local = (TextView) root.findViewById(R.id.local_days);
        other = (TextView) root.findViewById(R.id.other_days);
        need = (TextView) root.findViewById(R.id.need_days);
        late = (TextView) root.findViewById(R.id.late_days);
        early = (TextView) root.findViewById(R.id.early_days);

        ScrollView lv = (ScrollView) root.findViewById(R.id.state_content);

        final ScrollChildSwipeRefreshLayout swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        swipeRefreshLayout.setScrollUpChild(lv);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.start();
            }
        });

        return root;

    }

    @Override
    public void initView(AttendanceState response) {
        out_count.setText(response.getAdm_out_c());
        out_count.setTextColor(getRandomColor());
        off_c.setText(response.getOa_off_c());
        off_c.setTextColor(getRandomColor());
        double b = Double.parseDouble(response.getOa_off_d());
        BigDecimal d = new BigDecimal(b);
        b = d.setScale(1,BigDecimal.ROUND_HALF_DOWN).doubleValue();
        off_d.setText(String.valueOf(b));
        off_d.setTextColor(getRandomColor());
        local.setText(response.getCheck_local_days());
        local.setTextColor(getRandomColor());
        other.setText(response.getCheck_other_days());
        other.setTextColor(getRandomColor());
        need.setText(response.getCheck_need_days());
        need.setTextColor(getRandomColor());
        late.setText(response.getCheck_late_days());
        late.setTextColor(getRandomColor());
        early.setText(response.getCheck_early_days());
        early.setTextColor(getRandomColor());
    }

    private int getRandomColor(){
        Random random = new Random();
        return 0xff000000|random.nextInt(0x00ffffff);
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

}