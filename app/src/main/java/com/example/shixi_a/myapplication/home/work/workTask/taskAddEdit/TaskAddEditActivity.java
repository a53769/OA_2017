package com.example.shixi_a.myapplication.home.work.workTask.taskAddEdit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.util.ActivityUtils;
import com.example.shixi_a.myapplication.bean.Task;
import com.example.shixi_a.myapplication.model.task.TaskRepository;

/**
 * Created by Shixi-A on 2017/5/18.
 */

public class TaskAddEditActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_TASK = 1000;

    private TaskAddEditPresenter mPresenter;

    private TaskRepository taskRepository;

    private TextView task_add_title;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        task_add_title = (TextView) findViewById(R.id.tasktitle);

        Task task = (Task) getIntent().getSerializableExtra(TaskAddEditFragment.ARGUMENT_EDIT_TASK);

        setToolbarTitle(task);

        TaskAddEditFragment taskAddEditFragment = (TaskAddEditFragment) getSupportFragmentManager().findFragmentById(R.id.task_add);

        if(taskAddEditFragment == null){
            taskAddEditFragment = TaskAddEditFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), taskAddEditFragment, R.id.task_add);
        }
        taskRepository = new TaskRepository();

        mPresenter = new TaskAddEditPresenter(task, taskRepository, taskAddEditFragment, getApplicationContext());

    }


    public void setToolbarTitle(Task task) {
        if(task == null){
            task_add_title.setText("创建任务");
        }else{
            task_add_title.setText("编辑任务");
        }
    }
}
