package com.android.thunder.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.android.thunder.utils.MiscUtil;


/**
 * Copyright(c) 2016 All Rights Reserved.
 * Author: ZhaoCunming
 * Time: 16-11-10 15:22
 * E-mail: zhaocunming@dayima.com
 * Version：V1.0.1
 * ProjectName: Thunder
 * Description: TODO
 */
public class AudioView extends View {
    private int mRectCount=12;
    private int offset=5;
    private int mRectWidth= MiscUtil.dip2px(10);
    private int mRectHeight=MiscUtil.dip2px(80);
    private double mRandom;
    private int mWidth;
    private Paint paint;
    private LinearGradient mLinearGradient;
    private int lastX;
    private int lastY;
    private Scroller mScroller;
    public AudioView(Context context) {
        this(context,null);
    }

    public AudioView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public AudioView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        paint=new Paint();
        paint.setAntiAlias(false);
        paint.setColor(getResources().getColor(android.R.color.holo_blue_bright));
        mScroller = new Scroller(context);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i=0;i<mRectCount;i++){
            mRandom=Math.random();
            float currentHeight= (float) (mRectHeight*mRandom);
            canvas.drawRect((float) (mWidth*0.4/2+mRectWidth*i+offset),currentHeight,(float) (mWidth*0.4/2+mRectWidth*(i+1)),mRectHeight,paint);
        }
        postInvalidateDelayed(300);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=getWidth();
        mRectHeight=getHeight();
        mLinearGradient=new LinearGradient(0,0,mRectWidth,mRectHeight, Color.RED,Color.BLUE, Shader.TileMode.CLAMP);
        paint.setShader(mLinearGradient);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录触摸点坐标
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                // 计算偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                // 在当前left、top、right、bottom的基础上加上偏移量
//                layout(getLeft() + offsetX,
//                        getTop() + offsetY,
//                        getRight() + offsetX,
//                        getBottom() + offsetY);
//                        offsetLeftAndRight(offsetX);
//                        offsetTopAndBottom(offsetY);
               ((View)getParent()).scrollBy(-offsetX,-offsetY);
                break;
            case MotionEvent.ACTION_UP:
                View viewGroup = ((View) getParent());
                mScroller.startScroll(
                        viewGroup.getScrollX(),
                        viewGroup.getScrollY(),
                        -viewGroup.getScrollX(),
                        -viewGroup.getScrollY());
                invalidate();
                break;
        }
        return true;
    }
    @Override
    public void computeScroll() {
        super.computeScroll();
        // 判断Scroller是否执行完毕
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(
                    mScroller.getCurrX(),
                    mScroller.getCurrY());
            // 通过重绘来不断调用computeScroll
            invalidate();
        }
    }
}
