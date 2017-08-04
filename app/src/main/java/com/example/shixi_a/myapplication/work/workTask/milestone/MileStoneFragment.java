package com.example.shixi_a.myapplication.work.workTask.milestone;

import android.app.Activity;
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
import com.example.shixi_a.myapplication.bean.MileStone;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by a5376 on 2017/7/17.
 */

public class MileStoneFragment extends Fragment implements MileStoneContract.View{
    private MileStoneContract.Presenter mPresenter;

    private MilestoneAdapter adapter;

    @Override
    public void setPresenter(MileStoneContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public MileStoneFragment(){}

    public static MileStoneFragment newInstance() {
        return new MileStoneFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        adapter = new MilestoneAdapter(new ArrayList<MileStone>(0),mItemListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.milestone_content, container, false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        ListView listView = (ListView) root.findViewById(R.id.milestone_list);
        listView.setAdapter(adapter);

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
    public void showMilestones(List<MileStone> list) {
        adapter.replaceData(list);
    }

    @Override
    public void showTaskAdd(String id, String name) {
        Intent intent = new Intent();
        intent.putExtra("id",id);
        intent.putExtra("name",name);
        getActivity().setResult(Activity.RESULT_OK,intent);
        getActivity().finish();
    }

    private static class MilestoneAdapter extends BaseAdapter {
        List<MileStone> mList;
        MileItemListener mItemListener;

        public MilestoneAdapter(List<MileStone> mileStones, MileItemListener itemListener) {
            setList(mileStones);
            mItemListener = itemListener;
        }

        private void setList(List<MileStone> list) {
            mList = checkNotNull(list);
        }

        public void replaceData(List<MileStone> stones) {
            setList(stones);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public MileStone getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            if(rowView == null){
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                rowView = inflater.inflate(R.layout.list_item_milestone,parent,false);
            }
            final MileStone stone = getItem(position);

            TextView title = (TextView) rowView.findViewById(R.id.timestamp);
            title.setText(stone.getIn_time());

            TextView content = (TextView) rowView.findViewById(R.id.milestone_name);
            content.setText(stone.getName());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onMileStoneClick(stone);
                }
            });

            return rowView;
        }


    }

    MileItemListener mItemListener = new MileItemListener() {
        @Override
        public void onMileStoneClick(MileStone clickedMileStone) {
            mPresenter.selectedMileStone(clickedMileStone);
        }
    };

    public interface MileItemListener {

        void onMileStoneClick(MileStone clickedMileStone);
    }
}
