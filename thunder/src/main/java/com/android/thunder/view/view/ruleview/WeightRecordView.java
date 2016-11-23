package com.android.thunder.view.view.ruleview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zcm.practice.thunder.CallBack.OnRecordSelectCallBack;
import com.zcm.practice.thunder.R;
import com.zcm.practice.thunder.Util.MiscUtil;

/**
 * @author Yuhj
 * @Description 记录身高的view
 * @Date: 16-8-21  上午9:49
 * @Version
 */


public class WeightRecordView extends LinearLayout {
    private HorizontalScrollView scrollView;

    private RuleView ruleView;

    private TextView tvRecord;


    private int targetWeight;

    public WeightRecordView(Context context) {
        this(context, null);
    }

    public WeightRecordView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeightRecordView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.box_weight_record_layout, this, true);
        tvRecord = (TextView) findViewById(R.id.tv_record);
        scrollView = (HorizontalScrollView) findViewById(R.id.scroolview);
        ruleView = (RuleView) findViewById(R.id.ruleview);
        ruleView.setData(0, 180);
        scrollView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        scrollView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                int offset = 0;
                                double position = ((double) (scrollView.getScrollX() - MiscUtil.dip2px(20))) / MiscUtil.dip2px(10);
                                if (position > 0) {
                                    if (position % MiscUtil.dip2px(10) > 0) {
                                        offset = (int) position + 1;
                                    } else {
                                        offset = (int) position;
                                    }

                                }
                                if (offset * MiscUtil.dip2px(10) > 0) {
                                    scrollView.smoothScrollTo(offset * MiscUtil.dip2px(10) + MiscUtil.dip2px(20), 0);
                                }

                                if (callBack != null) {
                                    callBack.OnSelect(offset + 22);
                                    tvRecord.setText((offset + 22) + " kg");
                                }

                            }
                        }, 100);
                        break;
                }
                return false;
            }

        });

    }

    private OnRecordSelectCallBack callBack;

    public void setOnRecordSelectCallBack(OnRecordSelectCallBack callBack) {
        this.callBack = callBack;
    }

    public void setTargetWeight(final int targetWeight) {
        this.targetWeight = targetWeight;
        tvRecord.setText(targetWeight + " kg");
        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.smoothScrollTo(targetWeight * MiscUtil.dip2px(10) - MiscUtil.dip2px(200), 0);
            }
        }, 200);
    }
}
