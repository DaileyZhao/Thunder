package com.zcm.practice.thunder.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.zcm.practice.thunder.Util.MiscUtil;

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
    public AudioView(Context context) {
        this(context,null);
    }

    public AudioView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public AudioView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        paint=new Paint();
        paint.setAntiAlias(false);
        paint.setColor(getResources().getColor(android.R.color.holo_blue_bright));
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
}
