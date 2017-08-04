package com.example.shixi_a.myapplication.work.workTask.classify;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;
import com.example.shixi_a.myapplication.util.ToastUtils;
import com.example.shixi_a.myapplication.bean.Classify;
import com.example.shixi_a.myapplication.bean.Rows;
import com.example.shixi_a.myapplication.model.classify.ClassifyRepository;

import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by Shixi-A on 2017/6/9.
 */

public class ClassifysPresenter implements ClassifysContract.Presenter {

    private boolean mFirstLoad = true;

    private ClassifysContract.View mClassifysView;

    private ClassifyRepository mClassifyRepository;

    private Context context;

    public ClassifysPresenter(@NonNull ClassifyRepository classifyRepository, @NonNull ClassifysContract.View classifysView, Context context) {//

        mClassifyRepository = checkNotNull(classifyRepository, "classifysRepository cannot be null");

        mClassifysView = checkNotNull(classifysView, "classifysView cannot be null!");

        mClassifysView.setPresenter(this);

        this.context = context;
    }

    @Override
    public void start() {
        loadClassifys(false);
    }

    @Override
    public void loadClassifys(boolean forceUpdate) {
        loadClassifys(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadClassifys(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mClassifysView.setLoadingIndicator(true);
        }
        //缓存数据需要更新
        if (forceUpdate) {

            //  mTasksRepository.refreshTasks();
        }
        mClassifyRepository.getClassifys(context, new GsonResponseHandler<Rows<Classify>>() {
            @Override
            public void onSuccess(int statusCode, Rows<Classify> response) {
                List<Classify> classifys;
                classifys = response.rows;
                mClassifysView.setLoadingIndicator(false);
                processClassifys(classifys);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

                mClassifysView.showLoadingClassifysError();
                mClassifysView.setLoadingIndicator(false);
            }
        });


    }

    private void processClassifys(List<Classify> classifys) {
        if (classifys.isEmpty()){
            mClassifysView.showNoClassifys();
        }
        else{
            mClassifysView.showClassifys(classifys);
        }

    }

    @Override
    public void addNewClassify() {
        mClassifysView.showAddClassify();
    }

    @Override
    public void selectedClassify(@NonNull Classify clickedClassify) {
        checkNotNull(clickedClassify,"requestClassify must not null");
        mClassifysView.showTaskAdd(clickedClassify.getTarget_name(), clickedClassify.getId());
    }

    @Override
    public void EditClassify(Classify editClassify) {
        mClassifysView.showEditClassify(editClassify);
    }

    @Override
    public void DelClassify(String id) {
        mClassifyRepository.deleteCla(context, id, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                ToastUtils.showShort(context,"删除成功");
                loadClassifys(false);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showShort(context,"删除失败");
            }
        });
    }
}
