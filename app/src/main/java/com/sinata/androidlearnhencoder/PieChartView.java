package com.sinata.androidlearnhencoder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.LinkedHashMap;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2021
 * Company:成都博智维讯信息技术股份有限公司
 *
 * @author jingqiang.cheng
 * @date 2021/12/1
 */
public class PieChartView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final float RADIUS = Utils.dp2px(150);
    private static final float PULLED_LENGTH = Utils.dp2px(20);
    private int PULLED_INDEX = 3;
    private static String[] COLORS = new String[]{
            "#448AFF",
            "#D81B60",
            "#43A047",
            "#FDD835"
    };

    private static int[] ANGLES = new int[]{
            60, 120, 80, 100
    };
    private RectF mRectF = new RectF();

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int currentAngle = 0;
        for (int i = 0; i < COLORS.length; i++) {
            paint.setColor(Color.parseColor(COLORS[i]));
            if (PULLED_INDEX == i) {
                canvas.save();
                canvas.translate((float) Math.cos(Math.toRadians(currentAngle + ANGLES[i] / 2f)) * PULLED_LENGTH, (float) Math.sin(Math.toRadians(currentAngle + ANGLES[i] / 2f)) * PULLED_LENGTH);
            }
            canvas.drawArc(mRectF, currentAngle, ANGLES[i], true, paint);
            if (PULLED_INDEX == i) {
                canvas.restore();
            }
            currentAngle += ANGLES[i];
        }
    }
}
