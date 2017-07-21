package com.example.shixi_a.myapplication.model.leave;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Leave;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.model.BaseModel;

/**
 * Created by a5376 on 2017/7/18.
 */

public class LeaveRepository extends BaseModel implements LeaveDataSource {

    public void getLeaves(Context context, GsonResponseHandler<RowsNoPage<Leave>> gsonResponseHandler) {
        InitRequest(context,"rows");

        params.put("pn","50");
        params.put("np","1");

        sendPostRequest(context,BASE + GETLEAVELIST, params, gsonResponseHandler);
    }

    @Override
    public void getLeave(Context context, String leaveId, GsonResponseHandler<Leave> gsonResponseHandler) {
        InitRequest(context,"row");

        params.put("off_id",leaveId);
        params.put("show_log","1");

        sendPostRequest(context,BASE + GETLEAVEDETAIL,params,gsonResponseHandler);
    }

    @Override
    public void getTypeList(Context context, JsonResponseHandler callback) {
        InitRequest(context,"sort_show");

        sendPostRequest(context,BASE + GETLEAVETYPE,params,callback);
    }

    @Override
    public void applyLeave(Context context, String typeId, String stime, String etime, String is_handle, String handeoverId, String reason, JsonResponseHandler callback) {
        InitRequest(context,"add");

        params.put("need_modify",is_handle);
        params.put("off_start",stime);
        params.put("off_end",etime);
        params.put("sort",typeId);
        params.put("reason",reason);
        params.put("offset_start","");
        params.put("offset_end","");
        params.put("offset_memo","");
        params.put("handover_aid",handeoverId);

        sendPostRequest(context,BASE + APPLYLEAVE,params,callback);
    }

    @Override
    public void cancelLeave(Context context, String leaveId, JsonResponseHandler callback) {
        InitRequest(context,"cancel");

        params.put("off_id",leaveId);

        sendPostRequest(context,BASE + CAMCELLEAVE,params,callback);
    }

    @Override
    public void AuditLeave(Context context, String leaveId, String status, String s, JsonResponseHandler callback) {

        InitRequest(context,"audit");

        params.put("off_id",leaveId);
        params.put("status",status);
        params.put("result",s);
        params.put("memo","");

        sendPostRequest(context,BASE + AUDITLEAVE , params, callback);
    }

    @Override
    public void editHRLeave(Context context, String leaveId, String opt, String stime, String etime, JsonResponseHandler jsonResponseHandler) {
        InitRequest(context,"edit");

        params.put("off_id",leaveId);
        params.put("need_modify",opt);
        params.put("off_start",stime);
        params.put("off_end",etime);

        sendPostRequest(context,BASE + EDITLEAVE,params,jsonResponseHandler);

    }

    @Override
    public void editLeave(Context context, String leaveId, String typeId, String stime, String etime, String is_handle, String handeoverId, String reason, JsonResponseHandler jsonResponseHandler) {
        InitRequest(context,"edit");

        params.put("off_id",leaveId);

        params.put("need_modify",is_handle);
        params.put("off_start",stime);
        params.put("off_end",etime);
        params.put("sort",typeId);
        params.put("reason",reason);
        params.put("offset_start","");
        params.put("offset_end","");
        params.put("offset_memo","");
        params.put("handover_aid",handeoverId);

        sendPostRequest(context,BASE + EDITLEAVE,params,jsonResponseHandler);
    }
}
