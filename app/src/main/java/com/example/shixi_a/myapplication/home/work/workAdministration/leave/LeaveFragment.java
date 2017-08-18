package com.example.shixi_a.myapplication.home.work.workAdministration.leave;

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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.home.work.workAdministration.applyleave.ApplyLeaveActivity;
import com.example.shixi_a.myapplication.bean.Leave;
import com.example.shixi_a.myapplication.home.work.workAdministration.leaveDetail.LeaveDetailActivity;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static com.example.shixi_a.myapplication.R.color.gray;

/**
 * Created by a5376 on 2017/7/18.
 */

public class LeaveFragment extends Fragment implements LeaveContract.View {

    private LeaveContract.Presenter mPresenter;
    private LeaveAdapter adapter;

    @Override
    public void setPresenter(LeaveContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public LeaveFragment(){}

    public static LeaveFragment newInstance() {
        return new LeaveFragment();
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new LeaveAdapter(new ArrayList<Leave>(0),mItemListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.leave_content, container, false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        ListView listView = (ListView) root.findViewById(R.id.leave_list);
        listView.setAdapter(adapter);

        TextView apply = (TextView) getActivity().findViewById(R.id.apply_leave);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showApplyLeave();
            }
        });

        ScrollChildSwipeRefreshLayout swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(listView);

        swipeRefreshLayout.setOnRefreshListener(new android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.start();

            }
        });
        return root;
    }

    private void showApplyLeave() {
        Intent intent = new Intent(getActivity(), ApplyLeaveActivity.class);
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
    public void showLeave(List<Leave> leaves) {
        adapter.replaceData(leaves);
    }

    private void showLeaveDetail(String id) {
        Intent intent = new Intent(getActivity(), LeaveDetailActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    private static class LeaveAdapter extends BaseAdapter {

        List<Leave> mList;
        LeaveItemListener mItemListener;

        LeaveAdapter(ArrayList<Leave> leaves, LeaveItemListener itemListener) {
            setList(leaves);
            mItemListener = itemListener;
        }

        void replaceData(List<Leave> list){
            setList(list);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Leave getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;

            //这里取消了缓存判断因为要保持原始界面（会消耗系统资源，目前没有好的办法）加了判断语句依然有问题 之后再改吧
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            rowView = inflater.inflate(R.layout.list_item_record,parent,false);


            final Leave leave = getItem(position);
            TextView name = (TextView) rowView.findViewById(R.id.name);
            TextView state = (TextView) rowView.findViewById(R.id.state);
            TextView time = (TextView) rowView.findViewById(R.id.time);

            name.setText(leave.getAdmin_name());
            state.setText(leave.getStatus_show());
            if (leave.getStatus().equals("4")) {
                state.setTextColor(gray);
            }
            time.setText(leave.getSort_show());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onLeaveClick(leave);
                }
            });


            return rowView;
        }

        public void setList(List<Leave> list) {
            mList = list;
        }
    }

    LeaveItemListener mItemListener = new LeaveItemListener() {
        @Override
        public void onLeaveClick(Leave clickedLeave) {
            showLeaveDetail(clickedLeave.getId());
        }

    };



    public interface LeaveItemListener {

        void onLeaveClick(Leave clickedLeave);


    }
}
