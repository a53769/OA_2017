package com.example.shixi_a.myapplication.egress;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.applyout.ApplyOutActivity;
import com.example.shixi_a.myapplication.bean.Egress;
import com.example.shixi_a.myapplication.egressDetail.EgressDetailActivity;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by a5376 on 2017/7/11.
 */

public class EgressFragment extends Fragment implements EgressContract.View {

    private EgressContract.Presenter mPresenter;
    private EgressAdapter mListAdapter;
    private Toolbar toolbar;
    private TextView title;
    private String[] datas = {"我的记录", "团队记录"};

    public EgressFragment(){}

    public static EgressFragment newInstance(){
        return new EgressFragment();
    }

    @Override
    public void setPresenter(EgressContract.Presenter presenter) {
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
        View root = inflater.inflate(R.layout.egress_content, container, false);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        title = (TextView) getActivity().findViewById(R.id.out_title);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });

        TextView apply = (TextView) getActivity().findViewById(R.id.apply_out);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ApplyOutActivity.class);
                startActivity(intent);
            }
        });

        ListView listView = (ListView) root.findViewById(R.id.out_list);
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
    public void showEgress(List<Egress> egresses) {
        mListAdapter.replaceData(egresses);
    }

    @Override
    public void showEditEgress(Egress editEgress) {
        Intent intent = new Intent(getActivity(),ApplyOutActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("egress", editEgress);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showDetailEgress(Egress clickedEgress) {
        Intent intent = new Intent(getActivity(),EgressDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("DETAIL_EGRESS",clickedEgress);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void showPopupWindow() {
        View popupView = getActivity().getLayoutInflater().inflate(R.layout.popupwindow, null);
        final PopupWindow window = new PopupWindow(popupView, 350, 350);//大小
        window.setAnimationStyle(R.style.popup_window_anim);//动画
//        window.setBackgroundDrawable(new ColorDrawable(R.color.defaultBack));//背景色
        window.setFocusable(true);// 设置可以获取焦点
        window.setOutsideTouchable(true); // 设置可以触摸弹出框以外的区域
        window.update();//更新popupwindow的状态
        window.showAsDropDown(toolbar, toolbar.getWidth()/2 - window.getWidth()/2, 0 );// 以下拉的方式显示，并且可以设置显示的位置
        ListView listView = (ListView) popupView.findViewById(R.id.popupList);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.simple_list_item, datas));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                title.setText(datas[position]);
                switch (position){
                    case 0:
                        mPresenter.setFilter("admin");
                        break;
                    case 1:
                        mPresenter.setFilter("group");
                        break;
                    default:
                        break;
                }
                mPresenter.loadEgress();
                window.dismiss();
            }
        });
    }

    private static class EgressAdapter extends BaseAdapter{

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
                rowView = inflater.inflate(R.layout.list_item_out, parent, false);
            }

            final Egress egress = getItem(position);
            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView address = (TextView) rowView.findViewById(R.id.address);
            TextView person = (TextView) rowView.findViewById(R.id.person);
            title.setText(egress.getAdminname() + "  " + egress.getOut_time());
            if(egress.getStatus().equals("1")||egress.getStatus().equals("2")){
                if (egress.getAddr() == ""||egress.getAddr()==null) {//有时间可改写成函数这样太乱了
                    address.setText("暂无");
                } else {
                    address.setText(egress.getAddr());
                }
            }else{
                if (egress.getEvent_addr() == ""||egress.getEvent_addr()==null) {//有时间可改写成函数这样太乱了
                    address.setText("暂无");
                } else {
                    address.setText(egress.getEvent_addr());
                }
            }
            if (egress.getUsername() == "" || egress.getUsername() == null) {
                person.setText("暂无");
            } else{
                person.setText(egress.getUsername());
            }

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onEgressClick(egress);
                }
            });

            ImageView delete = (ImageView) rowView.findViewById(R.id.out_delete);
            ImageView edit = (ImageView) rowView.findViewById(R.id.out_edit);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onDelEgressClick(egress);
                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onEditEgressClick(egress);
                }
            });
            return rowView;
        }

    }


    EgressItemListener mItemListener = new EgressItemListener() {

        @Override
        public void onEgressClick(Egress clickedEgress) {
            mPresenter.selectedEgress(clickedEgress);
        }

        @Override
        public void onEditEgressClick(Egress editEgress) {
            mPresenter.editEgress(editEgress);
        }

        @Override
        public void onDelEgressClick(Egress delEgress) {
            mPresenter.deleteEgress(delEgress);
        }
    };

    public interface EgressItemListener {

        void onEgressClick(Egress clickedEgress);

        void onEditEgressClick(Egress editEgress);

        void onDelEgressClick(Egress delEgress);
    }
}
