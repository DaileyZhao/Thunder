package com.android.thunder.view.view.ruleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;

import com.zcm.practice.thunder.R;
import com.zcm.practice.thunder.Util.MiscUtil;

/**
 * Copyright(c) 2016 All Rights Reserved.
 * Author: ZhaoCunming
 * Time: 16-11-18 12:12
 * E-mail: zhaocunming@dayima.com
 * Version：V1.0.1
 * ProjectName: Thunder
 * Description: TODO
 */
public class RuleViewer extends View {
    private static final String TAG = "RuleViewer";
    public static final int MAXHEIGHT=MiscUtil.dip2px(30);
    public static final int MINHEIGHT=MiscUtil.dip2px(15);
    public static final int PERIODRULE = 1;
    public static final int WEIGHTRULE = 2;
    public static final int NORMALRULE = 3;
    private Paint paint;
    private int TouchSlop;
    private float viewHeight;
    private float viewWidth;
    private int minValue,maxValue;
    private int ruletype;
    private int scalewidth;
    private VelocityTracker velocityTracker;
    public RuleViewer(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public RuleViewer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RuleViewer(Context context) {
        this(context,null);
    }

    private void init(){
        paint=new Paint();
        paint.setColor(getResources().getColor(R.color.rel_black));
        paint.setStrokeWidth(MiscUtil.dip2px(0.8));
        paint.setAntiAlias(true);
        paint.setTextSize(24);
        TouchSlop= ViewConfiguration.get(getContext()).getScaledTouchSlop();
        velocityTracker=VelocityTracker.obtain();
    }
    private void drawCenterLine(Canvas canvas){
        canvas.drawLine(0,viewHeight/2,viewWidth,viewHeight/2,paint);
    }
    private void drawScale(Canvas canvas){
        for (int i=0;i<=maxValue;i++){
            switch (ruletype){
                case NORMALRULE:
                case WEIGHTRULE:
                    if (i%5==0){
                        canvas.drawLine(i*scalewidth,viewHeight/4,i*scalewidth,viewHeight/2,paint);
                        canvas.drawText(String.valueOf(i),i*scalewidth-paint.measureText(String.valueOf(i))/2,viewHeight/4*3,paint);
                    }
                    else
                        canvas.drawLine(i*scalewidth,viewHeight/8*3,i*scalewidth,viewHeight/2,paint);
                    break;
                case PERIODRULE:
                    canvas.drawLine(i*scalewidth,viewHeight/8*3,i*scalewidth,viewHeight/2,paint);
                    canvas.drawText(String.valueOf(i),i*scalewidth-paint.measureText(String.valueOf(i))/2,viewHeight/4*3,paint);
                    break;
            }
        }
    }
    public void setValue(int minValue,int maxValue,int ruletype){
        this.minValue=minValue;
        this.maxValue=maxValue;
        this.ruletype=ruletype;
        switch (ruletype){
            case NORMALRULE:
            case WEIGHTRULE:
                scalewidth=MiscUtil.dip2px(10);
                break;
            case PERIODRULE:
                scalewidth=MiscUtil.dip2px(50);
                break;
        }
        viewWidth=(maxValue-minValue)*scalewidth;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCenterLine(canvas);
        drawScale(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        velocityTracker.addMovement(event);
        velocityTracker.computeCurrentVelocity(1000);
        int xVelocity= (int) velocityTracker.getXVelocity();
        int yVelocity= (int) velocityTracker.getYVelocity();
        Log.e(TAG, "onTouchEvent: xVelocity "+xVelocity+"yVelocity  "+yVelocity);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent: ACTION_DOWN" );
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent: ACTION_MOVE" );
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent: ACTION_UP" );
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(TAG, "onTouchEvent: ACTION_CANCEL" );
                break;
        }
        return true;
    }
}
