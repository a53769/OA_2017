package com.example.shixi_a.myapplication.work.workTask.projectAddEdit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.util.LogUtils;
import com.example.shixi_a.myapplication.util.ToastUtils;
import com.example.shixi_a.myapplication.bean.Project;
import com.example.shixi_a.myapplication.model.project.ProjectDataSource;
import com.example.shixi_a.myapplication.model.project.ProjectRepository;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by a5376 on 2017/6/23.
 */

public class ProjectAddEditPresenter implements ProjectAddEditContract.Presenter {

    @NonNull
    private final ProjectDataSource mProjectRepository;

    @NonNull
    private final ProjectAddEditContract.View mAddProjectView;

    @Nullable
    private Project mProject;

    private Context context;

    public ProjectAddEditPresenter(Project project, ProjectRepository mRepository, ProjectAddEditFragment projectAddEditFragment, Context context) {
        mProjectRepository = checkNotNull(mRepository);
        mAddProjectView = checkNotNull(projectAddEditFragment);
        mProject = project;
        mAddProjectView.setPresenter(this);
        this.context = context;
    }

    @Override
    public void start() {
        if(!isNewProject()){
            onProjectLoad();
        }else {
            mAddProjectView.setText();
        }
    }

    private void onProjectLoad() {
        mAddProjectView.setTitle(mProject.getProject_name());
        mAddProjectView.setContent(mProject.getDescription());
        mAddProjectView.setEdit();
    }


    public boolean isNewProject() {
        if(mProject == null) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void submit(String description) {
        if(isNewProject()){
            mProjectRepository.addProject(context, mAddProjectView.getTitle(), description, new RawResponseHandler() {
                @Override
                public void onSuccess(int statusCode, String response) {
                    LogUtils.v("创建成功");
                    mAddProjectView.showProjects();
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    ToastUtils.showShort(context, "创建失败");
                }
            });
        }else {
            mProjectRepository.editProject(context, mProject.getId(),description, new RawResponseHandler() {
                @Override
                public void onSuccess(int statusCode, String response) {
                    ToastUtils.showShort(context, "修改成功");
                    mAddProjectView.showProjects();
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {

                }
            });
        }
    }


}
