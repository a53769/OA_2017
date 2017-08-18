package com.example.shixi_a.myapplication.home.my.clock;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.home.my.record.RecordActivity;
import com.example.shixi_a.myapplication.util.DrawUtil;
import com.example.shixi_a.myapplication.util.ToastUtils;
import com.example.shixi_a.myapplication.widget.ScrollChildSwipeRefreshLayout;
import com.example.shixi_a.myapplication.widget.SemiCircleRectView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by a5376 on 2017/7/5.
 */

public class ClockFragment extends Fragment implements ClockContract.View {

    private ClockContract.Presenter mPresenter;
    private static final int msgKey1 = 1;
    private TextView clock_in;
    private TextView clock_out;
    private TextView local_in;
    private TextView local_out;
    private TextView tvIn;
    private TextView tvOut;
    private TextView hide;
    private RelativeLayout rl_clockIn;
    private RelativeLayout rl_clockOut;
    private View line_one;
    private View line_two;
    private SemiCircleRectView circleIn;
    private SemiCircleRectView circleInDark;
    private SemiCircleRectView circleOut;
    private SemiCircleRectView circleOutDark;
    private boolean LOCAL = false;

    @Override
    public void setPresenter(ClockContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ClockFragment(){}

    public static ClockFragment newInstance() {
        return new ClockFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.clock_content, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        TextView record = (TextView) getActivity().findViewById(R.id.record);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RecordActivity.class);
                startActivity(intent);
            }
        });

        clock_in = (TextView) root.findViewById(R.id.clock_in);
        clock_out = (TextView) root.findViewById(R.id.clock_out);

        local_in = (TextView) root.findViewById(R.id.local_in);
        local_out = (TextView) root.findViewById(R.id.local_out);

        rl_clockIn = (RelativeLayout) root.findViewById(R.id.rl_clock_in);
        rl_clockIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.checkOn("1");
            }
        });
        rl_clockOut = (RelativeLayout) root.findViewById(R.id.rl_clock_out);
        rl_clockOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.checkOn("2");
            }
        });

        line_one = root.findViewById(R.id.first_line);
        line_two = root.findViewById(R.id.second_line);

        circleIn = (SemiCircleRectView) root.findViewById(R.id.circle_in);
        circleInDark = (SemiCircleRectView) root.findViewById(R.id.circle_in_dark);
        circleOut = (SemiCircleRectView) root.findViewById(R.id.circle_out);
        circleOutDark = (SemiCircleRectView) root.findViewById(R.id.circle_out_dark);


        tvIn = (TextView) root.findViewById(R.id.tv_in);
        tvOut = (TextView) root.findViewById(R.id.tv_out);

        hide = (TextView) root.findViewById(R.id.hide);

        final ScrollChildSwipeRefreshLayout swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        // Set the scrolling view in the custom SwipeRefreshLayout.
//        swipeRefreshLayout.setScrollUpChild(view);

        swipeRefreshLayout.setOnRefreshListener(new android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.start();//不强制刷新

            }
        });


        new TimeThread().start();

        return  root;
    }

    @Override
    public void setLoadingIndicator(final boolean active) {

        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void hideClockInWithUnCheck() {
        rl_clockIn.setVisibility(View.GONE);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) line_one.getLayoutParams();
        params.height = DrawUtil.dip2px(getContext(),40);
        line_one.setLayoutParams(params);
        circleIn.setVisibility(View.GONE);
        circleInDark.setVisibility(View.VISIBLE);
        hide.setVisibility(View.VISIBLE);
        tvIn.setText("(上班时间 09:00)");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(settingsIntent);
            } else {
               ToastUtils.showShort(getContext(),"请在设置中开启定位权限");
            }
        }
    }

    @Override
    public void getPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {

                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

//            } else {
//
//                // No explanation needed, we can request the permission.
//
//                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},2);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
        }else{

        }

    }

    @Override
    public void showWIFI(String location) {
        if(location.equals("true")){
            LOCAL = true;
        }else{
            local_in.setText("未进入Wi-Fi考勤范围");
            local_out.setText("未进入Wi-Fi考勤范围");
            LOCAL = false;
        }
    }

    @Override
    public void hideClockIn(String time) {
        rl_clockIn.setVisibility(View.GONE);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) line_one.getLayoutParams();
        params.height = DrawUtil.dip2px(getContext(),40);
        line_one.setLayoutParams(params);
        circleIn.setVisibility(View.GONE);
        circleInDark.setVisibility(View.VISIBLE);
        tvIn.setText("打卡时间"+time.substring(time.length()-8,time.length()-3)+"（上班时间 09:00）");
    }

    @Override
    public void hideClockOut(String time) {
        rl_clockOut.setVisibility(View.GONE);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) line_two.getLayoutParams();
        params.height = DrawUtil.dip2px(getContext(),20);
        line_two.setLayoutParams(params);
        circleOut.setVisibility(View.GONE);
        circleOutDark.setVisibility(View.VISIBLE);
        tvOut.setText("打卡时间"+time.substring(time.length()-8,time.length()-3)+"（下班时间 18:00）");
    }


    public class TimeThread extends  Thread{
        @Override
        public void run() {
            super.run();
            do{
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = msgKey1;
                    mHandler.sendMessage(msg);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (true);
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case msgKey1:
                    long time = System.currentTimeMillis();
                    Date date = new Date(time);
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                    clock_in.setText("上班打卡     " + format.format(date));
                    clock_out.setText("下班打卡     "+format.format(date));
                    break;
                default:
                    break;
            }
        }
    };
}
