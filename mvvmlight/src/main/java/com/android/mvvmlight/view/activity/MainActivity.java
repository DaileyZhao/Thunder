package com.android.mvvmlight.view.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.android.mvvmlight.BR;
import com.android.mvvmlight.R;
import com.android.mvvmlight.bean.UserBean;
import com.android.mvvmlight.databinding.MainactivityBinding;
import com.android.mvvmlight.viewmodel.LoginViewModel;

/**
 * Copyright(c) 2016 All Rights Reserved.
 * Author: ZhaoCunming
 * Time: 16-12-6 14:16
 * E-mail: zhaocunming@dayima.com
 * Version：V1.0.1
 * ProjectName: Thunder
 * Description: TODO
 */
public class MainActivity extends Activity {
    private UserBean userBean=new UserBean("Jack Sparrow","123");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainactivityBinding binding= DataBindingUtil.setContentView(this, R.layout.mainactivity);
        //binding.setUser(userBean);
        binding.setVariable(BR.user,userBean);
        binding.setVariable(BR.loginviewmodel,new LoginViewModel());
    }
}
