package com.example.shixi_a.myapplication.taskAddEdit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.classify.ClassifysActivity;
import com.example.shixi_a.myapplication.milestone.MileStoneActivity;
import com.example.shixi_a.myapplication.process.ProcessActivity;
import com.example.shixi_a.myapplication.projects.ProjectsActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.example.shixi_a.myapplication.util.StringUtils.getDate;
import static com.example.shixi_a.myapplication.util.TimeUtil.getCurrentTime;


/**
 * Created by Shixi-A on 2017/6/9.
 */

public class TaskAddEditFragment extends Fragment implements TaskAddEditContract.View {

    public final static String ARGUMENT_EDIT_TASK = "TASK_EDIT";

    private TaskAddEditContract.Presenter mPresenter;

    private TextView tv_priority;

    private TextView pickTime;

    private TextView proName;

    private TextView claName;

    private TextView mileName;

    private TextView tv_memo;

    private EditText title;

    private EditText goal;

    private EditText method;

    private String priorityId;


    @Override
    public void setPresenter(TaskAddEditContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public TaskAddEditFragment(){}

    public static TaskAddEditFragment newInstance() {
        return new TaskAddEditFragment();
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
        //获取根视图
        View root = inflater.inflate(R.layout.task_add_content, container, false);

        title = (EditText) root.findViewById(R.id.et_title);
        goal = (EditText) root.findViewById(R.id.et_goal);
        method = (EditText) root.findViewById(R.id.et_method);

        TextView submit  = (TextView) getActivity().findViewById(R.id.task_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.submit(getTitle(),getGoal(),getMethod(),getStime(),getMemo());
            }
        });

        ImageView extra = (ImageView) root.findViewById(R.id.link);
        extra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"添加附件", Toast.LENGTH_SHORT).show();
            }
        });

        //项目
        LinearLayout project = (LinearLayout) root.findViewById(R.id.task_item_pro);
        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showProjects();
            }
        });
        proName = (TextView) root.findViewById(R.id.pro_name);

        //分类
        LinearLayout classify = (LinearLayout) root.findViewById(R.id.task_item_cla);
        classify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showClassifys();
            }
        });
        claName = (TextView) root.findViewById(R.id.cla_name);

        //里程碑
        LinearLayout milestone = (LinearLayout) root.findViewById(R.id.task_item_milestone);
        milestone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showMilestone();
            }
        });
        mileName = (TextView) root.findViewById(R.id.milestone_name);

        //流程
        LinearLayout process = (LinearLayout) root.findViewById(R.id.task_item_process);
        process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {mPresenter.showProcesses();
        }});

        //优先级
        LinearLayout priority = (LinearLayout) root.findViewById(R.id.task_item_pri);
        priority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showPrioritys();
            }
        });
        tv_priority = (TextView) root.findViewById(R.id.tv_priority);

        //开始时间
        pickTime = (TextView) root.findViewById(R.id.picktime);
        pickTime.setText(getCurrentTime());
        LinearLayout time = (LinearLayout) root.findViewById(R.id.task_item_time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        //备注
        LinearLayout meno = (LinearLayout) root.findViewById(R.id.task_item_meno);
        meno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMemo();
            }
        });
        tv_memo = (TextView) root.findViewById(R.id.tv_memo);
        return root;
    }

    private void showTimePicker() {
        TimePickerView pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                pickTime.setText(getDate(date,"yyyy-MM-dd HH:mm:ss"));
            }
        }).setLabel("","月","日","时","分","秒").build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    private void showMemo() {
        View popupView = getActivity().getLayoutInflater().inflate(R.layout.pop_memo, null);
        final PopupWindow window = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);//大小
        window.setAnimationStyle(R.style.pop_memo_anim);//动画
