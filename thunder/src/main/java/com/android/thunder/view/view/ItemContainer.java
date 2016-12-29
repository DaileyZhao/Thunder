package com.android.thunder.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Copyright(c) 2016 All Rights Reserved.
 * Author: ZhaoCunming
 * Time: 16-12-20 12:03
 * E-mail: zhaocunming@dayima.com
 * Version：V1.0.1
 * ProjectName: Thunder
 * Description: TODO
 */
public class ItemContainer extends ViewGroup {
    private final static int VIEW_MARGIN=2;
    public ItemContainer(Context context) {
        this(context,null);
    }

    public ItemContainer(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ItemContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        // 如果是warp_content情况下，记录宽和高
        int width = 0;
        int height = 0;
        int childViewcount=getChildCount();
        int cWidth=0;
        int cHeight=0;
        /**
         * 记录每一行的宽度，width不断取最大宽度
         */
        int lineWidth = 0;
        MarginLayoutParams cParams = null;
        for (int i=0;i<childViewcount;i++){
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
            cParams = (MarginLayoutParams) childView.getLayoutParams();
            cWidth = childView.getMeasuredWidth()+cParams.leftMargin+cParams.rightMargin;
            cHeight = childView.getMeasuredHeight()+cParams.topMargin+cParams.bottomMargin;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childcount=getChildCount();
        int row=0;
        int lengthX=l; // right position of child relative to parent
        int lengthY=t; // bottom position of child relative to parent
        MarginLayoutParams cParams = null;
        for (int i=0;i<childcount;i++){
            View child = this.getChildAt(i);
            cParams = (MarginLayoutParams) child.getLayoutParams();
            int width = child.getMeasuredWidth()+cParams.leftMargin+cParams.rightMargin;
            int height = child.getMeasuredHeight()+cParams.topMargin+cParams.bottomMargin;
            lengthX+=width+VIEW_MARGIN;
            lengthY=row*(height+VIEW_MARGIN)+VIEW_MARGIN+height+t;
            if (lengthY>r/2){
                lengthX=width+VIEW_MARGIN+l;
                row++;
                lengthY=row*(height+VIEW_MARGIN)+VIEW_MARGIN+height+t;
            }
            child.layout(lengthX-width, lengthY-height, lengthX, lengthY);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}
