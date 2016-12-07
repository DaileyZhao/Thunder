package com.android.mvvmlight.viewmodel;

import android.databinding.ObservableBoolean;
import android.os.Handler;
import android.os.Message;
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
    public ObservableBoolean isShow=new ObservableBoolean();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isShow.set(false);
            switch (msg.what){
                case 0x001:
                    Toast.makeText(MvvmApplication.getContext(),"登录成功!",Toast.LENGTH_SHORT).show();
                    break;
                case 0x002:
                    Toast.makeText(MvvmApplication.getContext(),"登录失败!",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    public void onTextChanged(CharSequence s, int start, int before, int count){
        userBean.setUsername(s.toString());
    }
    public void ontextchanged2(CharSequence s, int start, int before, int count){
        userBean.setPassword(s.toString());
    }
    public void onClick(View view){
        isShow.set(true);
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                    if (userBean.getUsername().equals("Jack Sparrow")&&userBean.getPassword().equals("123")){
                        handler.sendEmptyMessage(0x001);
                    }else {
                        handler.sendEmptyMessage(0x002);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
