package com.example.shixi_a.myapplication.work.workTask.taskDetail;

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
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.czp.library.ArcProgress;
import com.czp.library.OnTextCenter;
import com.example.shixi_a.myapplication.work.workTask.contacts.ContactsActivity;
import com.example.shixi_a.myapplication.work.workTask.process.ProcessActivity;
import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.work.workTask.score.ScoreActivity;
import com.example.shixi_a.myapplication.work.workTask.tempoUpdate.TempoUpdateActivity;
import com.example.shixi_a.myapplication.util.ScrollViewUtil;
import com.example.shixi_a.myapplication.bean.Assessment;
import com.example.shixi_a.myapplication.bean.Logs;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static com.example.shixi_a.myapplication.util.StringUtils.getSubDate;

/**
 * Created by a5376 on 2017/6/28.
 */

public class TaskDetailFragment extends Fragment implements TaskDetailContract.View {

    public static final String ARGUMENT_DETAIL_TASK = "TASK_DETAIL";

    private TaskDetailContract.Presenter mPresenter;

    private MyExpandableListAdapter adapter;

    private LinearLayout process;

    private TextView title;

    private TextView goal;

    private TextView method;

    private TextView creater;

    private TextView time;

    private ExpandableListView listView;

    private RelativeLayout lv_Assess;

    private LinearLayout lv_Button;

    private ScrollView scrollView;

    private TextView translate;

    private TextView toolbar_title;

    public TaskDetailFragment(){}

    public static TaskDetailFragment newInstance() {
        return new TaskDetailFragment();
    }

