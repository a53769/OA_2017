package com.example.shixi_a.myapplication.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.administration.AdministrationActivity;
import com.example.shixi_a.myapplication.tasks.TasksActivity;

/**
 * Created by Shixi-A on 2017/5/17.
 */

public class Fragment_work extends Fragment implements View.OnClickListener {

    private LinearLayout ly1,ly2,ly3,ly4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_work, container, false);

        ly1 = (LinearLayout) view.findViewById(R.id.ly_approve);
        ly2 = (LinearLayout) view.findViewById(R.id.ly_task);
        ly3 = (LinearLayout) view.findViewById(R.id.ly_business);
        ly4 = (LinearLayout) view.findViewById(R.id.ly_administration);

        ly1.setOnClickListener(this);
        ly2.setOnClickListener(this);
        ly3.setOnClickListener(this);
        ly4.setOnClickListener(this);

        return view;
        //super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ly_approve:
                Toast.makeText(getActivity(),"暂无", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ly_task:
                Intent intent = new Intent(this.getActivity(), TasksActivity.class);
                startActivity(intent);
                //Toast.makeText(getActivity(),"任务", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ly_business:
                Toast.makeText(getActivity(),"暂无", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ly_administration:
                Intent intent1 = new Intent(getActivity(), AdministrationActivity.class);
                startActivity(intent1);
//                Toast.makeText(getActivity(),"暂无", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
