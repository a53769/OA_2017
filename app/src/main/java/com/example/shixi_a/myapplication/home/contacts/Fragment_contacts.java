package com.example.shixi_a.myapplication.home.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.bean.User;
import com.example.shixi_a.myapplication.home.contacts.userInfo.UserInfoActivity;
import com.example.shixi_a.myapplication.model.user.UserRepository;
import com.example.shixi_a.myapplication.util.LogUtils;
import com.example.shixi_a.myapplication.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shixi-A on 2017/5/17.
 */

public class Fragment_contacts extends Fragment {

    private ExpandableListView expandableListView;
    private static List<String> list;
    private String[] datas;
    private UserRepository mRepository;
    private Context context;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        list = new ArrayList<>();
        context = getContext();
        mRepository = new UserRepository();
        mRepository.getUsers(context, new GsonResponseHandler<RowsNoPage<User>>() {
            @Override
            public void onSuccess(int statusCode, RowsNoPage<User> response) {
                List<User> users = response.rows;
                processContacts(users);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogUtils.v("加载失败");
                ToastUtils.showShort(context,"加载列表失败");
            }
        });

        expandableListView = (ExpandableListView) view.findViewById(R.id.contacts_List);
        expandableListView.setVisibility(View.GONE);
        listView = (ListView) view.findViewById(R.id.lis_contacts);
        listView.setVisibility(View.VISIBLE);
        return view;

    }

    private void processContacts(final List<User> users) {

        for (int i = 0 ;i<users.size();i++){
            list.add(users.get(i).getAdmin_name_show());
        }
        datas = list.toArray(new String[list.size()]);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.simple_list_item, datas));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("USER_INFO",users.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }



}
