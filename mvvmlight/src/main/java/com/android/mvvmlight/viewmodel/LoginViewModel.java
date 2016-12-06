package com.android.mvvmlight.viewmodel;

import android.view.View;
import android.widget.Toast;

import com.android.mvvmlight.MvvmApplication;
import com.android.mvvmlight.model.UserBean;

/**
 * Copyright(c) 2016 All Rights Reserved.
 * Author: ZhaoCunming
 * Time: 16-12-6 16:17
 * E-mail: zhaocunming@dayima.com
 * Version：V1.0.1
 * ProjectName: Thunder
 * Description: TODO
 */
public class LoginViewModel {
    UserBean userBean=new UserBean("","");
    public void onTextChanged(CharSequence s, int start, int before, int count){
        userBean.setUsername(s.toString());
    }
    public void ontextchanged2(CharSequence s, int start, int before, int count){
        userBean.setPassword(s.toString());
    }
    public void onClick(View view){
        if (userBean.getUsername().equals("Jack Sparrow")&&userBean.getPassword().equals("123")){
            Toast.makeText(MvvmApplication.getContext(),"登录成功!",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MvvmApplication.getContext(),"登录失败!",Toast.LENGTH_SHORT).show();
        }
    }
}
