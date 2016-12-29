package com.android.thunder.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.android.thunder.R;
import com.android.thunder.view.adapter.RecyclerAdapter;
import com.android.thunder.view.view.RecycleDivider;

import butterknife.BindView;

/**
 * Copyright(c) 2016 All Rights Reserved.
 * Author: ZhaoCunming
 * Time: 16-12-16 11:37
 * E-mail: zhaocunming@dayima.com
 * Version：V1.0.1
 * ProjectName: Thunder
 * Description: TODO
 */
public class RecyclerViewActivity extends BaseActivity {
    @BindView(R.id.recyclerlistview)
    RecyclerView recyclerlistview;
    @Override
    protected void initViews() {
        //  创建线性布局管理器（默认是垂直方向）
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerlistview.setLayoutManager(layoutManager);
        //  创建列表项分隔线对象
        RecyclerView.ItemDecoration itemDecoration=new RecycleDivider(this);
        recyclerlistview.addItemDecoration(itemDecoration);
        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(this);
        recyclerlistview.setAdapter(recyclerAdapter);
    }

    @Override
    protected void initVariables() {

    }
}
