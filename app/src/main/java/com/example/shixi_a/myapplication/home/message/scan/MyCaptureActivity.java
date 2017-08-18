package com.example.shixi_a.myapplication.home.message.scan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.shixi_a.myapplication.R;
import com.example.shixi_a.myapplication.util.ToastUtils;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by a5376 on 2017/8/8.
 */

public class MyCaptureActivity extends AppCompatActivity {
    private Object permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getpermission();

        setContentView(R.layout.activity_camera);
        CaptureFragment captureFragment = new CaptureFragment();
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.camera_container, captureFragment).commit();
//        captureFragment.setCameraInitCallBack(new CaptureFragment.CameraInitCallBack() {
//            @Override
//            public void callBack(Exception e) {
//                if (e == null) {
//
//                } else {
//                    Log.e("TAG", "callBack: ", e);
//                }
//            }
//        });

    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, final String result) {
            ToastUtils.showShort(MyCaptureActivity.this,"扫描成功");
            Timer timer = new Timer();
            TimerTask task = new TimerTask(){
                public void run(){
                    Intent resultIntent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
                    bundle.putString(CodeUtils.RESULT_STRING, result);
                    resultIntent.putExtras(bundle);
                    MyCaptureActivity.this.setResult(RESULT_OK, resultIntent);

                    MyCaptureActivity.this.finish();

                }
            };
            timer.schedule(task, 1000);


        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            MyCaptureActivity.this.setResult(RESULT_OK, resultIntent);
            MyCaptureActivity.this.finish();
        }
    };
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                ToastUtils.showShort(getApplicationContext(),"请在设置中开启相机权限");
            }
        }
    }
    public void getpermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
    }
}
