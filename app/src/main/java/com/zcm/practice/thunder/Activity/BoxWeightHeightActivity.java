package com.zcm.practice.thunder.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


import com.zcm.practice.thunder.CallBack.OnRecordSelectCallBack;
import com.zcm.practice.thunder.R;
import com.zcm.practice.thunder.View.HeightRecordView;
import com.zcm.practice.thunder.View.WeightRecordView;

import java.text.DecimalFormat;

/**
 * @author Yuhj
 * @Description 用户的身高和体重记录
 * @Date: 16-8-19  下午3:11
 * @Version
 */


public class BoxWeightHeightActivity extends Activity {
    private TextView tvNext;
    private TextView tvBmi;
    public static String TAG_AGE = "key_age";
    private int age = 0;
    private int targetWeight, targetHeight;
    private HeightRecordView heightRecordView;
    private WeightRecordView weightRecordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boxweightheightactivity);
        initView();
        age = getIntent().getIntExtra(TAG_AGE, 10);
        initView();
    }

    private void initView() {
        tvNext = (TextView) findViewById(R.id.tv_next);
        tvBmi = (TextView) findViewById(R.id.tv_bmi);
        heightRecordView = (HeightRecordView) findViewById(R.id.heightRecordbiew);
        weightRecordView = (WeightRecordView) findViewById(R.id.weightRecordbiew);
        initWeightAndHeight();
        heightRecordView.setOnRecordSelectCallBack(new OnRecordSelectCallBack() {
            @Override
            public void OnSelect(int record) {
                targetHeight = record;
                setBmi();

            }
        });

        weightRecordView.setOnRecordSelectCallBack(new OnRecordSelectCallBack() {
            @Override
            public void OnSelect(int record) {
                targetWeight = record;
                setBmi();
            }
        });

    }

    private void setBmi() {
        if (targetHeight != 0) {
            double bmi = targetWeight / (((double) targetHeight / 100) * ((double) targetHeight / 100));
            DecimalFormat df = new DecimalFormat("0.0");
            String result = df.format(bmi);
            tvBmi.setText("BMI " + result);
        }
    }

    private void initWeightAndHeight() {
        if (age >= 1 && age < 10) {
            heightRecordView.setTargetHeight(100);
            weightRecordView.setTargetWeight(30);
            targetHeight = 100;
            targetWeight = 30;
        } else if (age >= 10 && age <= 20) {
            heightRecordView.setTargetHeight(150);
            weightRecordView.setTargetWeight(60);
            targetHeight = 150;
            targetWeight = 60;
        } else if (age > 20) {
            heightRecordView.setTargetHeight(150);
            weightRecordView.setTargetWeight(60);
            targetHeight = 150;
            targetWeight = 60;
        }

        setBmi();
    }


}
