package com.android.thunder.view.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Message;
import android.util.AttributeSet;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import org.apache.http.HttpStatus;

/**
 * Copyright(c) 2016 All Rights Reserved.
 * Author: ZhaoCunming
 * Time: 16-11-28 15:07
 * E-mail: zhaocunming@dayima.com
 * Version：V1.0.1
 * ProjectName: Thunder
 * Description: TODO
 */
public class WebViewBase extends WebView {
    public interface OnWebCallBack{
        void getTitle(String title);
    }
    private Context context ;
    private ProgressBar progressbar ;
    private OnWebCallBack onWebCallBack;
    public void setOnWebCallBack(OnWebCallBack onWebCallBack){
        this.onWebCallBack=onWebCallBack;
    }
    public WebViewBase(Context context) {
        super(context);
        this.context = context ;
        init();
    }

    public WebViewBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context ;
        init();
    }

    public WebViewBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context ;
        init();
    }
    @SuppressLint("SetJavaScriptEnabled")
    private void init(){
        progressbar = new ProgressBar( context , null , android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT, 20 , 0, 0 ));
        addView( progressbar ) ;
        WebSettings webSettings = this.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);
        this.setVerticalScrollBarEnabled(true);
        this.setWebViewClient(mWebViewClientBase);
        this.setWebChromeClient(mWebChromeClientBase);
    }
    private WebViewClientBase mWebViewClientBase = new WebViewClientBase();
    private WebChromeClientBase mWebChromeClientBase = new WebChromeClientBase();

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    protected int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    private class WebViewClientBase extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            switch(errorCode)
            {
                case HttpStatus.SC_NOT_FOUND:
                    view.loadUrl("file:///android_assets/error_handle.html");
                    break;
            }
        }

        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            super.doUpdateVisitedHistory(view, url, isReload);
        }
    }
    private class WebChromeClientBase extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (onWebCallBack!=null){
                onWebCallBack.getTitle(title);
            }
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url,
                                           boolean precomposed) {
            // TODO Auto-generated method stub
            super.onReceivedTouchIconUrl(view, url, precomposed);
        }

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog,
                                      boolean isUserGesture, Message resultMsg) {
            // TODO Auto-generated method stub
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            callback.invoke(origin,true,false);
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }
    }
}
