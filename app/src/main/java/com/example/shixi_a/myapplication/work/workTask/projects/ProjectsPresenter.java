package com.example.shixi_a.myapplication.work.workTask.projects;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.util.ToastUtils;
import com.example.shixi_a.myapplication.bean.Project;
import com.example.shixi_a.myapplication.bean.Rows;
import com.example.shixi_a.myapplication.model.project.ProjectRepository;

import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by Shixi-A on 2017/6/7.
 */

public class ProjectsPresenter implements ProjectsContract.Presenter {

    private final ProjectsContract.View mProjectsView;

    private ProjectRepository mProjectRepository;

    private boolean mFirstLoad = true;

    private Context context;


    public ProjectsPresenter(@NonNull ProjectRepository projectRepository, @NonNull ProjectsContract.View projectsView, Context context) {//

        mProjectRepository = checkNotNull(projectRepository, "tasksRepository cannot be null");

        mProjectsView = checkNotNull(projectsView, "tasksView cannot be null!");

        mProjectsView.setPresenter(this);

        this.context = context;
    }

    @Override
    public void start() {
        loadProjects(false);
    }

    @Override
    public void loadProjects(boolean forceUpdate) {
        loadProjects(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }


    private void loadProjects(boolean forceUpdate, final boolean showLoadingUI){

        if (showLoadingUI) {
            mProjectsView.setLoadingIndicator(true);
        }
        //缓存数据需要更新
        if (forceUpdate) {

            //  mTasksRepository.refreshTasks();
        }

        mProjectRepository.getProjects(context, new GsonResponseHandler<Rows<Project>>() {
            @Override
            public void onSuccess(int statusCode, Rows<Project> response) {
                List<Project> projects;
                projects = response.rows;
                mProjectsView.setLoadingIndicator(false);
                processProjects(projects);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

                mProjectsView.showLoadingProjectsError();
                mProjectsView.setLoadingIndicator(false);
            }
        });
    }

    private void processProjects(List<Project> projects) {
        if (projects.isEmpty()){
            mProjectsView.showNoProjects();
        }
        else{
            mProjectsView.showProjects(projects);
        }
    }

    @Override
    public void addNewProject() {
        mProjectsView.showAddProject();
    }

    @Override
    public void selectedProject(@NonNull Project clickedProject) {
        checkNotNull(clickedProject, "requestedProject cannot be null!");
        mProjectsView.showTaskAdd(clickedProject.getId(),clickedProject.getProject_name());
    }

    @Override
    public void EditProject(Project editProject) {
        mProjectsView.showEditProject(editProject);
    }

    @Override
    public void deletProject(String id) {
        mProjectRepository.deletProject(context, id, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                ToastUtils.showShort(context,"删除成功");
                loadProjects(false);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"删除失败");
            }
        });
    }

}
