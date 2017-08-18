package com.example.shixi_a.myapplication.home.work.workAdministration.egress;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.JsonResponseHandler;
import com.example.shixi_a.myapplication.bean.Egress;
import com.example.shixi_a.myapplication.bean.RowsNoPage;
import com.example.shixi_a.myapplication.model.egress.EgressRepository;
import com.example.shixi_a.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by a5376 on 2017/7/11.
 */

public class EgressPresenter implements EgressContract.Presenter {

    private EgressContract.View mEgressView;
    private EgressRepository mEgressRepository;
    private Context context;

    private String mCurrentFilter = "admin";

    public EgressPresenter(EgressRepository mRepository, EgressFragment egressFragment, Context context) {
        mEgressRepository = mRepository;
        mEgressView = egressFragment;
        mEgressView.setPresenter(this);
        this.context = context;
    }


    @Override
    public void start() {
        mEgressView.setLoadingIndicator(true);
        loadEgress();
        mEgressView.setLoadingIndicator(false);
    }

    @Override
    public void loadEgress() {
        String adminId = "";
        String groupId = "";
        switch (mCurrentFilter){
            case "admin":
                adminId = "-1";
                groupId = "0";
                break;
            case "group":
                adminId = "0";
                groupId = "-1";
                break;
            default:
                break;
        }
        mEgressRepository.getEgress(context, adminId,groupId,new GsonResponseHandler<RowsNoPage<Egress>>() {
            @Override
            public void onSuccess(int statusCode, RowsNoPage<Egress> response) {
                List<Egress> egresses;
                egresses = response.rows;
                processEgress(egresses);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,error_msg);
            }
        });
    }

    @Override
    public void setFilter(String filter) {
        mCurrentFilter = filter;
    }

    @Override
    public void selectedEgress(Egress clickedEgress) {
        mEgressView.showDetailEgress(clickedEgress);
    }

    @Override
    public void editEgress(Egress editEgress) {
        if(editEgress.getStatus().equals("4")){
            ToastUtils.showShort(context,"该记录已签到，不能编辑");
        }else {
            mEgressView.showEditEgress(editEgress);
        }
    }

    @Override
    public void deleteEgress(Egress egress) {
        if(egress.getStatus().equals("4")){
            ToastUtils.showShort(context,"该记录已签到，不能取消");
        }else if(egress.getStatus().equals("3"))
        {
            ToastUtils.showShort(context,"已经取消过了");
        }else {

            mEgressRepository.deletEgress(context, egress.getId(), new JsonResponseHandler() {
                @Override
                public void onSuccess(int statusCode, JSONObject response) throws JSONException {
                    if (response.getBoolean("rt")) {
                        ToastUtils.showShort(context, "取消成功");
                    } else {
                        ToastUtils.showShort(context, response.getString("error"));
                    }
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    ToastUtils.showShort(context, "删除成功");
                }
            });
        }
    }

    private void processEgress(List<Egress> egresses) {
        if(egresses.isEmpty()){
            ToastUtils.showShort(context,"列表为空");
        }
        mEgressView.showEgress(egresses);
    }
}
