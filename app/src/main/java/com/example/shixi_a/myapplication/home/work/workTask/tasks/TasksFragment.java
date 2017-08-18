package com.example.shixi_a.myapplication.home.work.workTask.tasks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.example.shixi_a.myapplication.bean.Task;
import com.example.shixi_a.myapplication.login.LoginActivity;
import com.example.shixi_a.myapplication.util.ToastUtils;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;
import com.example.shixi_a.myapplication.home.work.workTask.taskAddEdit.TaskAddEditActivity;
import com.example.shixi_a.myapplication.home.work.workTask.taskAddEdit.TaskAddEditFragment;
import com.example.shixi_a.myapplication.home.work.workTask.taskDetail.TaskDetailActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.shixi_a.myapplication.util.StringUtils.SubTime;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Shixi-A on 2017/5/26.
 */

public class TasksFragment extends Fragment implements TasksContract.View {

    private TasksContract.Presenter mPresenter;

    private TasksAdapter mListAdapter;//任务列表适配器——来源内部类

    private View mNoTasksView;//无任务视图

    private TextView mNoTaskAddView;//无任务的添加链接

    private TextView textView;//过滤标题

    private Toolbar toolbar;

    private String[] datas = {"未开始的任务", "进行中的任务" ,"参与的任务", "完成的任务","创建的任务"};

    public TasksFragment() {
        // Requires empty public constructor
    }


    public static TasksFragment newInstance() {
        return new TasksFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new TasksAdapter(new ArrayList<Task>(0), mItemListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //获取根视图
        View root = inflater.inflate(R.layout.task_content, container, false);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        //set up tasks view
        ListView listView = (ListView) root.findViewById(R.id.tasks_list);
        listView.setAdapter(mListAdapter);

        textView = (TextView) getActivity().findViewById(R.id.tasktitle);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu();
            }
        });

        //set up no tasks view
        mNoTasksView = root.findViewById(R.id.noTasks);
        mNoTaskAddView = (TextView) root.findViewById(R.id.noTasksAdd);
        mNoTaskAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showAddTask();            }
        });

        //set up 上一级的浮动按钮
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //分发P层处理添加任务事件
                mPresenter.addNewTask();
                //showAddTask();
            }
        });

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
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
                mPresenter.loadTasks(false);//不强制刷新

            }
        });

        return root;
    }

    //绑定Presenter
    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
