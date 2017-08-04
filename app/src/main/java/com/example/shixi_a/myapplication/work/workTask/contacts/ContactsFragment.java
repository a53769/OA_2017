package com.example.shixi_a.myapplication.work.workTask.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.Contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by a5376 on 2017/6/15.
 */

public class ContactsFragment extends Fragment implements ContactsContract.View {

    private ContactsContract.Presenter mPresenter;

    private MyExpandableListAdapter mAdapter;

    private static List<String> IdList;

    private static List<String> nameList;

    private static String name;

    public ContactsFragment(){}

    public static ContactsFragment newInstance(){
        return new ContactsFragment();
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mAdapter = new MyExpandableListAdapter(new ArrayList<Contact>(0));//传指针
        IdList = new ArrayList<String>();
        nameList = new ArrayList<String>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_contacts, container, false);

        ExpandableListView expandableListView = (ExpandableListView) root.findViewById(R.id.contacts_List);
        expandableListView.setAdapter(mAdapter);

//        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().finish();
//            }
//        });

        TextView submit = (TextView) root.findViewById(R.id.contacts_submit);
        submit.setVisibility(View.VISIBLE);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameList.size()>1){
                    name = nameList.get(0)+"等";
                }else if(nameList.size() == 0) {
                    name = "";
                }else{
                    name = nameList.get(0);
                }
                Intent intent = new Intent();
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("list", (Serializable) IdList);
                intent.putExtras(mBundle);
                intent.putExtra("name",name);
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
            }
        });

        return root;
    }



    @Override
    public void showContacts(List<Contact> contacts) {
        mAdapter.repalceData(contacts);
    }

    @Override
    public void showLoadingError() {
        Toast.makeText(getActivity(),"加载列表失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(ContactsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private static class MyExpandableListAdapter extends BaseExpandableListAdapter {

        private List<Contact> mContacts;

        public MyExpandableListAdapter(List<Contact> contacts){
            setlist(contacts);
        }

        public void setlist(List<Contact> list) {
            mContacts = checkNotNull(list);
        }

        public void repalceData(List<Contact> contacts) {
            setlist(contacts);
            notifyDataSetChanged();//通知内容改变重新生成页面
        }

        @Override
        public int getGroupCount() {
            return mContacts.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return mContacts.get(groupPosition).users.size();
        }

        @Override
        public Contact getGroup(int groupPosition) {
            return mContacts.get(groupPosition);
        }

        @Override
        public Contact.User getChild(int groupPosition, int childPosition) {
            return mContacts.get(groupPosition).users.get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View rowView = convertView;
            if (rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                rowView = inflater.inflate(R.layout.expandable_list_group, parent, false);
            }

            final Contact contact = getGroup(groupPosition);

            TextView contactsTitle = (TextView) rowView.findViewById(R.id.contacts_title);
            contactsTitle.setText(contact.getGroup_name());


            return rowView;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View rowView = convertView;
            if (rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                rowView = inflater.inflate(R.layout.expandable_list_child, parent, false);
            }

            final Contact.User user = getChild(groupPosition,childPosition);

            TextView contactsName = (TextView) rowView.findViewById(R.id.contacts_content);
            contactsName.setText(user.getUser_name());

            final CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.checkBox);
            checkBox.setChecked(user.getChecked());
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkBox.isChecked()) {
                        user.toggle();
                        IdList.add(user.getUser_id());
                        nameList.add(user.getUser_name());
                        notifyDataSetChanged();
                    }else{
                        user.toggle();
                        deleteItem(user.getUser_id());
                        notifyDataSetChanged();
                    }
                }
            });
            return rowView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    private static void deleteItem(String temp) {
        for(int i=0; i<IdList.size();i++){
            if(IdList.get(i) == temp){
                IdList.remove(i);
                nameList.remove(i);
            }
        }
    }
}
