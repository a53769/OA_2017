package com.example.shixi_a.myapplication.record;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Attendance;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.model.attendance.AttendanceRepository;
import com.example.shixi_a.myapplication.util.ToastUtils;

import java.util.List;

/**
 * Created by a5376 on 2017/7/7.
 */

public class RecordPresenter implements RecordContract.Presenter{

    private AttendanceRepository mRepository;
    private RecordFragment mRecordeView;
    private Context context;

    public RecordPresenter(AttendanceRepository recordRepository, RecordFragment recordFragment, Context context) {
        mRecordeView = recordFragment;
        mRecordeView.setPresenter(this);
        mRepository = recordRepository;
        this.context = context;
    }

    @Override
    public void start() {
        loadRecord();
    }

    public void loadRecord() {
        mRepository.getRecord(context, new GsonResponseHandler<RowsNoPage<Attendance>>() {
            @Override
            public void onSuccess(int statusCode, RowsNoPage<Attendance> response) {
                List<Attendance> attendances;
                attendances = response.rows;
                mRecordeView.setLoadingIndicator(false);
                processRecord(attendances);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"加载失败");
                mRecordeView.setLoadingIndicator(false);
            }
        });
    }

    private void processRecord(List<Attendance> attendances) {
        mRecordeView.showRecords(attendances);
    }
}
