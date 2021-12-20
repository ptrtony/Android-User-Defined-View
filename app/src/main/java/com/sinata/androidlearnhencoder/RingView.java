package com.sinata.androidlearnhencoder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2021
 * Company:成都博智维讯信息技术股份有限公司
 *
 * @author jingqiang.cheng
 * @date 2021/12/1
 */


public class RingView extends View {

    private static final float RADIUS = Utils.dp2px(100);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int GRAY_COLOR = Color.parseColor("#EEEEEE");
    private int HIGH_COLOR = Color.parseColor("#D81B60");
    private int TEXT_COLOR = Color.parseColor("#00897B");
    private RectF rectF = new RectF();
    private Rect bounds = new Rect();

    public RingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStrokeWidth(Utils.dp2px(10));
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制背景
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(GRAY_COLOR);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, paint);

        //绘制圆环
        paint.setColor(HIGH_COLOR);
        canvas.drawArc(rectF, 0, 120, false, paint);

        //绘制文本
        paint.setTextSize(Utils.dp2px(40));
        paint.setColor(TEXT_COLOR);
        paint.setStyle(Paint.Style.FILL);
        String text = "abab";
        paint.getTextBounds(text, 0, text.length(), bounds);

        int offsetY = (bounds.top + bounds.bottom) / 2;
        int offsetX = (bounds.left + bounds.right) / 2;
        canvas.drawText(text, getWidth() / 2f - offsetX, getHeight() / 2f - offsetY, paint);
    }
}
