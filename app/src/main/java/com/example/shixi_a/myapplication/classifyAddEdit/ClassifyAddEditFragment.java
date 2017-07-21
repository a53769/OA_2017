package com.example.shixi_a.myapplication.classifyAddEdit;

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
 * Created by a5376 on 2017/6/26.
 */

public class ClassifyAddEditFragment extends Fragment implements ClassifyAddEditContract.View {

    public final static String ARGUMENT_EDIT_CLA = "EDIT_CLA";

    private ClassifyAddEditContract.Presenter mPresenter;

    private EditText claTitle;

    private EditText claDescription;

    private TextView text;

    public ClassifyAddEditFragment(){}

    public static ClassifyAddEditFragment newInstance() {
        return new ClassifyAddEditFragment();
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(ClassifyAddEditContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.classify_add_content,container,false);
        claTitle = (EditText) root.findViewById(R.id.et_claTitle);
        claDescription = (EditText) root.findViewById(R.id.et_claDescription);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        TextView submit = (TextView) getActivity().findViewById(R.id.cla_submit);
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
    public void setTitle(String classify_name) {
        claTitle.setText(classify_name);
    }

    @Override
    public void setContent(String description) {
        claDescription.setText(description);
    }

    @Override
    public String getDescription() {
        return claDescription.getText().toString().trim();
    }

    @Override
    public String getTitle() {
        return claTitle.getText().toString().trim();
    }

    @Override
    public void setEdit() {
        claTitle.setFocusable(false);
        claTitle.setKeyListener(null);
    }

    @Override
    public void showClassifys() {
        getActivity().finish();
    }

    @Override
    public void setText() {
        text.setText("分类名称");
    }


}