//        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));//背景色
        window.setFocusable(true);// 设置可以获取焦点
        window.setOutsideTouchable(true); // 设置可以触摸弹出框以外的区域
        window.update();//更新popupwindow的状态
        window.showAtLocation(getView(), Gravity.BOTTOM,0,0);

        final EditText et_memo = (EditText) popupView.findViewById(R.id.memo_content);
        et_memo.setText(tv_memo.getText().toString());
        Button bt_memo = (Button) popupView.findViewById(R.id.memo_submit);
        bt_memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_memo.setText(et_memo.getText());
                window.dismiss();
            }
        });
    }

    @Override
    public void showPrioritys(final Map<String, String> valueMap) {
        List list = new ArrayList(valueMap.values());
        final String[] datas = (String[]) list.toArray(new String[list.size()]);
        View popupView = getActivity().getLayoutInflater().inflate(R.layout.popupwindow, null);
        final PopupWindow window = new PopupWindow(popupView, 500, LinearLayout.LayoutParams.WRAP_CONTENT);//大小
        window.setAnimationStyle(R.style.popup_window_anim);//动画
//        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));//背景色
        window.setFocusable(true);// 设置可以获取焦点
        window.setOutsideTouchable(true); // 设置可以触摸弹出框以外的区域
        window.update();//更新popupwindow的状态
        window.showAtLocation(getView(), Gravity.CENTER,0,0);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.4f;
        getActivity().getWindow().setAttributes(lp);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
        ListView listView = (ListView) popupView.findViewById(R.id.popupList);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.simple_list_item, datas));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = datas[position];
                tv_priority.setText(value);
                for(Map.Entry entry:valueMap.entrySet()){
                    if(value.equals(entry.getValue())) {
                        priorityId = (String) entry.getKey();
                        mPresenter.setPriority(priorityId) ;
                    }
                }
                window.dismiss();

            }
        });
    }

    @Override
    public void showTasks() {
        getActivity().finish();
    }

    @Override
    public void setText(String pro_name, String type,String stone_name) {
        proName.setText(pro_name);
        claName.setText(type);
        mileName.setText(stone_name);
    }

    @Override
    public void initView(String Title, String target_desc, String exec_method, String estimate_stime, String priority_show, String memo) {
        title.setText(Title);
        goal.setText(target_desc);
        method.setText(exec_method);
        tv_priority.setText(priority_show);
        pickTime.setText(estimate_stime);
        tv_memo.setText(memo);
    }

    @Override
    public void showMileStone(String proId) {
        Intent intent = new Intent(getActivity(), MileStoneActivity.class);
        intent.putExtra("pro_id", proId);
        startActivityForResult(intent,MileStoneActivity.REQUEST_MILE_CODE);
    }

    @Override
    public void setStoneName(String stoneName) {
        mileName.setText(stoneName);
    }


    @Override
    public void showProjects() {
        Intent intent = new Intent(getActivity(), ProjectsActivity.class);
        startActivityForResult(intent, ProjectsActivity.REQUEST_PRO_CODE);//请求CODE 通过Intent参数返回
    }

    @Override
    public void setProName(String name) {
        proName.setText(name);
    }

    @Override
    public void showClassifys() {
        Intent intent = new Intent(getActivity(), ClassifysActivity.class);
        startActivityForResult(intent, ClassifysActivity.REQUEST_CLA_CODE);//请求CODE 通过Intent参数返回
    }

    @Override
    public void setClaName(String cla_name) {
        claName.setText(cla_name);
    }

    @Override
    public void showProcesses(String taskId) {//不需要返回传参 只需要传入taskId和开始时间
        Intent intent = new Intent(getContext(), ProcessActivity.class);
        intent.putExtra(ProcessActivity.EXTRA_TASK_ID, taskId);
        intent.putExtra("STime",pickTime.getText().toString());
        startActivity(intent);
    }


    public String getTitle() {
        return title.getText().toString().trim();
    }

    public String getGoal() {
        return goal.getText().toString().trim();
    }

    public String getMethod() {
        return method.getText().toString().trim();
    }

    public String getMemo() {
        return tv_memo.getText().toString().trim();
    }

    public String getStime() {
        return pickTime.getText().toString();
    }
}
