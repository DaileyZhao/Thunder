package com.android.thunder.view.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.thunder.R;
import com.android.thunder.http.ApiServers;
import com.android.thunder.http.HttpControl;
import com.android.thunder.http.ResponseListener;
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
    @BindView(R.id.tv_post)
    TextView tv_post;
    @BindView(R.id.flow_layout)
    LinearLayout flow_layout;
    @BindView(R.id.rulerWheel)
    RulerWheel rulerWheel;
    @BindView(R.id.curValue_tv)
    TextView curValue_tv;
    Intent intent=new Intent();
    String[] illness={"沙眼","寻常型银屑病","慢性结膜炎","结膜炎","白内障","青光眼"};
    @Override
    protected void initViews() {
        bt_title.setTitleText("闪电");
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
        rulerWheel.setData(list);
        rulerWheel.setScrollingListener(new RulerWheel.OnWheelScrollListener() {
            @Override
            public void onChanged(RulerWheel wheel, Object oldValue, Object newValue) {
                curValue_tv.setText(newValue + "");
            }

            @Override
            public void onScrollingStarted(RulerWheel wheel) {

            }

            @Override
            public void onScrollingFinished(RulerWheel wheel) {

            }
        });
        ApiServers api= HttpControl.retrofit();
        HttpControl.buildHttpRequest(api.getPostMessage("zhongtong", "419738635979"), new ResponseListener() {
            @Override
            public void onSuccess(JSONObject object) {
                String post=object.optString("message");
                tv_post.setText(post);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onFail(JSONObject object) {

            }

            @Override
            public void showProgress() {

            }

            @Override
            public void disMissProgress() {

            }
        });
        getMermoryLimited(this);
    }
    public void FlowLayout(String[] illness){
        for (int i=0;i<illness.length;i++){
            if (illness[i].length()>=6){
            LinearLayout holeline=new LinearLayout(this);
                holeline.addView(new TextView(this));
            }
        }
    }
    public void onclick(View view){
        startActivity(new Intent(this,WebViewActivity.class));
    }
    public void click(View view){
        startActivity(new Intent(this,RecyclerViewActivity.class));
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
        stopService(intent);
    }
    private void getPostMessage(){
        String baseUrl ="http://www.kuaidi100.com";
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .client(new OkHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
                .addConverterFactory(GsonConverterFactory.create()) // 使用Gson作为数据转换器
                .build();
        ApiServers apiServers=retrofit.create(ApiServers.class);
        apiServers.getPostMessage("zhongtong", "419738635979")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
                        tv_post.setText(s);
                    }
                });
    }

    class ComparatorByLength implements Comparator {   //定义比较器

        @Override
        public int compare(Object o1, Object o2) {
            String s1 = (String) o1;
            String s2 = (String) o2;
            int temp = s1.length() - s2.length();
            return temp == 0 ? temp:s1.compareTo(s2);
        }

    }
}
