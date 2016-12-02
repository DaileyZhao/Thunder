package com.android.thunder.http;

import android.widget.Toast;

import com.android.thunder.ThunderApplication;
import com.android.thunder.utils.MiscUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by dayima on 16-11-2.
 */

public class HttpControl {
    public static final String BASE_URL="http://www.kuaidi100.com";

    private static byte[] lock_sync = new byte[0];
    //主域名
    private static OkHttpClient mOkHttpClient;

    /**
     * 普通接口
     *
     * @return
     */
//    public static ApiStores retrofit() {
//        return retrofit(ROOT_URL_TYPE.TYPE_DEFAULT);
//    }


    public static ApiServers retrofit() {
        Retrofit retrofit;
        synchronized (lock_sync) {
                initHttpClient();
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(mOkHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
        }
        return retrofit.create(ApiServers.class);
    }

    private static void initHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        if (httpClientBuilder.interceptors() != null) {
            httpClientBuilder.interceptors().clear();
        }
        /**
         //缓存相关
         File cacheFile = new File(XinWoApplication.getContext().getExternalCacheDir(), "XinWoCache");
         Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);*/

        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //缓存处理
                Request request = chain.request();
//                String marvelHash = ApiUtils.generateMarvelHash(mApiKey, mApiSecret);

                // 添加公共参数
                HttpUrl.Builder authorizedUrlBuilder = request.url()
                        .newBuilder()
                        .scheme(request.url().scheme())
                        .host(request.url().host())
                        .addQueryParameter("sdkVer", "android")
                        .addQueryParameter("platform", "android");
                // 新的请求
                Request newRequest = request.newBuilder()
                        .method(request.method(), request.body())
                        .url(authorizedUrlBuilder.build())
                        .build();
                return chain.proceed(newRequest);
            }
        })
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);
        //在debug模式下log拦截器
        if (ThunderApplication.isDebug()) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }
        //通过build模式构建实例
        mOkHttpClient = httpClientBuilder.build();
        /**
         //带缓存实例
         mOkHttpClient = httpClientBuilder.cache(cache).build();*/
    }

    public static void buildHttpRequest(Observable<String> observable, final ResponseListener listener) {
        if (!MiscUtil.isNetworkConnected()){
            Toast.makeText(ThunderApplication.getContext(),"请检查网络",Toast.LENGTH_SHORT).show();
        }
        listener.showProgress();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        listener.disMissProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.disMissProgress();
                        listener.onError(e);
                    }

                    @Override
                    public void onNext(String s) {
                        listener.disMissProgress();
                        JSONObject object = null;
                        try {
                            object = new JSONObject(s);
                        } catch (JSONException e) {
                            listener.onFail(new JSONObject());
                        }
                        if (object == null) {
                            listener.onFail(new JSONObject());
                        } else {
                            if (object.optInt("errno", -1) == 0) {
                                listener.onSuccess(object);
                            } else {
                                listener.onFail(object);
                            }
                        }
                    }
                });
    }
}
