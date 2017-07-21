package com.example.shixi_a.myapplication.projects;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.util.ActivityUtils;
import com.example.shixi_a.myapplication.model.project.ProjectRepository;


/**
 * Created by Shixi-A on 2017/5/25.
 */

public class ProjectsActivity extends AppCompatActivity {

    public static final int REQUEST_PRO_CODE = 1;

    private ProjectsPresenter mProjectPresenter;

    private ProjectRepository projectRepository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        ProjectsFragment projectsFragment = (ProjectsFragment) getSupportFragmentManager().findFragmentById(R.id.project_content);
        if(projectsFragment == null){
            projectsFragment = new ProjectsFragment().newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), projectsFragment, R.id.project_content);
        }

        projectRepository = new ProjectRepository();

        mProjectPresenter = new ProjectsPresenter(projectRepository, projectsFragment, getApplicationContext());

    }

}
