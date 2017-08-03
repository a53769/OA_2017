package com.example.shixi_a.myapplication.financeDetail;

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
import com.example.shixi_a.myapplication.bean.FinanceType;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a5376 on 2017/8/2.
 */

public class FinanceDetailFragment extends Fragment implements FinanceDetailContract.View {

    private FinanceDetailContract.Presenter mPresenter;

    private TypeAdapter adapter;

    @Override
    public void setPresenter(FinanceDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

   public FinanceDetailFragment(){}

    public static FinanceDetailFragment newInstance() {
        return new FinanceDetailFragment();
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TypeAdapter(new ArrayList<FinanceType.Sort.Child>(0),mItemListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.finance_type_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        ListView listView = (ListView) root.findViewById(R.id.type_list);
        listView.setAdapter(adapter);

        ScrollChildSwipeRefreshLayout swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        // Set the scrolling view in the custom SwipeRefreshLayout.
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
    public void showTypes(List<FinanceType.Sort.Child> sort_arr) {
        adapter.replaceData(sort_arr);
    }

    private static class TypeAdapter extends BaseAdapter {
        List<FinanceType.Sort.Child> mList;
        TypeItemListener mItemListener;

        public TypeAdapter(List<FinanceType.Sort.Child> sorts, TypeItemListener itemListener) {
            setList(sorts);
            mItemListener = itemListener;
        }

        public void replaceData(List<FinanceType.Sort.Child> sort_arr) {
            setList(sort_arr);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public FinanceType.Sort.Child getItem(int position) {
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
                rowView = inflater.inflate(R.layout.list_finance_type, parent, false);
            }

            final FinanceType.Sort.Child child = getItem(position);
            TextView name = (TextView) rowView.findViewById(R.id.name);
            name.setText(child.getSubname());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onSortClick(child);
                }
            });

            return rowView;
        }


        public void setList(List<FinanceType.Sort.Child> list) {
            mList = list;
        }
    }

    TypeItemListener mItemListener = new TypeItemListener() {
        @Override
        public void onSortClick(FinanceType.Sort.Child clickedInfo) {
            showFinanceAudit(clickedInfo.getSubid(),clickedInfo.getSubname());
        }
    };

    private void showFinanceAudit(String id, String name) {
        Intent intent = new Intent();
        intent.putExtra("id",id);
        intent.putExtra("name",name);
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    public interface TypeItemListener {

        void onSortClick(FinanceType.Sort.Child clickedSort);
    }
}
