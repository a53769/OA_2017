package com.example.shixi_a.myapplication.classifyAddEdit;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.util.LogUtils;
import com.example.shixi_a.myapplication.util.ToastUtils;
import com.example.shixi_a.myapplication.bean.Classify;
import com.example.shixi_a.myapplication.model.classify.ClassifyDataSource;
import com.example.shixi_a.myapplication.model.classify.ClassifyRepository;

/**
 * Created by a5376 on 2017/6/26.
 */

public class ClassifyAddEditPresenter implements ClassifyAddEditContract.Presenter {

    @NonNull
    private final ClassifyDataSource mClaRepository;

    @NonNull
    private final ClassifyAddEditFragment mClaAddView;

    @NonNull
    private Classify mCla;

    private Context context;

    public ClassifyAddEditPresenter(Classify cla, ClassifyRepository mRepository, ClassifyAddEditFragment claAddEditFragment, Context context) {
        mClaRepository = mRepository;
        mClaAddView = claAddEditFragment;
        mClaAddView.setPresenter(this);
        mCla = cla;
        this.context = context;
    }

    @Override
    public void start() {
        if(!isNewCla()){
            onClassifyLoad();
        }else{
            mClaAddView.setText();
        }
    }

    private void onClassifyLoad() {
        mClaAddView.setTitle(mCla.getTarget_name());
        mClaAddView.setContent(mCla.getMemo());
        mClaAddView.setEdit();
    }

    @Override
    public void submit(String description) {
        if(isNewCla()){
            mClaRepository.addClassify(context,mClaAddView.getTitle(),description, new RawResponseHandler() {
                @Override
                public void onSuccess(int statusCode, String response) {
                    LogUtils.v("创建成功");
                    mClaAddView.showClassifys();
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    ToastUtils.showShort(context,"创建失败");
                }
            });
        }else {
            mClaRepository.editClassify(context,mCla.getId(),description, new RawResponseHandler() {
                @Override
                public void onSuccess(int statusCode, String response) {
                    ToastUtils.showShort(context,"修改成功");
                    mClaAddView.showClassifys();
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    ToastUtils.showShort(context,"修改失败");
                }
            });
        }
    }

    public boolean isNewCla() {
        return mCla == null;
    }
}
