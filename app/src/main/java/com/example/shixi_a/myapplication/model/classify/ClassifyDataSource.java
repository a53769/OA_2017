package com.example.shixi_a.myapplication.model.classify;

import android.content.Context;

import com.example.myokhttp.response.GsonResponseHandler;
import com.example.myokhttp.response.RawResponseHandler;

/**
 * Created by Shixi-A on 2017/6/9.
 */

public interface ClassifyDataSource {

    void getClassifys(Context context, GsonResponseHandler callback);

    void addClassify(Context context, String title, String description, RawResponseHandler rawResponseHandler);

    void editClassify(Context context, String id, String description, RawResponseHandler rawResponseHandler);
}
