package com.example.shixi_a.myapplication.home.work.workTask.taskAddEdit;

import android.content.Intent;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;

import java.util.Map;


/**
 * Created by Shixi-A on 2017/6/9.
 */

public interface TaskAddEditContract {

    interface View extends BaseView<Presenter> {

        void showProjects();//跳转项目列表

        void setProName(String name);//为TextView赋值

        void showClassifys();//跳转分类列表

        void setClaName(String proName);//为TextView赋值

        void showProcesses(String taskId);//跳转流程列表，首跳为空

        void showPrioritys(Map<String, String> map);//显示优先级列表

        void showTasks();

        void setText(String project_name, String pro_name, String type);

        void initView(String title, String target_desc, String exec_method, String estimate_stime, String priority_show, String memo);

        void showMileStone(String proId);

        void setStoneName(String stoneName);
    }

    interface Presenter extends BasePresenter {

        void showProjects();

        void showPrioritys();

        void result(int requestCode, int resultCode, Intent data);//通过Intent回调的参数处理

        void showClassifys();

        void showProcesses();

        void submit(String title, String goal, String method, String stime, String memo);//提交任务

        void showMilestone();

        void setPriority(String priorityId);
    }
}
