package com.example.shixi_a.myapplication.work.workAdministration.linkMan;

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
import com.example.shixi_a.myapplication.bean.User;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a5376 on 2017/7/21.
 */

public class LinkManFragment extends Fragment implements LinkManContract.View{

    private LinkManContract.Presenter mPresenter;
    private UsersAdapter adapter;

    public static LinkManFragment newInstance() {
        return new LinkManFragment();
    }

    @Override
    public void setPresenter(LinkManContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        adapter = new UsersAdapter(new ArrayList<User>(0),mItemListener);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.link_man_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });


        ListView listView = (ListView) root.findViewById(R.id.list_hand_over);
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
    public void showUsers(List<User> users) {
        adapter.replaceData(users);
    }

    private static class UsersAdapter extends BaseAdapter {

        private List<User> mList;
        private UserItemListener mItemListener;

        public UsersAdapter(List<User> users, UserItemListener itemListener) {
            setList(users);
            mItemListener = itemListener;
        }
        public void setList(List<User> list) {
            mList = list;
        }

        public void replaceData(List<User> list) {
            setList(list);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public User getItem(int position) {
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
                rowView = inflater.inflate(R.layout.list_item_link_man, parent, false);
            }

            final User user = getItem(position);

            TextView name = (TextView) rowView.findViewById(R.id.name);
            name.setText(user.getAdmin_name_show());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onUserClick(user);
                }
            });

            return rowView;
        }


    }

    UserItemListener mItemListener = new UserItemListener() {
        @Override
        public void onUserClick(User clickedUser) {
            Intent intent = new Intent();
            intent.putExtra("name",clickedUser.getAdmin_name_show());
            intent.putExtra("id",clickedUser.getId());
            getActivity().setResult(Activity.RESULT_OK, intent);
            getActivity().finish();
        }
    };

    public interface UserItemListener {

        void onUserClick(User clickedUser);
    }
}
