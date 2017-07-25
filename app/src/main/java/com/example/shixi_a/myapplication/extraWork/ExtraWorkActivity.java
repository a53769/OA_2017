package com.example.shixi_a.myapplication.extraWork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.shixi_a.myapplication.R;

/**
 * Created by a5376 on 2017/7/25.
 */

public class ExtraWorkActivity extends AppCompatActivity implements ExtraWorkContract.View {

    private ExtraWorkContract.Presenter mPresenter;

    @Override
    public void setPresenter(ExtraWorkContract.Presenter presenter) {
        mPresenter = presenter;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_work);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button sure = (Button) findViewById(R.id.submit);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
