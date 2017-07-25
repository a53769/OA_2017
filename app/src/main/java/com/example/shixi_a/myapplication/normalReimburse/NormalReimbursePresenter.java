package com.example.shixi_a.myapplication.normalReimburse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.linkMan.LinkManActivity;
import com.example.shixi_a.myapplication.model.reimbursement.ReimbursementRepository;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by a5376 on 2017/7/24.
 */

public class NormalReimbursePresenter implements NormalReimburseContract.Presenter{
    private SharedPreferences sp;

    private ReimbursementRepository mRepository;
    private NormalReimburseFragment mNormalReimbursView;
    private Context context;

    private String realId;


    @Override
    public void result(int requestCode, int resultCode, Intent data) {
        if(LinkManActivity.REQUEST_USERS_CODE == requestCode && Activity.RESULT_OK == resultCode){
            realId = data.getStringExtra("id");
            String name = data.getStringExtra("name");
            mNormalReimbursView.setRealName(name);
        }
    }

    @Override
    public void applyReimbursement() {
        mRepository.appluReimbursement(context, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) throws JSONException {

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }

    public NormalReimbursePresenter(ReimbursementRepository repository, NormalReimburseFragment normalReimburseFragment, Context context) {
        mRepository = repository;
        mNormalReimbursView = normalReimburseFragment;
        mNormalReimbursView.setPresenter(this);
        this.context = context;
    }

    @Override
    public void start() {
        loadName();
    }

    private void loadName() {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        String name = sp.getString("name","");
        mNormalReimbursView.setName(name);
    }


}
