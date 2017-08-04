package com.example.shixi_a.myapplication.work.workAdministration.leaveType;

import android.content.Context;

import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.model.leave.LeaveRepository;
import com.example.shixi_a.myapplication.util.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by a5376 on 2017/7/19.
 */

public class LeaveTypePresenter implements LeaveTypeContract.Presenter {
    private LeaveRepository mRepository;
    private LeaveTypeActivity mLeaveTypeView;
    private Context context;

    private Map<String, String> valueMap;

    public LeaveTypePresenter(LeaveRepository repository, LeaveTypeActivity leaveTypeActivity, Context context ) {
        mRepository = repository;
        mLeaveTypeView = leaveTypeActivity;
        mLeaveTypeView.setPresenter(this);
        this.context = context;
    }

    @Override
    public void start() {
        loadList();
    }

    private void loadList() {
        mRepository.getTypeList(context, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                JSONObject jsonObject;
                jsonObject = new JSONObject(response.getString("sort_arr"));
                Iterator<String> keyIter= jsonObject.keys();
                String key;
                String value ;
                valueMap = new LinkedHashMap<String, String>();
                while (keyIter.hasNext()) {
                    key = keyIter.next();
                    value = (String) jsonObject.get(key);
                    valueMap.put(key, value);
                }
                process(valueMap);
            }


            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogUtils.v("获取失败");
            }
        });
    }

    private void process(Map<String, String> valueMap) {
        mLeaveTypeView.showTypes(valueMap);
    }
}
