package com.example.shixi_a.myapplication.projectAddEdit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;

/**
 * Created by a5376 on 2017/6/23.
 */

public class ProjectAddEditFragment extends Fragment implements ProjectAddEditContract.View {

    public static final String ARGUMENT_EDIT_PROJECT = "EDIT_PROJECT";

    private ProjectAddEditContract.Presenter mPresenter;

    private EditText proTitle;

    private EditText proDescription;

    private TextView text;

    public ProjectAddEditFragment(){}

    public static ProjectAddEditFragment newInstance() {
        return new ProjectAddEditFragment();
    }


    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull ProjectAddEditContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.project_add_content,container,false);
        proTitle = (EditText) root.findViewById(R.id.et_name);
        proDescription = (EditText) root.findViewById(R.id.et_description);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        TextView submit = (TextView) getActivity().findViewById(R.id.pro_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.submit(getDescription());
            }
        });

        text = (TextView) root.findViewById(R.id.textView1);

        return root;
    }

    @Override
    public void setTitle(String project_name) {
        proTitle.setText(project_name);
    }

    @Override
    public void setContent(String description) {
        proDescription.setText(description);
    }

    @Override
    public String getDescription() {
        return proDescription.getText().toString().trim();
    }

    @Override
    public String getTitle() {
        return proTitle.getText().toString().trim();
    }

    @Override
    public void setEdit() {
        proTitle.setFocusable(false);
        proTitle.setKeyListener(null);
    }

    @Override
    public void showProjects() {
        getActivity().finish();
    }

    @Override
    public void setText() {
        text.setText("项目名称");
    }
}