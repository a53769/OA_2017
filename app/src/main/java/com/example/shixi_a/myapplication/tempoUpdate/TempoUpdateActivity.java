package com.example.shixi_a.myapplication.tempoUpdate;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.xw.repo.BubbleSeekBar;

/**
 * Created by a5376 on 2017/6/29.
 */

public class TempoUpdateActivity extends AppCompatActivity implements TempoUpdateContract.View{

    public static final String ARGUMENT_TASK_ID = "task_id";

    public static final String ARGUMENT_TEMPO = "tempo";

    private TempoUpdateContract.Presenter mPresent;

    private Context context;

    private String taskId;

    private EditText et_memo;

    private String tempo;
    private BubbleSeekBar bubbleSeekBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tempo);

        mPresent = new TempoUpdatePresent(this);

        taskId = getIntent().getStringExtra(ARGUMENT_TASK_ID);
        tempo = getIntent().getStringExtra(ARGUMENT_TEMPO);

        bubbleSeekBar = (BubbleSeekBar) findViewById(R.id.seekbar);
        bubbleSeekBar.setProgress(Float.parseFloat(tempo));

        TextView tv_tenpo = (TextView) findViewById(R.id.tempo_content);
        tv_tenpo.setText("当前进度："+ tempo);

        et_memo = (EditText) findViewById(R.id.memo);

        TextView save = (TextView) findViewById(R.id.tempo_submit);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresent.saveChange(context,taskId, bubbleSeekBar.getProgress(), getMemo());
            }
        });

        //终止任务
        ImageView stop = (ImageView) findViewById(R.id.stop_task);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresent.saveChange(context,taskId,-1,getMemo());
            }
        });



        context = getApplicationContext();

    }

    @Override
    public void setPresenter(TempoUpdateContract.Presenter presenter) {
        mPresent = presenter;
    }

    @Override
    public void showTaskDetail() {
        finish();
    }

    public String getMemo() {
        return et_memo.getText().toString().trim();
    }
}
