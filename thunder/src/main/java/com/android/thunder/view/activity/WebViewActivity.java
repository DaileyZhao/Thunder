package com.android.thunder.view.activity;

import android.view.KeyEvent;

import com.android.thunder.R;
import com.android.thunder.view.view.WebViewBase;

import butterknife.BindView;

/**
 * Copyright(c) 2016 All Rights Reserved.
 * Author: ZhaoCunming
 * Time: 16-11-28 11:19
 * E-mail: zhaocunming@dayima.com
 * Version：V1.0.1
 * ProjectName: Thunder
 * Description: TODO
 */
public class WebViewActivity extends BaseActivity {
    @BindView(R.id.webview)
    WebViewBase webView;
    private String url="https://www.baidu.com/";
    @Override
    protected void initViews() {
        webView.setOnWebCallBack(new WebViewBase.OnWebCallBack(){

            @Override
            public void getTitle(String title) {
                bt_title.setTitleText(title);
            }
        });
    }

    @Override
    protected void initVariables() {
        webView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
