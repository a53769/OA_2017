package com.example.shixi_a.myapplication.model.leave;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Leave;
import com.example.shixi_a.myapplication.bean.RowsNoPage;

/**
 * Created by a5376 on 2017/7/18.
 */

public interface LeaveDataSource {
    void getLeaves(Context context, GsonResponseHandler<RowsNoPage<Leave>> gsonResponseHandler);

    void getLeave(Context context, String leaveId, GsonResponseHandler<Leave> gsonResponseHandler);

    void getTypeList(Context context, JsonResponseHandler gsonResponseHandler);

    void applyLeave(Context context, String id, String stime1, String s, String typeId, String stime, String etime, String is_handle, String handeoverId, String reason, JsonResponseHandler callback);

    void cancelLeave(Context context, String leaveId, JsonResponseHandler callback);

    void AuditLeave(Context context, String leaveId, String status, String s, JsonResponseHandler jsonResponseHandler);

    void editLeave(Context context, String leaveId, String typeId, String stime, String etime, String is_handle, String handeoverId, String reason, String offstart, String offend, String offcontent, JsonResponseHandler jsonResponseHandler);
}
