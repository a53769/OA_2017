package com.example.shixi_a.myapplication.work.workTask.tasks;

/**
 * Created by Shixi-A on 2017/5/26.
 */
import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.Task;

import java.util.List;

public interface TasksContract {

    interface View extends BaseView<Presenter> {

        void showTasks(List<Task> tasks);//加载任务列表

        void showAddTask();//跳转添加任务界面

        void showPopupMenu();//弹出过滤器列表

        void setLoadingIndicator(final boolean active);//下拉刷新动画显示

        boolean isActive();// The view may not be able to handle UI updates anymore目前没错，不用

        void showNoTasks(List<Task> tasks);//显示空任务列表界面

        void showLoadingTasksError();//snackbar显示加载失败

        void showSuccessfullySavedMessage();

        void showTaskDetail(Task task);

        void showEditTask(Task editTask);

        void showLogin();
    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode);//远程请求回调结果，目前没有用

        void addNewTask();//新增任务，跳转

        void loadTasks(boolean forceUpdate);//获取远程数据，加载任务列表 不对缓存进行刷新

        void setFiltering(TasksFilterType currentFilteringKey);//设定过滤器

        TasksFilterType getFiltering();//获得过滤类型


        void openTaskDetails(Task clickedTask);

        void DeleteTask(String id);

        void EditTask(Task editTask);
    }

}
