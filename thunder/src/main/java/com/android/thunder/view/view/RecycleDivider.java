package com.android.thunder.view.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Copyright(c) 2016 All Rights Reserved.
 * Author: ZhaoCunming
 * Time: 16-12-29 10:30
 * E-mail: zhaocunming@dayima.com
 * Version：V1.0.1
 * ProjectName: Thunder
 * Description: TODO
 */
public class RecycleDivider extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    private Drawable recycledivider;
    public RecycleDivider(Context context){
        TypedArray typedArray=context.obtainStyledAttributes(ATTRS);
        recycledivider=typedArray.getDrawable(0);
        typedArray.recycle();
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int left=parent.getPaddingLeft();//列表项左边缘距离
        int right=parent.getWidth()-parent.getPaddingRight();//列表项右边缘距离
        int childcount=parent.getChildCount();
        for (int i=0;i<childcount;i++){
            View child=parent.getChildAt(i);
            RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) child.getLayoutParams();
            int top=child.getBottom()+params.bottomMargin;//计算分隔条左上角纵坐标
            int bottom=top+recycledivider.getIntrinsicHeight();//计算分隔条右下角纵坐标
            recycledivider.setBounds(left,top,right,bottom);//绘制分割线位置
            recycledivider.draw(c);
        }
    }
}
