package com.example.shixi_a.myapplication.model.attendance;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.bean.Attendance;
import com.example.shixi_a.myapplication.bean.AttendanceState;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.model.BaseModel;

/**
 * Created by a5376 on 2017/7/5.
 */

public class AttendanceRepository extends BaseModel implements AttendanceDataSource {

    @Override
    public void getAttendances(Context context, String sort, String sTime, String eTime, GsonResponseHandler callback) {
        initRequest2(context);
        params.put("in_time_start",sTime);
        params.put("in_time_end",eTime);
        params.put("sort",sort);


        sendPostRequest(context, BASE + ATTENDANCELIST, params, callback);
    }

    @Override
    public void getLocation(Context context, JsonResponseHandler callback) {
        initRequest2(context);

        sendPostRequest(context,BASE + ATTENDANCELOCAATION,params,callback);
    }

    @Override
    public void AddAttendance(Context context, String s, String longitude, String latitude, String altitude, RawResponseHandler callback) {
        initRequest2(context);
        params.put("longitude",longitude);
        params.put("latitude",latitude);
        params.put("altitude",altitude);
        params.put("sort",s);
        sendPostRequest(context,BASE + ATTENDANCEADD, params, callback);
    }

    @Override
    public void getAttendanceState(Context context, GsonResponseHandler<AttendanceState> callback) {
        InitRequest(context,"month");
        params.put("admin_id","");
        params.put("month","");
        sendPostRequest(context,BASE + ATTENDANCESTATE,params,callback);
    }

    @Override
    public void getRecord(Context context, GsonResponseHandler<RowsNoPage<Attendance>> callback) {
        initRequest2(context);
        sendPostRequest(context,BASE + ATTENDANCELIST,params,callback);
    }
}
