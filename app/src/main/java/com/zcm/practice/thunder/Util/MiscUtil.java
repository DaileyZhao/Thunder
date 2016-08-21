package com.zcm.practice.thunder.Util;

import com.zcm.practice.thunder.ApplicationManager;

/**
 * Copyright(c) 2016 All Rights Reserved.
 * Author: ZhaoCunming
 * Time: 16-8-21 14:31
 * E-mail: zhaocunming@dayima.com
 * Version：V1.0.1
 * ProjectName: Thunder
 * Description: TODO
 */
public class MiscUtil {
    public static int px2dip(float pxValue){
        final float scale= ApplicationManager.context.getResources().getDisplayMetrics().density;
        return (int) (pxValue/scale+0.5f);
    }
    public static int dip2px(float dipValue){
        final  float scale=ApplicationManager.context.getResources().getDisplayMetrics().density;
        return (int) (dipValue*scale+0.5f);
    }
    public static int dip2px(double dipValue){
        final  float scale=ApplicationManager.context.getResources().getDisplayMetrics().density;
        return (int) (dipValue*scale+0.5f);
    }
}
