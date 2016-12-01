package com.android.thunder.view.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.android.thunder.ThunderApplication;

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
    private long lastExitRequestTime;

    @Override
    protected void initViews() {

    }

    @Override
    protected void initVariables() {
        //HttpMethods.createApi(ApiServers.class).getPostMessage("zhongtong","701223137508");
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
