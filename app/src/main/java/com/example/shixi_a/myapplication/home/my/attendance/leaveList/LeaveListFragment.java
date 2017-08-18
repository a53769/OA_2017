package com.example.shixi_a.myapplication.home.my.attendance.leaveList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.AttendanceState;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a5376 on 2017/7/20.
 */

public class LeaveListFragment extends Fragment implements LeaveListContract.View {
    private LeaveListContract.Presenter mPresenter;
    private LogsAdapter adapter;

    @Override
    public void setPresenter(LeaveListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public LeaveListFragment(){}

    public static LeaveListFragment newInstance() {
        return new LeaveListFragment();
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        adapter = new LogsAdapter(new ArrayList<AttendanceState.LeaveInfo>(0));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.leave_info_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        ListView listView = (ListView) root.findViewById(R.id.leave_info_list);
        listView.setAdapter(adapter);

        ScrollChildSwipeRefreshLayout swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        swipeRefreshLayout.setScrollUpChild(listView);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.start();
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
    public void showLogs(List<AttendanceState.LeaveInfo> leaves) {
        adapter.replaceData(leaves);
    }

    private static class LogsAdapter extends BaseAdapter{

        List<AttendanceState.LeaveInfo> mList;

        public LogsAdapter(List<AttendanceState.LeaveInfo> logs) {
            setList(logs);
        }

        public void setList(List<AttendanceState.LeaveInfo> list) {
            mList = list;
        }

        public void replaceData(List<AttendanceState.LeaveInfo> logs) {
            setList(logs);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public AttendanceState.LeaveInfo getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;

            if(rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                rowView = inflater.inflate(R.layout.list_item_leave_info, parent, false);
            }

            int Positon = getCount() - position - 1;

            AttendanceState.LeaveInfo leave = getItem(Positon);

            TextView num = (TextView) rowView.findViewById(R.id.num);
            TextView start = (TextView) rowView.findViewById(R.id.s_time);
            TextView end = (TextView) rowView.findViewById(R.id.e_time);
            TextView type = (TextView) rowView.findViewById(R.id.type);

            num.setText(String.valueOf(Positon+1));
            start.setText("开始时间："+leave.getOff_start());
            end.setText("结束时间："+leave.getOff_end());
            type.setText("请假类型：" + getType(leave.getSort()));

            return rowView;
        }

        private String getType(String sort) {

            switch (sort){
                case "1" : return"事假";
                case "2" : return "年假";
                case "3" : return "婚假";
                case "4" : return "丧假";
                case "5" : return "病假";
                case "6" : return "调休";
                case "7" : return "产假";
                case "8" : return "产检";
                default : return "...";
            }
        }

    }
}
