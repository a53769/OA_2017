package com.example.shixi_a.myapplication.home.my.record;

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
import com.example.shixi_a.myapplication.bean.Attendance;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static com.example.shixi_a.myapplication.util.StringUtils.getSubDate;

/**
 * Created by a5376 on 2017/7/7.
 */

public class RecordFragment extends Fragment implements RecordContract.View {

    private RecordContract.Presenter mPresenter;

    private RecordAdapter mListAdapter;

    public RecordFragment (){}

    public static RecordFragment newInstance(){
        return new RecordFragment();
    }

    @Override
    public void setPresenter(RecordContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mListAdapter = new RecordAdapter(new ArrayList<Attendance>(0));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.record_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        ListView listView = (ListView) root.findViewById(R.id.record_list);
        listView.setAdapter(mListAdapter);

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
    public void showRecords(List<Attendance> attendances) {
        mListAdapter.replaceData(attendances);
    }

    private class RecordAdapter extends BaseAdapter{

        List<Attendance> mList;

        public RecordAdapter(List<Attendance> attendance) {
            setList(attendance);
        }
        private void setList(List<Attendance> list) {
            mList = checkNotNull(list);
        }
        public void replaceData(List<Attendance> attendance) {
            setList(attendance);
            notifyDataSetChanged();
        }
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Attendance getItem(int position) {
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
                rowView = inflater.inflate(R.layout.list_item_record, parent, false);
            }

            Attendance attendance = getItem(position);
            TextView name = (TextView) rowView.findViewById(R.id.name);
            name.setText(attendance.getAdmin_name());
            TextView state = (TextView) rowView.findViewById(R.id.state);
            state.setText(getState(attendance.getSort()));
            TextView time = (TextView) rowView.findViewById(R.id.time);
            time.setText(getSubDate(attendance.getIn_time()));

            return rowView;
        }

        private String getState(String sort) {
            switch (sort){
                case "1":
                    return "上班";
                default:
                    return "下班";
            }
        }


    }
}
