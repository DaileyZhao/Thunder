package com.zcm.practice.thunder.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


import com.zcm.practice.thunder.R;
import com.zcm.practice.thunder.Util.MiscUtil;

import java.util.ArrayList;

/**
 * @author Yuhj
 * @Description 刻度View
 * @Date: 16-8-19  下午4:05
 * @Version
 */


public class RuleView extends View {
    private ArrayList<Integer> data = new ArrayList<>();

    private Paint paint;

    private int min, max;

    private final static int MARGIN_LEFT = MiscUtil.dip2px(20);

    /**
     * 左右两边最大刻度
     */
    private final static int MAXHEIGHT = MiscUtil.dip2px(30);
    /**
     * 左中刻度
     */
    private final static int HEIGHT_LEFT = MiscUtil.dip2px(20);
    /**
     * 最小刻度
     */
    private final static int MINHEIGHT = MiscUtil.dip2px(10);

    /**
     * 横向间距
     */
    private final static int HOR_PADDING = MiscUtil.dip2px(10);


    public RuleView(Context context) {
        this(context, null);
    }

    public RuleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RuleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setData(int min, int max) {
        this.min = min;
        this.max = max;
        for (int i = min; i <= max; i += 10) {
            data.add(i);
        }
        postInvalidate();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.rel_black));
        paint.setStrokeWidth(MiscUtil.dip2px(0.8));
        paint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((data.size() - 1) * 10 * HOR_PADDING + MARGIN_LEFT * 2, MiscUtil.dip2px(80));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < data.size() - 1; i++) {
            for (int j = 0; j < 10; j++) {
                int x = MARGIN_LEFT + (i * 10 + j) * HOR_PADDING;
                if (j == 0) {
                    paint.setTextSize(MiscUtil.dip2px(14));
                    canvas.drawLine(x, 0, x, MAXHEIGHT, paint);
                    canvas.drawText(String.valueOf(data.get(i)), x - (int) (paint.measureText(String.valueOf(data.get(i))) / 2), MAXHEIGHT + MiscUtil.dip2px(20), paint);
                } else if (j == 5) {
                    canvas.drawLine(x, MiscUtil.dip2px(5), x, HEIGHT_LEFT + MiscUtil.dip2px(5), paint);
                } else {
                    canvas.drawLine(x, MiscUtil.dip2px(10), x, MINHEIGHT + MiscUtil.dip2px(10), paint);
                }
            }
        }
        canvas.drawText(String.valueOf(data.get(data.size() - 1)), MARGIN_LEFT + ((data.size() - 1) * 10) * HOR_PADDING - (int) (paint.measureText(String.valueOf(data.get(data.size() - 1))) / 2), MAXHEIGHT + MiscUtil.dip2px(20), paint);

        canvas.drawLine(MARGIN_LEFT + ((data.size() - 1) * 10) * HOR_PADDING, 0, MARGIN_LEFT + ((data.size() - 1) * 10) * HOR_PADDING, MAXHEIGHT, paint);

        canvas.drawLine(MiscUtil.dip2px(10), MINHEIGHT + MiscUtil.dip2px(10), ((data.size()) * 10) * HOR_PADDING + MiscUtil.dip2px(10), MINHEIGHT + MiscUtil.dip2px(10), paint);
    }
}
