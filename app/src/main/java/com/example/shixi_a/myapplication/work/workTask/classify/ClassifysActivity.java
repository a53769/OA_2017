package com.example.shixi_a.myapplication.work.workTask.classify;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.util.ActivityUtils;
import com.example.shixi_a.myapplication.model.classify.ClassifyRepository;


/**
 * Created by Shixi-A on 2017/6/8.
 */

public class ClassifysActivity extends AppCompatActivity {

    public static final int REQUEST_CLA_CODE = 2;

    private ClassifysPresenter mClassifyPresenter;

    private ClassifyRepository classifyRepository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);

        ClassifysFragment classifysFragment = (ClassifysFragment) getSupportFragmentManager().findFragmentById(R.id.classify_content);
        if(classifysFragment == null){
            classifysFragment = new ClassifysFragment().newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), classifysFragment, R.id.classify_content);
        }

        classifyRepository = new ClassifyRepository();

        mClassifyPresenter = new ClassifysPresenter(classifyRepository, classifysFragment, getApplicationContext());

    }

}
