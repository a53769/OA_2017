package com.example.shixi_a.myapplication.work.workTask.taskDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.model.task.TaskRepository;
import com.example.shixi_a.myapplication.util.ActivityUtils;

/**
 * Created by Shixi-A on 2017/6/9.
 */

public class TaskDetailActivity extends AppCompatActivity {

    private TaskDetailPresenter mPresenter;

    private TaskRepository mRepository;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

//        Task task = (Task) getIntent().getSerializableExtra(TaskDetailFragment.ARGUMENT_DETAIL_TASK);
        String taskId = getIntent().getStringExtra("task_id");

        TaskDetailFragment taskDetailFragment = (TaskDetailFragment) getSupportFragmentManager().findFragmentById(R.id.task_detail_contentFrame);

        if(taskDetailFragment == null){
            taskDetailFragment = TaskDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),taskDetailFragment,R.id.task_detail_contentFrame);
        }

        mRepository = new TaskRepository();

        mPresenter = new TaskDetailPresenter(taskId,mRepository,taskDetailFragment,getApplicationContext());

    }



}
