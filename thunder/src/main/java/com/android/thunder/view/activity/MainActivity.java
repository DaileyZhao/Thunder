package com.android.thunder.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.thunder.R;
import com.android.thunder.http.ApiServers;
import com.android.thunder.http.HttpMethods;

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
    @BindView(R.id.post_detail)
    TextView postDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initVariables() {
        HttpMethods.createApi(ApiServers.class).getPostMessage("zhongtong","701223137508");
    }
    public void onclick(View view){
        startActivity(new Intent(this,WebViewActivity.class));
    }
}
