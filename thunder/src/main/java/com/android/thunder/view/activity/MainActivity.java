package com.android.thunder.view.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.thunder.R;
import com.android.thunder.http.ApiServers;
import com.android.thunder.http.HttpControl;
import com.android.thunder.http.ResponseListener;

import org.json.JSONObject;

import butterknife.BindView;

/**
 * Copyright(c) 2016 All Rights Reserved.
 * Author: ZhaoCunming
 * Time: 16-11-23 12:38
 * E-mail: zhaocunming@dayima.com
 * Version：V1.0.1
 * ProjectName: Thunder
 * Description: TODO
 */
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private long lastExitRequestTime;
    @BindView(R.id.tv_post)
    TextView tv_post;
    @Override
    protected void initViews() {
    }

    @Override
    protected void initVariables() {
       ApiServers api= HttpControl.retrofit();
        HttpControl.buildHttpRequest(api.getPostMessage("zhongtong", "419738635979"), new ResponseListener() {
            @Override
            public void onSuccess(JSONObject object) {
                String post=object.optString("message");
                tv_post.setText(post);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onFail(JSONObject object) {

            }

            @Override
            public void showProgress() {

            }

            @Override
            public void disMissProgress() {

            }
        });
    }
    public void onclick(View view){
        startActivity(new Intent(this,WebViewActivity.class));
    }
    /**
     * 三秒内两次点击 以退出(回桌面)
     */
    private void exitOnSecondTime() {
        if (System.currentTimeMillis() - lastExitRequestTime <= 3000) {
            goHome();
            lastExitRequestTime = 0;
        } else {
            Toast.makeText(this,"再次点击返回退出",Toast.LENGTH_SHORT).show();
            lastExitRequestTime = System.currentTimeMillis();
        }
    }

    public void goHome() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitOnSecondTime();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
