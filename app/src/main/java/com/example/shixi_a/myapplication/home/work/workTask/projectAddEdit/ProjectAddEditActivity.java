package com.example.shixi_a.myapplication.home.work.workTask.projectAddEdit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.util.ActivityUtils;
import com.example.shixi_a.myapplication.bean.Project;
import com.example.shixi_a.myapplication.model.project.ProjectRepository;

/**
 * Created by Shixi-A on 2017/6/7.
 */

public class ProjectAddEditActivity extends AppCompatActivity  {

    private ProjectAddEditPresenter mPresenter;
    private ProjectRepository mRepository;
    private TextView toolbar_title;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_add);

        toolbar_title = (TextView) findViewById(R.id.pro_add_title);
        ProjectAddEditFragment projectAddEditFragment = (ProjectAddEditFragment) getSupportFragmentManager().findFragmentById(R.id.project_add_content);

        Project project = (Project) getIntent().getSerializableExtra(ProjectAddEditFragment.ARGUMENT_EDIT_PROJECT);

        setToolbarTitle(project);

        if(projectAddEditFragment == null){
            projectAddEditFragment = ProjectAddEditFragment.newInstance();
            if(getIntent().hasExtra(ProjectAddEditFragment.ARGUMENT_EDIT_PROJECT)){
                Bundle bundle = new Bundle();
                bundle.putSerializable(ProjectAddEditFragment.ARGUMENT_EDIT_PROJECT, project);
                projectAddEditFragment.setArguments(bundle);
            }
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),projectAddEditFragment,R.id.project_add_content);
        }

        mRepository = new ProjectRepository();

        mPresenter = new ProjectAddEditPresenter(project, mRepository, projectAddEditFragment, getApplicationContext());
    }

    public void setToolbarTitle(Project project) {
        if(project == null){
            toolbar_title.setText(R.string.project_add);
        } else{
            toolbar_title.setText(R.string.project_edit);
        }
    }
}
