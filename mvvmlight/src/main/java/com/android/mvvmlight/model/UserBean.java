package com.android.mvvmlight.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.mvvmlight.BR;

/**
 * Copyright(c) 2016 All Rights Reserved.
 * Author: ZhaoCunming
 * Time: 16-12-6 14:34
 * E-mail: zhaocunming@dayima.com
 * Version：V1.0.1
 * ProjectName: Thunder
 * Description: TODO
 */
public class UserBean extends BaseObservable{
    private String username;
    private String password;
    public UserBean(String username,String password){
        this.username=username;
        this.password=password;
    }
    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
