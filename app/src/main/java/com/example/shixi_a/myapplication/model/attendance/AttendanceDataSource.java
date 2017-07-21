package com.example.shixi_a.myapplication.model.attendance;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.bean.Attendance;
import com.example.shixi_a.myapplication.bean.AttendanceState;
import com.example.shixi_a.myapplication.bean.RowsNoPage;

/**
 * Created by a5376 on 2017/7/5.
 */

public interface AttendanceDataSource {
    void getAttendances(Context context, String sort, String sTime, String eTime, GsonResponseHandler callback);

    void getLocation(Context context, JsonResponseHandler jsonResponseHandler);

    void AddAttendance(Context context, String s, String longitude, String latitude, String altitude, RawResponseHandler callback);

    void getAttendanceState(Context context, GsonResponseHandler<AttendanceState> gsonResponseHandler);

    void getRecord(Context context, GsonResponseHandler<RowsNoPage<Attendance>> gsonResponseHandler);
}
