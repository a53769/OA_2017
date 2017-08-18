package com.example.shixi_a.myapplication.home.work.workTask.projects;

import com.example.shixi_a.myapplication.base.BasePresenter;
import com.example.shixi_a.myapplication.base.BaseView;
import com.example.shixi_a.myapplication.bean.Project;

import java.util.List;

/**
 * Created by Shixi-A on 2017/6/7.
 */

public interface ProjectsContract {
    interface View extends BaseView<Presenter> {

        void showProjects(List<Project> projects);//加载项目列表

        void showAddProject();//跳转添加项目

        void showNoProjects();//显示没有项目

        void showLoadingProjectsError();//显示加载项目列表失败

        void setLoadingIndicator(final boolean active);

        void showTaskAdd(String id, String name);//结束当前返回任务创建界面

        void showEditProject(Project editProject);
    }
    interface Presenter extends BasePresenter {

        void loadProjects(boolean forceUpdate);

        void addNewProject();

        void selectedProject(Project clickedProject);

        void EditProject(Project editProject);


        void deletProject(String id);
    }
}