    @Override
    public void setPresenter(TaskDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
         mPresenter.result(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        adapter = new MyExpandableListAdapter(new ArrayList<Logs>(0));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.task_detail_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        toolbar_title = (TextView) getActivity().findViewById(R.id.task_detail_title);

        translate = (TextView) getActivity().findViewById(R.id.translation);

        title = (TextView) root.findViewById(R.id.textView6);
        goal = (TextView) root.findViewById(R.id.textView8);
        method = (TextView) root.findViewById(R.id.textView10);
        creater = (TextView) root.findViewById(R.id.textView);
        time = (TextView) root.findViewById(R.id.textView4);

        process = (LinearLayout) root.findViewById(R.id.process);
        process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showProcess();
            }
        });

        listView = (ExpandableListView) root.findViewById(R.id.logs_List);
        listView.setGroupIndicator(null);
        listView.setAdapter(adapter);
        listView.setOnGroupExpandListener(new onGroupExpandListenerImpl());
        listView.setOnGroupCollapseListener(new onGroupCollapseListenerImpl());

        lv_Assess = (RelativeLayout) root.findViewById(R.id.score);
        scrollView = (ScrollView) root.findViewById(R.id.task_detail_content);

        lv_Button = (LinearLayout) root.findViewById(R.id.buttonView);

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.task_detail_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(scrollView);

        swipeRefreshLayout.setOnRefreshListener(new android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {mPresenter.start();
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
                (SwipeRefreshLayout) getView().findViewById(R.id.task_detail_refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void setLogs(List<Logs> logs) {
        adapter.repalceData(logs);
    }

    @Override
    public void showAssess(Assessment assess) {
        lv_Assess.setVisibility(View.VISIBLE);
        RatingBar quanlity = (RatingBar) lv_Assess.findViewById(R.id.ratingBar);
        RatingBar efficient = (RatingBar) lv_Assess.findViewById(R.id.ratingBar1);
        RatingBar attitude = (RatingBar) lv_Assess.findViewById(R.id.ratingBar2);
        quanlity.setProgress(Integer.parseInt(assess.getQuality()));
        efficient.setProgress(Integer.parseInt(assess.getEfficient()));
        attitude.setProgress(Integer.parseInt(assess.getAttitude()));
        TextView memo = (TextView) lv_Assess.findViewById(R.id.memo_content);
        memo.setText(assess.getMemo());
    }

    @Override
    public void showNoAccessView() {//未接受
        translate.setVisibility(View.VISIBLE);
        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContacts();
            }
        });
        showPopup(1);
    }

    private void showContacts() {
        Intent intent = new Intent(getActivity(), ContactsActivity.class);
        startActivityForResult(intent,ContactsActivity.REQUEST_CONTACTS_CODE);
    }

    @Override
    public void showNoAssessView() {
        showPopup(2);
    }//未审核

    @Override
    public void showDoing() {//进行中
        showPopup(3);
    }//进行中

    @Override
    public void showNoScoreView() {//未评分
        showPopup(4);
    }//未评价

    @Override
    public void showScore(String taskId) {
        Intent intent = new Intent(getActivity(), ScoreActivity.class);
        intent.putExtra(ScoreActivity.ARGUMENT_TASK_ID,taskId);
        startActivity(intent);
        getActivity().finish();
    }//跳转评价

    @Override
    public void showTasks() {
        getActivity().finish();
    }

    @Override
    public void refuseProcess(String id) {
        Intent intent = new Intent(getActivity(),ProcessActivity.class);
        intent.putExtra(ProcessActivity.EXTRA_TASK_ID,id);
        intent.putExtra("refuse", id);
        startActivityForResult(intent,ProcessActivity.REQUEST_PROCESS_CODE);
    }

    @Override
    public void showTaskDetail() {
        getActivity().finish();
    }

    @Override
    public void showNoOpt() {
        lv_Button.setVisibility(View.GONE);
    }

    @Override
    public void showUpdateTempo(String taskId, String tempo) {
        Intent intent = new Intent(getActivity(), TempoUpdateActivity.class);
        intent.putExtra(TempoUpdateActivity.ARGUMENT_TASK_ID, taskId);
        intent.putExtra(TempoUpdateActivity.ARGUMENT_TEMPO, tempo);
        startActivity(intent);
    }

    private void showPopup(int i) {//其实只是一个嵌套在帧布局的线性界面
        lv_Button.setVisibility(View.VISIBLE);
        Button bt_first = (Button) lv_Button.findViewById(R.id.button);
        Button bt_second = (Button) lv_Button.findViewById(R.id.button1);
        bt_first.setVisibility(View.VISIBLE);
        bt_second.setVisibility(View.VISIBLE);
        switch (i){
            case 1:
                bt_second.setText("接收");
                bt_second.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.dealTask("accept");
                    }
                });
                bt_first.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.dealTask("refuse");
                    }
                });
                break;
            case 2:
                bt_first.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.refuseProcess();
                        mPresenter.acceptanceTask("refuse");
                    }
                });
                bt_second.setText("通过");
                bt_second.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.acceptanceTask("pass");
                    }
                });
                break;
            case 3:
                if(mPresenter.getTempo().equals("100")){
                    lv_Button.setVisibility(View.GONE);
                }else {
                    bt_second.setText("更新");
                    bt_first.setVisibility(View.GONE);
                    bt_second.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {mPresenter.showUpdateTempo();
                        }
                    });
                }
                break;
            case 4:
                bt_second.setText("评价");
                bt_first.setVisibility(View.GONE);
                bt_second.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.showScore();
                        mPresenter.showScore();
                    }
                });
        }

    }

    @Override
    public void initView(String Title, String Goal, String exec_method, String creater_name, String create_time, String status) {
        title.setText(Title);
        goal.setText(Goal);
        method.setText(exec_method);
        creater.setText(creater_name);
        time.setText("发布于" + create_time);
        setToolbar(status);
    }

    private void setToolbar(String status) {
        switch(status){
            case "-1"://编辑中
                toolbar_title.setText("编辑中");
                break;
            case "1"://待接收
                toolbar_title.setText("待接收");
                break;
            case "3"://进行中
                toolbar_title.setText("进行中");
                break;
            case "4"://进行中
                toolbar_title.setText("进行中");
                break;
            case "5"://暂停
                toolbar_title.setText("暂停");
                break;
            case "6"://拒绝
                toolbar_title.setText("拒绝");
                break;
            case "7"://终止
                toolbar_title.setText("终止");
                break;
            case "8"://待验收
                toolbar_title.setText("待验收");
                break;
            case "9"://待评分
                toolbar_title.setText("待评分");
                break;
            case "10"://完成
                toolbar_title.setText("完成");
                break;
            default:
                break;
        }

    }
    @Override
    public void showProcess(String id) {
        Intent intent = new Intent(getActivity(), ProcessActivity.class);
        intent.putExtra(ProcessActivity.EXTRA_TASK_ID, id);
        if(mPresenter.getStatus().equals("10")) {
            intent.putExtra("detail", id);
        }
        startActivity(intent);
    }

    private void refreshchildView() {
        listView.collapseGroup(0);
        listView.expandGroup(0);
    }

    private class MyExpandableListAdapter extends BaseExpandableListAdapter {

        private List<Logs> mLogs;

        public MyExpandableListAdapter(List<Logs> logs){
            setlist(logs);
        }

        public void setlist(List<Logs> list) {
            mLogs = checkNotNull(list);
        }

        public void repalceData(List<Logs> logs) {
            setlist(logs);
            notifyDataSetChanged();//通知内容改变重新生成页面 目前没有效果需要重新展开页来刷新
            refreshchildView();
        }



        @Override
        public int getGroupCount() {
            return 1;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return mLogs.size();
        }

        @Override
        public String getGroup(int groupPosition) {
            return "任务日志";
        }

        @Override
        public Logs getChild(int groupPosition, int childPosition) {
            return mLogs.get(childPosition);
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
                rowView = inflater.inflate(R.layout.log_title, parent, false);
            }
            return rowView;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View rowView = convertView;
            if (rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                rowView = inflater.inflate(R.layout.task_log_list_item, parent, false);
            }

            final Logs log = getChild(groupPosition,childPosition);

            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView content = (TextView) rowView.findViewById(R.id.content);
            TextView time = (TextView) rowView.findViewById(R.id.time);
            title.setText(log.getUser_name() + log.getAction_show());
            content.setText(log.getMemo());
            time.setText(getSubDate(log.getIn_time()));

            ArcProgress progressBar = (ArcProgress) rowView.findViewById(R.id.progrssbar);
            progressBar.setProgress(Integer.parseInt(log.getTempo()));
            progressBar.setOnCenterDraw(new OnTextCenter());
            return rowView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    private class onGroupExpandListenerImpl implements ExpandableListView.OnGroupExpandListener {
        public void onGroupExpand(int groupPosition) {
            ScrollViewUtil.setListViewHeightBasedOnChildren(listView);
        }
    }

    private class onGroupCollapseListenerImpl implements ExpandableListView.OnGroupCollapseListener {
        public void onGroupCollapse(int groupPosition) {
            ScrollViewUtil.setListViewHeightBasedOnChildren(listView);

        }
    }

//    LogItemListener mItemListener = new LogItemListener() {
//        @Override
//        public void onLogClick(Logs clickedLog) {
////            mPresenter.selectedLog(clickedLog);
//        }
//    };
//
//    public interface LogItemListener {
//
//        void onLogClick(Logs clickedLog);
//
//    }
}
