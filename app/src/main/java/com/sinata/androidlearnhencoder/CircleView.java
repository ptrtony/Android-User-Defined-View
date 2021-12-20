package com.sinata.androidlearnhencoder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
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
 * @date 2021/12/6
 */
public class CircleView extends View {

    private float RADIUS = Utils.dp2px(50);
    private float PADDING = Utils.dp2px(50);
    private Paint paint = new Paint();

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = (int) ((PADDING + RADIUS) * 2);

        int measureWidth = resolveSize(size, widthMeasureSpec);
        int measureHeight = resolveSize(size, heightMeasureSpec);

        setMeasuredDimension(measureWidth, measureHeight);
    }





    public void setRadius(float radius) {
        this.RADIUS = radius;
        invalidate();
    }

    public float getRadius() {
        return this.RADIUS;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, paint);
    }
}
