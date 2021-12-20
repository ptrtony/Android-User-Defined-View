package com.sinata.androidlearnhencoder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2021
 * Company:成都博智维讯信息技术股份有限公司
 *
 * @author jingqiang.cheng
 * @date 2021/11/30
 */
public class TestView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final float RADIUS = Utils.dp2px(100);
    Path path = new Path();
    PathMeasure pathMeasure = new PathMeasure();
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //绘制的图形会先测量，测量后的结果和实际显示的不一样会执行onSizeChanged的方法
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

//        path.addCircle(getWidth() / 2, getHeight() / 2, RADIUS, Path.Direction.CCW);
//        path.addRect(getWidth() / 2 - RADIUS, getHeight() / 2, getWidth() / 2 + RADIUS,getHeight()/2+RADIUS*2, Path.Direction.CCW );
////        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
//        pathMeasure.setPath(path,false);
//        pathMeasure.getLength();
//        pathMeasure.getPosTan();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }
}
