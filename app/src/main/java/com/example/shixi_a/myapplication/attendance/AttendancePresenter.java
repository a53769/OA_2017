package com.example.shixi_a.myapplication.attendance;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.shixi_a.myapplication.bean.AttendanceState;
import com.example.shixi_a.myapplication.model.attendance.AttendanceRepository;

/**
 * Created by a5376 on 2017/7/7.
 */

public class AttendancePresenter implements AttendanceContract.Presenter {
    private AttendanceRepository mRepository;
    private AttendanceFragment mAttendanceView;
    private Context context;

    public AttendancePresenter(AttendanceRepository mRepository, AttendanceFragment attendanceFragment, Context context) {
        mAttendanceView = attendanceFragment;
        mAttendanceView.setPresenter(this);
        this.mRepository = mRepository;
        this.context = context;
    }

    @Override
    public void start() {
        loadAttendance();
        mAttendanceView.setLoadingIndicator(false);
    }

    private void loadAttendance() {
        mRepository.getAttendanceState(context, new GsonResponseHandler<AttendanceState>() {
            @Override
            public void onSuccess(int statusCode, AttendanceState response) {
                mAttendanceView.initView(response);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }
}
