package com.example.shixi_a.myapplication.applyleave;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.leaveType.LeaveTypeActivity;
import com.example.shixi_a.myapplication.linkMan.LinkManActivity;

import java.util.Calendar;
import java.util.Date;

import static com.example.shixi_a.myapplication.util.StringUtils.getDate;

/**
 * Created by a5376 on 2017/7/18.
 */

public class ApplyLeaveFragment extends Fragment implements ApplyLeaveContract.View {

    private ApplyLeaveContract.Presenter mPresenter;

    private TextView type;
    private TextView startTime;
    private TextView endTime;
    private TextView is_personnel;
    private TextView handover_person;

    private TextView offsetStart;
    private TextView offsetEnd;

    private EditText reason;

    private EditText offsetcontent;

    private Button submit;
    private String typeName;
    private String is_handle = "0";

    private LinearLayout lv_type;
    private LinearLayout personnel;
    private LinearLayout handover;

    private LinearLayout offset;


    @Override
    public void setPresenter(ApplyLeaveContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ApplyLeaveFragment() {
    }

    public static ApplyLeaveFragment newInstance() {
        return new ApplyLeaveFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode, data);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.apply_leave_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        lv_type = (LinearLayout) root.findViewById(R.id.leave_type);
        lv_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLeaveTypes();
            }
        });
        type = (TextView) root.findViewById(R.id.type);

        LinearLayout start = (LinearLayout) root.findViewById(R.id.leave_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStartPicker();
            }
        });
        startTime = (TextView) root.findViewById(R.id.Stime);

        LinearLayout end = (LinearLayout) root.findViewById(R.id.leave_end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEndPicker();
            }
        });
        endTime = (TextView) root.findViewById(R.id.Etime);

        offset = (LinearLayout) root.findViewById(R.id.offset);

        handover = (LinearLayout) root.findViewById(R.id.leave_turnover);
        handover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHandOver();
            }
        });
        handover_person = (TextView) root.findViewById(R.id.over_person);

        reason = (EditText) root.findViewById(R.id.leave_matter);

        Button submit = (Button) root.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(offset.getVisibility() == View.VISIBLE){
                    ApplyLeavePresenter.extraWorkStart = offsetStart.getText().toString();
                    ApplyLeavePresenter.extraWorkEnd = offsetEnd.getText().toString();
                    ApplyLeavePresenter.extraWorkContent = offsetcontent.getText().toString().trim();
                }
                mPresenter.submitLeave(getStime(),getEtime(),is_handle,getReason());
            }
        });

        return root;
    }

    private void showHandOver() {
        Intent intent = new Intent(getActivity(), LinkManActivity.class);
        startActivityForResult(intent, LinkManActivity.REQUEST_USERS_CODE);

    }

    //功能取消
    private void showListDialog() {
        final String[] items = { "不需要" ,"需要"};
        AlertDialog.Builder listDialog = new AlertDialog.Builder(getActivity());
        listDialog.setTitle("操作");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                is_personnel.setText(items[which]);
                is_handle = String.valueOf(which);
            }
        });
        listDialog.show();
    }

    private void showEndPicker() {
        TimePickerView pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                endTime.setText(getDate(date,"yyyy-MM-dd HH:mm:ss"));
            }
        }).setLabel("","月","日","时","分","秒").build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    private void showStartPicker() {
        TimePickerView pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                startTime.setText(getDate(date,"yyyy-MM-dd HH:mm:ss"));
            }
        }).setLabel("","月","日","时","分","秒").build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    private void showLeaveTypes() {
        Intent intent = new Intent(getActivity(), LeaveTypeActivity.class);
        startActivityForResult(intent, LeaveTypeActivity.REQUEST_TYPE_CODE);
    }

    public void setTypeName(String typeName) {
        type.setText(typeName);
    }


    public void setHandOver(String handOver) {
        handover_person.setText(handOver);
    }

    public String getStime() {
        return startTime.getText().toString();
    }

    public String getEtime() {
        return endTime.getText().toString();
    }

    public String getReason() {
        return reason.getText().toString().trim();
    }

    @Override
    public void showLeaveDetail() {
        getActivity().finish();
    }

    @Override
    public void InitView(String off_start, String off_end, String need_modify,String matter) {

        startTime.setText(off_start);
        endTime.setText(off_end);
        is_personnel.setText(getType(need_modify));
        reason.setText(matter);
    }

    @Override
    public void initView(String sort_show, String handover_name) {
        type.setText(sort_show);
        handover_person.setText(handover_name);
    }

    @Override
    public void showOffset() {
        offset.setVisibility(View.VISIBLE);
        LinearLayout overworkstart = (LinearLayout) offset.findViewById(R.id.extra_work_start);
        overworkstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showoverstratpicker();
            }
        });
        LinearLayout overworkend = (LinearLayout) offset.findViewById(R.id.extra_work_end);
        overworkend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overendpicker();
            }
        });
        offsetStart = (TextView) offset.findViewById(R.id.OffStime);
        offsetEnd = (TextView) offset.findViewById(R.id.OffEtime);
        offsetcontent = (EditText) offset.findViewById(R.id.offcontent);
    }

    @Override
    public void hideoffset() {
        offset.setVisibility(View.GONE);
        ApplyLeavePresenter.extraWorkStart = "";
        ApplyLeavePresenter.extraWorkEnd = "";
        ApplyLeavePresenter.extraWorkContent = "";

    }

    @Override
    public void initOffset(String off_start, String offset_end, String offset_memo) {
        offsetStart.setText(off_start);
        offsetEnd.setText(offset_end);
        offsetcontent.setText(offset_memo);
        ApplyLeavePresenter.extraWorkStart = off_start;
        ApplyLeavePresenter.extraWorkEnd = offset_end;
        ApplyLeavePresenter.extraWorkContent = offset_memo;
    }

    private void overendpicker() {
        TimePickerView pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                offsetEnd.setText(getDate(date,"yyyy-MM-dd HH:mm:ss"));
            }
        }).setLabel("","月","日","时","分","秒").build();
        pvTime.setDate(Calendar.getInstance());
        pvTime.show();
    }

    private void showoverstratpicker() {
        TimePickerView pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                offsetStart.setText(getDate(date,"yyyy-MM-dd HH:mm:ss"));
            }
        }).setLabel("","月","日","时","分","秒").build();
        pvTime.setDate(Calendar.getInstance());
        pvTime.show();
    }





    private String getType(String need_modify) {

        if(need_modify.equals("0")){
            return "不需要";
        }else{
            return "需要";
        }
    }
}
