package com.example.shixi_a.myapplication.model.process;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;

import java.util.List;

/**
 * Created by Shixi-A on 2017/6/12.
 */

public interface ProcessDataSource {
    void getProcesses(Context context, String taskId, GsonResponseHandler callback);
    void submitProcess(Context context, String taskId, String title, String content, List<String> user_id,
                       String estime, String father_id, JsonResponseHandler callback);
    void editProcess(Context context, String tempId, String title, List<String> mList, String etime, String content, JsonResponseHandler callback);
    void delProcess(Context context, String step_id, RawResponseHandler callback);
}