//    创建任务的返回结果
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Override
    public void showTasks(List<Task> tasks) {
        mListAdapter.replaceData(tasks);
        mNoTasksView.setVisibility(View.GONE);
    }

    @Override
    public void showPopupMenu() {

        View popupView = getActivity().getLayoutInflater().inflate(R.layout.popupwindow, null);
        final PopupWindow window = new PopupWindow(popupView, 400, 600);//大小
        window.setAnimationStyle(R.style.popup_window_anim);//动画
//        window.setBackgroundDrawable(new ColorDrawable(R.color.defaultBack));//背景色
        window.setFocusable(true);// 设置可以获取焦点
        window.setOutsideTouchable(true); // 设置可以触摸弹出框以外的区域
        window.update();//更新popupwindow的状态
        window.showAsDropDown(toolbar, toolbar.getWidth()/2 - window.getWidth()/2, 0 );// 以下拉的方式显示，并且可以设置显示的位置
        ListView listView = (ListView) popupView.findViewById(R.id.popupList);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.simple_list_item_tight, datas));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(datas[position]);
                switch (position){
                case 0:
                    mPresenter.setFiltering(TasksFilterType.UNSTART_TASKS);
                    break;
                case 1:
                    mPresenter.setFiltering(TasksFilterType.PROCESSING_TASKS);
                    break;
                case 2:
                    mPresenter.setFiltering(TasksFilterType.PARTICIPATE_TASKS);
                    break;
                case 3:
                    mPresenter.setFiltering(TasksFilterType.COMPLETED_TASKS);
                    break;
                case 4:
                    mPresenter.setFiltering(TasksFilterType.CREATED_TASKS);
                    break;
                default:
                    mPresenter.setFiltering(TasksFilterType.ALL_TASKS);
                    break;
            }
                mPresenter.loadTasks(false);
                window.dismiss();
            }
        });

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
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showNoTasks(List<Task> tasks) {
        mListAdapter.replaceData(tasks);
        mNoTasksView.setVisibility(View.VISIBLE);
        showMessage(getString(R.string.loading_null));
    }

    @Override
    public void showLoadingTasksError() {
        showMessage(getString(R.string.loading_tasks_error));
    }

    @Override
    public void showSuccessfullySavedMessage() {
        showMessage("创建任务成功");
    }

    @Override
    public void showTaskDetail(Task task) {
        Intent intent = new Intent(getActivity(),TaskDetailActivity.class);
        intent.putExtra("task_id",task.getId());
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(TaskDetailFragment.ARGUMENT_DETAIL_TASK,task);
//        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showAddTask() {
        Intent intent = new Intent(getContext(), TaskAddEditActivity.class);//创建成功的返回 还没有处理
        startActivityForResult(intent, TaskAddEditActivity.REQUEST_ADD_TASK);
    }

    @Override
    public void showEditTask(Task editTask) {
        Intent intent = new Intent(getActivity(),TaskAddEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(TaskAddEditFragment.ARGUMENT_EDIT_TASK, editTask);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showLogin() {
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

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    //任务列表适配器
    private static class TasksAdapter extends BaseAdapter{

        private List<Task> mTasks;
        private TaskItemListener mItemListener;

        public TasksAdapter(List<Task> tasks, TaskItemListener itemListener) {
            setList(tasks);
            mItemListener = itemListener;
        }

        public void replaceData(List<Task> tasks) {
            setList(tasks);
            notifyDataSetChanged();
        }

        private void setList(List<Task> tasks) {
            mTasks = checkNotNull(tasks);
        }

        @Override
        public int getCount() {
            return mTasks.size();
        }

        @Override
        public Task getItem(int position) {
            return mTasks.get(position);
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
                rowView = inflater.inflate(R.layout.list_item_task, parent, false);
            }

            final Task task = getItem(position);

            int color = getColor(task.getStatus());
            int res = getImageSrc(task.getStatus());

            //修改lab颜色
            View lab = rowView.findViewById(R.id.lab);
            GradientDrawable drawable = (GradientDrawable) lab.getBackground();
            drawable.setColor(color);

            //修改标签图片
            ImageView mImageLab = (ImageView) rowView.findViewById(R.id.im_lab);
            mImageLab.setImageResource(res);

            TextView titleTV = (TextView) rowView.findViewById(R.id.task_name);
            titleTV.setText(task.getTitle());

            TextView descriptionTV = (TextView) rowView.findViewById(R.id.task_timestamp);
            descriptionTV.setText(task.getCreater_name() + "  发布于" + SubTime(task.getCreate_time()));

            ImageView editTask = (ImageView) rowView.findViewById(R.id.task_edit);
            editTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onEditTaskClick(task);
                }
            });

            ImageView deleteTask = (ImageView) rowView.findViewById(R.id.task_delete);
            deleteTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onDelTaskClick(task);
                }
            });

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemListener.onTaskClick(task);
                }
            });

            return rowView;
        }

        private int getImageSrc(String status) {
            int res;
            switch (status){
                case "-1"://编辑中
                    res = R.drawable.state_2;
                    break;
                case "1"://等待接收
                    res = R.drawable.status_no_accept;
                    break;
                case "3"://未开始
                    res = R.drawable.status_doing;
                    break;
                case "4"://进行中
                    res = R.drawable.status_doing;
                    break;
                case "5"://暂停
                    res = R.drawable.status_pause;
                    break;
                case "6"://已拒绝
                    res = R.drawable.status_refuse_accept;
                    break;
                case "7"://已取消
                    res = R.drawable.status_end_task;
                    break;
                case "8"://待验收
                    res = R.drawable.status_no_check;
                    break;
                case "9"://待评分
                    res = R.drawable.status_no_rate;
                    break;
                case "10"://完成
                    res = R.drawable.status_done;
                    break;
                default:
                    res = R.drawable.status_no_accept;
                    break;
            }
            return res;
        }

        private int getColor(String status) {
            int color;
            switch (status){
                case "-1"://编辑中
                    color = 0xff13CA00;
                    break;
                case "1"://等待接收
                    color = 0xff3A9BEE;
                    break;
                case "3"://未开始
                    color = 0xff13CA00;
                    break;
                case "4"://进行中
                    color = 0xff13CA00;
                    break;
                case "5"://暂停
                    color = 0xffE94646;
                    break;
                case "6"://已拒绝
                    color = 0xffEF69D4;
                    break;
                case "7"://已取消
                    color = 0xffADADAD;
                    break;
                case "8"://待验收
                    color = 0xffFFC001;
                    break;
                case "9"://待评分
                    color = 0xffFFC001;
                    break;
                case "10"://完成
                    color = 0xffFFC001;
                    break;
                default:
                    color = 0xffFFC001;
                    break;
            }
            return color;
        }
    }

    /**
     * Listener for clicks on tasks in the ListView.
     */
    TaskItemListener mItemListener = new TaskItemListener() {
        @Override
        public void onTaskClick(Task clickedTask) {
            mPresenter.openTaskDetails(clickedTask);
        }

        @Override
        public void onEditTaskClick(Task editTask) {
            mPresenter.EditTask(editTask);
        }

        @Override
        public void onDelTaskClick(Task delTask) {
            if(hasOpt(delTask.getOpts(),"cancel")) {
                mPresenter.DeleteTask(delTask.getId());
            }else{
                ToastUtils.showShort(getContext(),"没有权限");
            }
        }
    };

    private boolean hasOpt(List<String> opts, String opt) {
        if(opts.contains(opt))
            return true;
        return false;
    }

    //任务点击事件监听接口
    public interface TaskItemListener {

        void onTaskClick(Task clickedTask);

        void onEditTaskClick(Task editTask);

        void onDelTaskClick(Task delTask);
    }
}
