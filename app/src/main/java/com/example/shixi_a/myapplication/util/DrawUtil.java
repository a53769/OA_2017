package com.example.shixi_a.myapplication.util;

import android.content.Context;

/**
 * Created by a5376 on 2017/7/6.
 */

public class DrawUtil {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
