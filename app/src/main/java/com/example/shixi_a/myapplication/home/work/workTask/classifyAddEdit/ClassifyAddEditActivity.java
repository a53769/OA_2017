package com.example.shixi_a.myapplication.home.work.workTask.classifyAddEdit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.util.ActivityUtils;
import com.example.shixi_a.myapplication.bean.Classify;
import com.example.shixi_a.myapplication.model.classify.ClassifyRepository;

/**
 * Created by Shixi-A on 2017/6/9.
 */

public class ClassifyAddEditActivity extends AppCompatActivity{

    private ClassifyAddEditPresenter mPresenter;
    private ClassifyRepository mRepository;
    private TextView toolbar_title;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_add);

        toolbar_title = (TextView) findViewById(R.id.cla_add_title);
        ClassifyAddEditFragment claAddEditFragment = (ClassifyAddEditFragment) getSupportFragmentManager().findFragmentById(R.id.cla_add_content);

        Classify cla = (Classify) getIntent().getSerializableExtra(ClassifyAddEditFragment.ARGUMENT_EDIT_CLA);
        setToolbarTitle(cla);

        if(claAddEditFragment == null){
            claAddEditFragment = ClassifyAddEditFragment.newInstance();
            if(getIntent().hasExtra(ClassifyAddEditFragment.ARGUMENT_EDIT_CLA)){
                Bundle bundle = new Bundle();
                bundle.putSerializable(ClassifyAddEditFragment.ARGUMENT_EDIT_CLA,cla);
                claAddEditFragment.setArguments(bundle);
            }
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), claAddEditFragment,R.id.cla_add_content);
        }
        mRepository = new ClassifyRepository();

        mPresenter = new ClassifyAddEditPresenter(cla, mRepository, claAddEditFragment, getApplicationContext());
    }

    public void setToolbarTitle(Classify cla) {
        if(cla == null){
            toolbar_title.setText("添加分类");
        }else {
            toolbar_title.setText("编辑分类");
        }
    }
}
