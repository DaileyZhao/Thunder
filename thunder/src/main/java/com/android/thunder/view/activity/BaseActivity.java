package com.android.thunder.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.android.thunder.R;

import java.util.Locale;

/**
 * Copyright(c) 2016 All Rights Reserved.
 * Author: ZhaoCunming
 * Time: 16-8-11 16:10
 * E-mail: zhaocunming@dayima.com
 * Version：V1.0.1
 * ProjectName: Thunder
 * Description: TODO
 */
public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Material_Light);
        setContentView(R.layout.baseactivity);
        setContent();
        initViews();
        initVariables();
    }
    /**
     * 根据类名来绑定布局
     */
    private View getContentView() {
        Class<?> clazz = this.getClass();
        View view = null;
        while (true) {
            try {
                view = (View) LayoutInflater.from(this).inflate(R.layout.class.getField(clazz.getSimpleName()
                        .toLowerCase(Locale.getDefault())).getInt(R.layout.class), null);
                break;
            } catch (Exception e) {
                e.printStackTrace();
                if (clazz.getClass().equals(Activity.class))
                    break;
                else
                    clazz = clazz.getSuperclass();
            }
        }
        return view;
    }
    private void setContent(){
        ((FrameLayout) findViewById(R.id.fm_root)).removeAllViews();
        ((FrameLayout) findViewById(R.id.fm_root)).addView(getContentView());
    }
    protected abstract void initViews();
    protected abstract void initVariables();
}
