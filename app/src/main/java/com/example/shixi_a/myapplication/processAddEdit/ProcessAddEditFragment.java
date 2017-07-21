package com.example.shixi_a.myapplication.processAddEdit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.contacts.ContactsActivity;

import java.util.Calendar;
import java.util.Date;

import static com.example.shixi_a.myapplication.util.StringUtils.getDate;
import static com.example.shixi_a.myapplication.util.TimeUtil.String2Date;

/**
 * Created by a5376 on 2017/6/16.
 */

public class ProcessAddEditFragment extends Fragment implements ProcessAddEditContract.View {
    public static final String ARGUMENT_EDIT_PROCESS = "EDIT_PROCESS";

    private ProcessAddEditContract.Presenter mPresenter;

    private TextView exe;

    private EditText content;

    private EditText title;

    private TextView startTime;

    private TextView pickTime;

    public ProcessAddEditFragment(){}

    public static ProcessAddEditFragment newInstance() {
        return new ProcessAddEditFragment();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode, data);
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.process_add_content, container, false);

        //导航栏
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        //提交
        TextView submit = (TextView) getActivity().findViewById(R.id.process_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.submit(getProcessTitle(), getProcessContent(), pickTime.getText().toString().trim());
            }
        });

        title = (EditText) root.findViewById(R.id.Process_name);
        content = (EditText) root.findViewById(R.id.Process_content);

        //开始时间
        startTime = (TextView) root.findViewById(R.id.tv_process_stime);
//        SimpleDateFormat formatter = new SimpleDateFormat   ("yyyy-MM-dd HH:mm:ss");
//        curDate = new Date(System.currentTimeMillis());
//        startTime.setText(formatter.format(curDate));

        //执行人
        LinearLayout principal = (LinearLayout) root.findViewById(R.id.process_principal);
        principal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContacts();
            }
        });
        exe = (TextView) root.findViewById(R.id.executor_name);

        //截止时间
        pickTime = (TextView) root.findViewById(R.id.tv_process_etime);
        LinearLayout endTime = (LinearLayout) root.findViewById(R.id.process_etime);
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        return root;
    }


    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }

    private void showTimePicker() {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.setTime(String2Date(startTime.getText().toString()));
        endDate.add(Calendar.YEAR,1);
        TimePickerView pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                pickTime.setText(getDate(date,"yyyy-MM-dd HH:mm:ss"));
            }
        }).setLabel("","月","日","时","分","秒")
                .setRangDate(startDate,endDate).build();
//        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    @Override
    public void setExecutor(String executor) {
        exe.setText(executor);
    }


    @Override
    public void showContacts() {
        Intent intent = new Intent(getActivity(), ContactsActivity.class);
        startActivityForResult(intent,ContactsActivity.REQUEST_CONTACTS_CODE);
    }

    @Override
    public void showProcesses() {
//        Intent intent = new Intent();
//        intent.putExtra("name", name);
//        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    @Override
    public void setTitle(String Title) {
        title.setText(Title);
    }

    @Override
    public void setContent(String description) {
        content.setText(description);
    }

    @Override
    public void setEdit() {
        title.setFocusable(false);
        title.setKeyListener(null);
    }

    @Override
    public void setTime(String estimate_stime, String estimate_etime) {
        startTime.setText(estimate_stime);
        pickTime.setText(estimate_etime);
    }

    @Override
    public void setTime(String time) {
        startTime.setText(time);
    }

    @Override
    public void setPresenter(ProcessAddEditContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public String getProcessTitle() {
        return title.getText().toString().trim();
    }

    public String getProcessContent() {
        return content.getText().toString().trim();
    }


}
