package com.example.shixi_a.myapplication.leaveLogs;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.Leave;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static com.example.shixi_a.myapplication.util.StringUtils.getSubDate;

/**
 * Created by a5376 on 2017/7/20.
 */

public class LeaveLogsFragment extends Fragment implements LeaveLogsContract.View {
    private LeaveLogsContract.Presenter mPresenter;
    private LogsAdapter adapter;

    @Override
    public void setPresenter(LeaveLogsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public LeaveLogsFragment(){}

    public static LeaveLogsFragment newInstance() {
        return new LeaveLogsFragment();
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        adapter = new LogsAdapter(new ArrayList<Leave.Log>(0));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.leave_logs_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        ListView listView = (ListView) root.findViewById(R.id.leave_logs_list);
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
    public void showLogs(List<Leave.Log> logs) {
        adapter.replaceData(logs);
    }

    private static class LogsAdapter extends BaseAdapter{

        List<Leave.Log> mList;

        public LogsAdapter(List<Leave.Log> logs) {
            setList(logs);
        }

        public void setList(List<Leave.Log> list) {
            mList = list;
        }

        public void replaceData(List<Leave.Log> logs) {
            setList(logs);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Leave.Log getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;


            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            rowView = inflater.inflate(R.layout.list_item_leave_log, parent, false);

            int Positon = getCount() - position - 1;

            Leave.Log log = getItem(Positon);

            TextView time = (TextView) rowView.findViewById(R.id.time);
            TextView action = (TextView) rowView.findViewById(R.id.action);
            ImageView point = (ImageView) rowView.findViewById(R.id.point);
            View viewTop = rowView.findViewById(R.id.viewtop);
            View viewBottom = rowView.findViewById(R.id.viewbottom);
            time.setText(getSubDate(log.getActime()));
            action.setText(log.getAction());

            if(getCount() == 1){
                point.setImageResource(R.drawable.ic_fiber_p_24dp);
                viewBottom.setVisibility(View.GONE);
                viewTop.setVisibility(View.GONE);
            }else if(position == 0){
                point.setImageResource(R.drawable.ic_fiber_p_24dp);
                viewTop.setVisibility(View.INVISIBLE);
            }else if(position == getCount()-1){
                viewTop.setVisibility(View.VISIBLE);
                viewBottom.setVisibility(View.GONE);
            }
            return rowView;
        }

    }
}
