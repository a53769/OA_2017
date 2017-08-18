package com.example.shixi_a.myapplication.home.work.workAdministration.trafficTool;

import android.content.Context;

import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by a5376 on 2017/7/28.
 */

public class TrafficToolPresenter implements TrafficToolContract.Presenter {

    private ReimbursementRepository mRepository;
    private TrafficToolActivity mToolView;
    private Context context;

    private String flag;

    private Map<String, String> valueMap;

    public TrafficToolPresenter(String flag, ReimbursementRepository repository, TrafficToolActivity trafficToolActivity, Context context) {
        mRepository = repository;
        mToolView = trafficToolActivity;
        mToolView.setPresenter(this);
        this.context = context;
        this.flag = flag;
    }

    @Override
    public void start() {
        loadTools();
    }

    private void loadTools() {
        if(flag.equals("out")){
            mRepository.getTrafficOutTools(context, new JsonResponseHandler() {
                @Override
                public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                    JSONObject jsonObject;
                    jsonObject = new JSONObject(response.getString("outcity_traffic_arr"));
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

                }
            });
        }else if(flag.equals("in")){
            mRepository.getTrafficTools(context, new JsonResponseHandler() {
                @Override
                public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                    JSONObject jsonObject;
                    jsonObject = new JSONObject(response.getString("incity_traffic_arr"));
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

                }
            });
        }

    }

    private void process(Map<String, String> valueMap) {
        mToolView.showTypes(valueMap);
    }
}
