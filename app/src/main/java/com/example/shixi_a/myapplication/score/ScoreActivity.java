package com.example.shixi_a.myapplication.score;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;

/**
 * Created by a5376 on 2017/6/30.
 */

public class ScoreActivity extends AppCompatActivity implements ScoreContract.View{
    public static final String ARGUMENT_TASK_ID = "task_id";

    private ScoreContract.Presenter mPresenter;

    private String taskId;

    private RatingBar quanlity;

    private RatingBar efficient;

    private RatingBar attitude;

    private EditText et_memo;

    private Context context;

    @Override
    public void setPresenter(ScoreContract.Presenter presenter) {
        mPresenter = presenter;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mPresenter = new ScorePresenter(this);
        context = getApplicationContext();

        taskId = getIntent().getStringExtra(ARGUMENT_TASK_ID);

        quanlity = (RatingBar) findViewById(R.id.ratingBar);
        efficient = (RatingBar) findViewById(R.id.ratingBar1);
        attitude = (RatingBar) findViewById(R.id.ratingBar2);
        et_memo = (EditText) findViewById(R.id.memo_content);

        TextView save = (TextView) findViewById(R.id.score_submit);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.saveChange(context,taskId, quanlity.getRating(), efficient.getRating(), attitude.getRating(), getMemo());
            }
        });

    }
    public String getMemo() {
        return et_memo.getText().toString().trim();
    }


    @Override
    public void showTasks() {
        finish();
    }
}
