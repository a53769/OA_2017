package com.example.shixi_a.myapplication.home.work.workAdministration.chooseEgress;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.Egress;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;
import com.example.shixi_a.myapplication.home.work.workAdministration.entertainmentReimburse.EntertainmentReimburseActivity;
import com.example.shixi_a.myapplication.home.work.workAdministration.trafficReimburse.TrafficReimburseActivity;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by a5376 on 2017/7/28.
 */

public class ChooseEgressFragment extends Fragment implements ChooseEgressContract.View{

    private ChooseEgressContract.Presenter mPresenter;
    private EgressAdapter mListAdapter;

    public ChooseEgressFragment(){}

    public static ChooseEgressFragment newInstance() {
        return new ChooseEgressFragment();
    }

    @Override
    public void setPresenter(ChooseEgressContract.Presenter presenter) {
        mPresenter = presenter;
    }
    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new EgressAdapter(new ArrayList<Egress>(0),mItemListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //获取根视图
        View root = inflater.inflate(R.layout.choose_egress_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        ListView listView = (ListView) root.findViewById(R.id.choose_out_list);
        listView.setAdapter(mListAdapter);

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
    public void showEgresses(List<Egress> egresses) {
        mListAdapter.replaceData(egresses);
    }

    @Override
    public void showTrafficReimmburse(String id, String addr, String s) {
        Intent intent = new Intent(getActivity(), TrafficReimburseActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("addr",addr);
        intent.putExtra("person",s);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void showEntertainmentReimburse(String id, String address, String s) {
        Intent intent = new Intent(getActivity(), EntertainmentReimburseActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("addr",address);
        intent.putExtra("person",s);
        startActivity(intent);
        getActivity().finish();
    }

    private static class EgressAdapter extends BaseAdapter {

        List<Egress> mList;
        private EgressItemListener mItemListener;

        public EgressAdapter(List<Egress> egresses,EgressItemListener itemListener) {
            setList(egresses);
            mItemListener = itemListener;
        }

        private void setList(List<Egress> list) {
            mList = checkNotNull(list);
        }

        public void replaceData(List<Egress> egresses) {
            setList(egresses);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Egress getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            if (rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                rowView = inflater.inflate(R.layout.list_item_simple_out, parent, false);
            }

            final Egress egress = getItem(position);
            TextView title = (TextView) rowView.findViewById(R.id.title);
            final TextView address = (TextView) rowView.findViewById(R.id.address);
            final TextView person = (TextView) rowView.findViewById(R.id.person);
            title.setText(egress.getAdminname() + "  " + egress.getOut_time());
            if(egress.getStatus().equals("1")||egress.getStatus().equals("2")){
                address.setText(getString(egress.getAddr()));
            }else{
                address.setText(getString(egress.getEvent_addr()));
            }

            person.setText(getString(egress.getUsername()));

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onEgressClick(egress.getId(),address.getText().toString(),person.getText().toString());
                }
            });

            return rowView;
        }

        private String getString(String s) {
            if (s == ""||s==null) {
                return "暂无";
            }
            return s;
        }

    }


    EgressItemListener mItemListener = new EgressItemListener() {
        @Override
        public void onEgressClick(String id, String address, String person) {
            showDailog(id,address,person);

        }


    };

    private void showDailog(final String id, final String address, final String person) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("一旦选择就不能修改,你确定选择此次外出记录吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.selectedEgress(id,address,person);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public interface EgressItemListener {

        void onEgressClick(String id, String address, String person);

    }
}
