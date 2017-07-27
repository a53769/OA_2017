package com.example.shixi_a.myapplication.reimbursement;

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
import com.example.shixi_a.myapplication.bean.Reimbursement;
import com.example.shixi_a.myapplication.reimburseDetail.ReimburseDetailActivity;
import com.example.shixi_a.myapplication.reimburseType.ReimburseTypeActivity;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a5376 on 2017/7/24.
 */

public class ReimbursementFragment extends Fragment implements ReimbursementContract.View {
    private ReimbursementContract.Presenter mPresenter;
    private ReimbursementAdapter adapter;

    @Override
    public void setPresenter(ReimbursementContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ReimbursementFragment(){}

    public static ReimbursementFragment newInstance() {
        return new ReimbursementFragment();
    }
    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ReimbursementAdapter(new ArrayList<Reimbursement>(0),mItemListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.reimburse_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        ListView listView = (ListView) root.findViewById(R.id.reimbursement_list);
        listView.setAdapter(adapter);

        TextView apply = (TextView) getActivity().findViewById(R.id.apply_reimbursement);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReimburseType();
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

    private void showReimburseType() {
        Intent intent = new Intent(getActivity(), ReimburseTypeActivity.class);
        startActivity(intent);
    }

    @Override
    public void showReimburses(List<Reimbursement> list) {
        adapter.replaceData(list);
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


    private static class ReimbursementAdapter extends BaseAdapter{
        List<Reimbursement> mList;
        ReimburseItemListener mItemListener;

        public ReimbursementAdapter(List<Reimbursement> reimbursements, ReimburseItemListener itemListener) {
            setList(reimbursements);
            mItemListener = itemListener;
        }

        public void setList(List<Reimbursement> list) {
            mList = list;
        }

        public void replaceData(List<Reimbursement> list){
            setList(list);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Reimbursement getItem(int position) {
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
            rowView = inflater.inflate(R.layout.list_item_reimburse,parent,false);


            final Reimbursement reimburse = getItem(position);
            TextView name = (TextView) rowView.findViewById(R.id.name);
            TextView state = (TextView) rowView.findViewById(R.id.state);
            TextView time = (TextView) rowView.findViewById(R.id.time);

            name.setText(reimburse.getAdmin_name());
            state.setText(reimburse.getStatus_show());
//            if (reimburse.getStatus().equals("4")) {
//                state.setTextColor(gray);
//            }
            time.setText(reimburse.getType_show());



            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onReimburseClick(reimburse);
                }
            });

            return rowView;
        }


    }

    ReimburseItemListener mItemListener = new ReimburseItemListener() {
        @Override
        public void onReimburseClick(Reimbursement clickedReimburse) {
            showReimburseDetail(clickedReimburse.getId());
        }

    };

    private void showReimburseDetail(String id) {
        Intent intent = new Intent(getActivity(), ReimburseDetailActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    public interface ReimburseItemListener {

        void onReimburseClick(Reimbursement clickedReimburse);
    }
}
