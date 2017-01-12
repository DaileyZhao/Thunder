package com.android.thunder.model;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zcm on 2017/1/10.
 */

public class OkHttpAsyncTask extends AsyncTask<Integer,Integer,String> {
    TextView textView;
    OkHttpClient mOkHttpClient=new OkHttpClient();//创建okhttpclient对象
    public OkHttpAsyncTask(TextView textView){
        super();
        this.textView=textView;
    }
    /**
     * 这里的Integer参数对应AsyncTask中的第一个参数
     * 这里的String返回值对应AsyncTask的第三个参数
     * 该方法并不运行在UI线程当中，主要用于异步操作，所有在该方法中不能对UI当中的空间进行设置和修改
     * 但是可以调用publishProgress方法触发onProgressUpdate对UI进行操作
     */
    @Override
    protected String doInBackground(Integer... params) {
        Request request=new Request.Builder().url("http://api.kanzhihu.com/getposts/1484044454").build();//创建一个Request
        Call call=mOkHttpClient.newCall(request);
        String result;
//        call.enqueue(new Callback() {//异步方式调用
//            @Override
//            public void onFailure(Call call, IOException e) {
//                main_test_text.setText(e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                main_test_text.setText(response.body().string());
//            }
//        });

        Response response= null;
        try {
            response = call.execute();
            result=response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return result;
    }
    /**
     * 这里的String参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）
     * 在doInBackground方法执行结束之后在运行，并且运行在UI线程当中 可以对UI空间进行设置
     */
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        textView.setText("异步操作执行结束" + s);
    }
    //该方法运行在UI线程当中,并且运行在UI线程当中 可以对UI空间进行设置
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        textView.setText("开始执行异步线程");
    }
    /**
     * 这里的Intege参数对应AsyncTask中的第二个参数
     * 在doInBackground方法当中，，每次调用publishProgress方法都会触发onProgressUpdate执行
     * onProgressUpdate是在UI线程中执行，所以可以对UI空间进行操作
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
//    public static void setCertificates(InputStream... certificates) throws Exception{
//        CertificateFactory certificateFactory=CertificateFactory.getInstance("X.509");
//        KeyStore keyStore=KeyStore.getInstance(KeyStore.getDefaultType());
//        keyStore.load(null);
//        int index=0;
//        for (InputStream certificate:certificates){
//            String certificateAlias=Integer.toString(index++);
//            keyStore.setCertificateEntry(certificateAlias,certificateFactory.generateCertificate(certificate));
//            if (certificate != null)
//                certificate.close();
//        }
//        SSLContext sslContext=SSLContext.getInstance("TLS");
//        TrustManagerFactory trustManagerFactory=TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//        trustManagerFactory.init(keyStore);
//        sslContext.init(null,trustManagerFactory.getTrustManagers(),new SecureRandom());
//        mOkHttpClient.newBuilder().sslSocketFactory(sslContext.getSocketFactory());
//    }
}
