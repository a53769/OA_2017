package com.example.shixi_a.myapplication.home.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.bean.UserInfo;
import com.example.shixi_a.myapplication.login.LoginActivity;
import com.example.shixi_a.myapplication.model.user.UserRepository;
import com.example.shixi_a.myapplication.home.my.attendance.AttendanceActivity;
import com.example.shixi_a.myapplication.home.my.clock.ClockActivity;
import com.example.shixi_a.myapplication.home.my.myInfo.MyInfoActivity;
import com.example.shixi_a.myapplication.util.ToastUtils;

/**
 * Created by Shixi-A on 2017/5/17.
 */

public class Fragment_my extends Fragment {

    private Context context;
    private UserRepository mRepository;
    private SharedPreferences sp;//缓存用户名与密码实现自动登录
    private SharedPreferences.Editor editor;

    private TextView name;
    private TextView email;

    private UserInfo user;

    @Override
    public void onResume() {
        super.onResume();
        mRepository.getUser(context, new GsonResponseHandler<UserInfo>() {
            @Override
            public void onSuccess(int statusCode, UserInfo response) {
                user = response;
                name.setText(response.getRow().getName());
                email.setText(response.getRow().getEmail());
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"加载用户信息失败请重新登录");
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sp.edit();

        mRepository = new UserRepository();
        context = getContext();

        name = (TextView) view.findViewById(R.id.name);
        email = (TextView) view.findViewById(R.id.email);

        TextView check = (TextView) view.findViewById(R.id.clock);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ClockActivity.class);
                startActivity(intent);
            }
        });
        //用户信息
        LinearLayout userInfo = (LinearLayout) view.findViewById(R.id.my_info);
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("USER_INFO",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //考勤
        LinearLayout attendance = (LinearLayout) view.findViewById(R.id.attendance);
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AttendanceActivity.class);
                startActivity(intent);
            }
        });

        //帮助与反馈
        LinearLayout help = (LinearLayout) view .findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort(context,"暂未开发");
            }
        });

        //设置
        LinearLayout set = (LinearLayout) view.findViewById(R.id.set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort(context,"暂未开发");
            }
        });

        //注销
        LinearLayout logout = (LinearLayout) view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRepository.logout(context, new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
                        ToastUtils.showShort(context, "登出成功");
                        editor.clear();
                        editor.apply();
                        editor.putBoolean("main",false);
                        editor.apply();
                        showLogin();
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        ToastUtils.showShort(context, "登出失败");
                    }
                });
            }
        });


        return view;
    }

    private void showLogin() {

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
