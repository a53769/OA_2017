package com.example.shixi_a.myapplication.tasks;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.taskAddEdit.TaskAddEditActivity;
import com.example.shixi_a.myapplication.util.ToastUtils;
import com.example.shixi_a.myapplication.bean.Rows;
import com.example.shixi_a.myapplication.bean.Task;
import com.example.shixi_a.myapplication.model.task.TaskRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Shixi-A on 2017/5/26.
 */

public class TasksPresenter implements TasksContract.Presenter{

    private final TasksContract.View mTasksView;

    private TasksFilterType mCurrentFiltering = TasksFilterType.UNSTART_TASKS;

    private boolean mFirstLoad = true;

    private TaskRepository mTaskRepository;

    private Context context;

    public TasksPresenter(@NonNull TaskRepository taskRepository, @NonNull TasksContract.View tasksView, Context context) {//

        mTaskRepository = checkNotNull(taskRepository, "tasksRepository cannot be null");

        mTasksView = checkNotNull(tasksView, "tasksView cannot be null!");

        mTasksView.setPresenter(this);

        this.context = context;
    }

    @Override
    public void start() {
        loadTasks(false);
    }

    @Override
    public void result(int requestCode, int resultCode) {
        // If a task was successfully added, show snackbar
        if (TaskAddEditActivity.REQUEST_ADD_TASK == requestCode && Activity.RESULT_OK == resultCode) {
            mTasksView.showSuccessfullySavedMessage();
        }
    }

    @Override
    public void addNewTask() {
        mTasksView.showAddTask();
    }

    @Override
    public void loadTasks(boolean forceUpdate) {
        loadTasks(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }


    private void loadTasks(boolean forceUpdate, final boolean showLoadingUI){
        String userID = "";
        String createrID = "";
        String status = "";

        if (showLoadingUI) {
            mTasksView.setLoadingIndicator(true);
        }
        //缓存数据需要更新
        if (forceUpdate) {

          //  mTasksRepository.refreshTasks();
        }

        switch (mCurrentFiltering){
            case UNSTART_TASKS:
                status = "not_started";
                userID = "";
                createrID = "";
                break;
            case PROCESSING_TASKS:
                status = "doing";
                userID = "";
                createrID = "";
                break;
            case PARTICIPATE_TASKS:
                userID = "-1";
                status = "";
                createrID = "";
                break;
            case CREATED_TASKS:
                createrID = "-1";
                userID = "";
                status = "";
                break;
            case COMPLETED_TASKS:
                status = "finished";
                userID = "";
                createrID = "";
                break;
            default:
                break;
        }

        mTaskRepository.getTasks(context,userID,createrID,status, new GsonResponseHandler<Rows<Task>>(){

            @Override
            public void onSuccess(int statusCode, Rows<Task> response) {

                List<Task> tasks ;
                tasks = response.rows;
//                if (!mTasksView.isActive()) {
//                    return;
//                }
//                if (showLoadingUI) {
//                    mTasksView.setLoadingIndicator(false);
//                }
                mTasksView.setLoadingIndicator(false);
                processTasks(tasks);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mTasksView.setLoadingIndicator(false);
                mTasksView.showLoadingTasksError();
            }
        });

    }

    private void processTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            // Show a MessageDataSource indicating there are no tasks for that filter type.
            //mTasksView.showEmptyTask();
            mTasksView.showNoTasks(tasks);
        } else {
            // Show the list of tasks
            mTasksView.showTasks(tasks);
            // Set the filter label's text.
            //showFilterLabel();
        }
    }

    //目前没啥用的函数
    private void showFilterLabel() {
        switch (mCurrentFiltering) {
            case ALL_TASKS:

                break;
            case UNSTART_TASKS:

                break;
            case PROCESSING_TASKS:

                break;
            case PARTICIPATE_TASKS:

                break;
            case CREATED_TASKS:

                break;
            case COMPLETED_TASKS:

                break;
            default:

                break;
        }
    }

    @Override
    public void setFiltering(TasksFilterType currentFilteringKey) {
        mCurrentFiltering = currentFilteringKey;
    }

    @Override
    public TasksFilterType getFiltering() {
        return mCurrentFiltering;
    }

    @Override
    public void openTaskDetails(Task clickedTask) {
        mTasksView.showTaskDetail(clickedTask);
    }

    @Override
    public void DeleteTask(String id) {
        mTaskRepository.deleteTask(context,id, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                ToastUtils.showShort(context,"删除成功");
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"删除失败");
            }
        });
    }

    @Override
    public void EditTask(Task editTask) {
        mTasksView.showEditTask(editTask);
    }


}
