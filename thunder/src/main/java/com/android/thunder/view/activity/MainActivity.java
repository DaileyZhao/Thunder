package com.android.thunder.view.activity;

import android.app.ActivityManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.thunder.R;
import com.android.thunder.http.ApiServers;
import com.android.thunder.http.HttpControl;
import com.android.thunder.http.ResponseListener;
import com.android.thunder.utils.MiscUtil;
import com.android.thunder.view.fragment.BaseFragment;
import com.android.thunder.view.fragment.MainFragment;
import com.android.thunder.view.fragment.MessageFragment;
import com.android.thunder.view.fragment.NavigationFragment;
import com.android.thunder.view.fragment.SettingFragment;
import com.android.thunder.view.view.RulerWheel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
//    @BindView(R.id.tv_post)
//    TextView tv_post;
//    @BindView(R.id.flow_layout)
//    LinearLayout flow_layout;
//    @BindView(R.id.rulerWheel)
//    RulerWheel rulerWheel;
//    @BindView(R.id.curValue_tv)
//    TextView curValue_tv;
//    @BindView(R.id.test_progressbar)
//    ProgressBar test_progressbar;
//    Intent intent=new Intent();
//    int pro=0;
//    String[] illness={"沙眼","寻常型银屑病","慢性结膜炎","结膜炎","白内障","青光眼"};
//    private Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case 0x001:
//                    test_progressbar.setProgress(pro);
//                    break;
//            }
//        }
//    };
    @Override
    protected void initViews() {
        bt_title.setVisibility(View.GONE);
        manager=getFragmentManager();
        if (manager!=null){
            transaction=manager.beginTransaction();
        }
        frmlist=new ArrayList<>();
        mainFragment=new MainFragment();
        frmlist.add(mainFragment);
        transaction.add(R.id.fl_content,mainFragment);
        navigationFragment=new NavigationFragment();
        frmlist.add(navigationFragment);
        transaction.add(R.id.fl_content,navigationFragment);
        messageFragment=new MessageFragment();
        frmlist.add(messageFragment);
        transaction.add(R.id.fl_content,messageFragment);
        settingFragment=new SettingFragment();
        frmlist.add(settingFragment);
        transaction.add(R.id.fl_content,settingFragment);
        transaction.commit();
        main_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                BaseFragment to=null;
                switch (checkedId){
                    case R.id.rb_tab1:
                        to=frmlist.get(MAIN);
                        transaction.replace(R.id.fl_content,to);
                        break;
                    case R.id.rb_tab2:
                        to=frmlist.get(NAVIGATION);
                        transaction.replace(R.id.fl_content,to);
                        break;
                    case R.id.rb_tab3:
                        to=frmlist.get(MESSAGE);
                        transaction.replace(R.id.fl_content,to);
                        break;
                    case R.id.rb_tab4:
                        to=frmlist.get(SETTING);
                        transaction.replace(R.id.fl_content,to);
                        break;
                }
                transaction.commit();
            }
        });
    }

    @Override
    protected void initVariables() {
        List<String> list = new ArrayList<>();
        for (int i = 30; i < 150; i += 1) {
            list.add(i + "");
            for (int j = 1; j < 10; j++) {
                list.add(i + "." + j);
            }
        }
//        rulerWheel.setData(list);
//        rulerWheel.setSelectedValue("100");
//        rulerWheel.setScrollingListener(new RulerWheel.OnWheelScrollListener() {
//            @Override
//            public void onChanged(RulerWheel wheel, Object oldValue, Object newValue) {
//                curValue_tv.setText(newValue + "");
//            }
//
//            @Override
//            public void onScrollingStarted(RulerWheel wheel) {
//
//            }
//
//            @Override
//            public void onScrollingFinished(RulerWheel wheel) {
//
//            }
//        });
        getMermoryLimited(this);
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
