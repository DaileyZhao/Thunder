package com.android.thunder.view.activity;

import android.app.ActivityManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.thunder.R;
import com.android.thunder.services.DownloadService;
import com.android.thunder.view.fragment.BaseFragment;
import com.android.thunder.view.fragment.MainFragment;
import com.android.thunder.view.fragment.MessageFragment;
import com.android.thunder.view.fragment.NavigationFragment;
import com.android.thunder.view.fragment.SettingFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Copyright(c) 2016 All Rights Reserved.
 * Author: ZhaoCunming
 * Time: 16-11-23 12:38
 * E-mail: zhaocunming@dayima.com
 * Version：V1.0.1
 * ProjectName: Thunder
 * Description: TODO
 */
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private long lastExitRequestTime;
    @BindView(R.id.main_radio_group)
    RadioGroup main_radio_group;
    @BindView(R.id.rb_tab1)
    RadioButton rb_tab1;
    @BindView(R.id.rb_tab2)
    RadioButton rb_tab2;
    @BindView(R.id.rb_tab3)
    RadioButton rb_tab3;
    @BindView(R.id.rb_tab4)
    RadioButton rb_tab4;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private MainFragment mainFragment;
    private NavigationFragment navigationFragment;
    private MessageFragment messageFragment;
    private SettingFragment settingFragment;
    private List<BaseFragment> frmlist;
    private static final int MAIN = 0;
    private static final int NAVIGATION = MAIN + 1;
    private static final int MESSAGE = NAVIGATION + 1;
    private static final int SETTING = MESSAGE + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bt_title.setVisibility(View.GONE);
        getMermoryLimited(this);
        manager=getFragmentManager();
        main_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                transaction=manager.beginTransaction();
                hideAllFragment(transaction);
                switch (checkedId){
                    case R.id.rb_tab1:
                        if (mainFragment==null){
                            mainFragment=new MainFragment();
                            transaction.add(R.id.fl_content,mainFragment);
                        }else {
                            transaction.show(mainFragment);
                        }
                        break;
                    case R.id.rb_tab2:
                        if (navigationFragment==null){
                            navigationFragment=new NavigationFragment();
                            transaction.add(R.id.fl_content,navigationFragment);
                        }else {
                            transaction.show(navigationFragment);
                        }
                        break;
                    case R.id.rb_tab3:
                        if (messageFragment==null){
                            messageFragment=new MessageFragment();
                            transaction.add(R.id.fl_content,messageFragment);
                        }else {
                            transaction.show(messageFragment);
                        }
                        break;
                    case R.id.rb_tab4:
                        if (settingFragment==null){
                            settingFragment=new SettingFragment();
                            transaction.add(R.id.fl_content,settingFragment);
                        }else {
                            transaction.show(settingFragment);
                        }
                        break;
                }
                transaction.commit();
            }
        });
        rb_tab1.setChecked(true);
        startService(new Intent(this, DownloadService.class));
    }
    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(mainFragment != null)fragmentTransaction.hide(mainFragment);
        if(navigationFragment != null)fragmentTransaction.hide(navigationFragment);
        if(messageFragment != null)fragmentTransaction.hide(messageFragment);
        if(settingFragment != null)fragmentTransaction.hide(settingFragment);
    }
    /**
     * 三秒内两次点击 以退出(回桌面)
     */
    private void exitOnSecondTime() {
        if (System.currentTimeMillis() - lastExitRequestTime <= 3000) {
            goHome();
            lastExitRequestTime = 0;
        } else {
            Toast.makeText(this,"再次点击返回退出",Toast.LENGTH_SHORT).show();
            lastExitRequestTime = System.currentTimeMillis();
        }
    }
    public void goHome() {
        moveTaskToBack(true);
    }

    public void getMermoryLimited(Context context){
        ActivityManager activityManager= (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        Log.e(TAG, "getMermoryLimited: "+activityManager.getMemoryClass() );
        Log.e(TAG, "getMermoryLimited: "+activityManager.getLargeMemoryClass() );
        Log.e(TAG, "getMermoryLimited: "+Runtime.getRuntime().maxMemory()/(1024*1024)+"MB" );
        Log.e(TAG, "getMermoryLimited: "+Thread.currentThread().getName() );
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitOnSecondTime();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
