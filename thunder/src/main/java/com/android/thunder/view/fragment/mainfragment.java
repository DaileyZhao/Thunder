package com.android.thunder.view.fragment;

import android.widget.TextView;

import com.android.thunder.R;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zcm on 2017/1/6.
 */

public class MainFragment extends BaseFragment {
    @BindView(R.id.main_test_text)
    TextView main_test_text;
    @Override
    public void initViews() {
        OkHttpClient mOkHttpClient=new OkHttpClient();//创建okhttpclient对象
        Request request=new Request.Builder().url("http://www.baidu.com").build();//创建一个Request
        Call call=mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {//异步方式调用
            @Override
            public void onFailure(Call call, IOException e) {
                main_test_text.setText(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                main_test_text.setText(response.body().string());
            }
        });
    }
}
