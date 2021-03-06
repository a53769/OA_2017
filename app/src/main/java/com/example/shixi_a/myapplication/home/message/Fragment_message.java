package com.example.shixi_a.myapplication.home.message;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.Message;
import com.example.shixi_a.myapplication.login.LoginActivity;
import com.example.shixi_a.myapplication.model.message.MessageRepository;
import com.example.shixi_a.myapplication.home.message.scan.MyCaptureActivity;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;
import com.example.shixi_a.myapplication.home.work.workAdministration.checkOut.CheckOutActivity;
import com.example.shixi_a.myapplication.home.work.workAdministration.entertainmentReimburseDetail.EntertainmentReimburseDetailActivity;
import com.example.shixi_a.myapplication.home.work.workAdministration.leaveDetail.LeaveDetailActivity;
import com.example.shixi_a.myapplication.home.work.workAdministration.normalReimburseDetail.NormalReimburseDetailActivity;
import com.example.shixi_a.myapplication.home.work.workAdministration.trafficReimburseDetail.TrafficReimburseDetailActivity;
import com.example.shixi_a.myapplication.home.work.workAdministration.tripReimburseDetail.TripReimburseDetailActivity;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by Shixi-A on 2017/5/17.
 */

public class Fragment_message extends Fragment implements MessageContract.View{

    private MessageContract.Presenter mPresenter;
    private MessageAdapter mAdapter;
    public static final int REQUEST_CODE = 12;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode, data);
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mAdapter = new MessageAdapter(new ArrayList<Message>(0));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_message, container, false);

        MessageRepository mRepository = new MessageRepository();

        mPresenter = new MessagePresenter(mRepository,this, getContext());

        TextView scanQRCode = (TextView) root.findViewById(R.id.scan_qrcode);
        scanQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyCaptureActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        ListView listView = (ListView) root.findViewById(R.id.message_list);
        listView.setAdapter(mAdapter);

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
    public void setPresenter(MessageContract.Presenter presenter) {
        mPresenter = presenter;
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
    public void showMessage(List<Message> messages) {
        mAdapter.replaceData(messages);
    }

    @Override
    public void showReimburseDetail(String id, String type) {
        switch (type){
            case "2":
                Intent intent1 = new Intent(getActivity(), TrafficReimburseDetailActivity.class);
                intent1.putExtra("id",id);
                startActivity(intent1);
                break;
            case "3":
                Intent intent2 = new Intent(getActivity(), EntertainmentReimburseDetailActivity.class);
                intent2.putExtra("id",id);
                startActivity(intent2);
                break;
            case "4":
                Intent intent3 = new Intent(getActivity(), TripReimburseDetailActivity.class);
                intent3.putExtra("id",id);
                startActivity(intent3);
                break;
            default:
                Intent intent = new Intent(getActivity(), NormalReimburseDetailActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
        }
    }

    @Override
    public void showLogin() {
        //注销登录权限
        SharedPreferences sp;
        SharedPreferences.Editor editor;
        sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sp.edit();
        editor.putBoolean("main",false);
        editor.apply();

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private class MessageAdapter extends BaseAdapter{

        List<Message> mList;
        public MessageAdapter(List<Message> messages) {
            setList(messages);
        }

        private void setList(List<Message> list) {
            mList = checkNotNull(list);
        }

        public void replaceData(List<Message> messages) {
            setList(messages);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Message getItem(int position) {
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
            rowView = inflater.inflate(R.layout.list_item_message, parent, false);


            final Message message = getItem(position);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.Im_title);
            TextView name = (TextView) rowView.findViewById(R.id.name);
            name.setText(message.getTitle());
            TextView content = (TextView) rowView.findViewById(R.id.content);
            content.setText(message.getMsg());

            switch (message.getTab_name()){
                case "oa_off":
                    imageView.setImageResource(R.drawable.qingjia);
                    rowView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showLeaveDetail(message.getTab_id());
                        }
                    });
                    break;
                case "oa_adm_out":
                    imageView.setImageResource(R.drawable.waichu);
                    rowView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showcheckOut(message.getTab_id(),message.getTab_name());
                        }
                    });
                    break;
                case "oa_reimburse":
                    imageView.setImageResource(R.drawable.baoxiao);
                    rowView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getReimburseDetail(message.getTab_id());
                        }
                    });
                    break;
                case "miit_command":
                    imageView.setImageResource(R.drawable.notify);
                    rowView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showDialog(message.getTitle(),message.getMsg());
                        }
                    });

                    break;
                default:
                    imageView.setImageResource(R.drawable.notify);
                    rowView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    break;
            }
            return rowView;
        }


    }

    private void showDialog(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg + "。因手机暂不支持该功能，请到web上进行操作。");
        builder.setTitle(title);
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
}

    private void getReimburseDetail(String id) {
        mPresenter.getReimburseDetail(id);//因为拆开写详情页 现在也懒得改了 只能先获取一次 看看是啥类别
    }

    private void showcheckOut( String tab_id,String tabName) {
        Intent intent = new Intent(getActivity(), CheckOutActivity.class);
        intent.putExtra("id",tab_id);
        intent.putExtra("name",tabName);
        startActivity(intent);
    }

    private void showLeaveDetail(String tab_id) {
        Intent intent = new Intent(getActivity(), LeaveDetailActivity.class);
        intent.putExtra("id",tab_id);
        startActivity(intent);
    }
}
