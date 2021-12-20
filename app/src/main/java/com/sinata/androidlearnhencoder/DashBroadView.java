package com.sinata.androidlearnhencoder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
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
public class DashBroadView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final float RADIUS = Utils.dp2px(150);
    private static final int ANGLE = 120;
    private static final float LENGTH = Utils.dp2px(80);
    Path dash = new Path();
    Path path = new Path();
    RectF rectF;
    PathMeasure pathMeasure = new PathMeasure();
    PathDashPathEffect dashPathEffect;
    public DashBroadView(Context context) {
        super(context);
        init(context);
    }

    public DashBroadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public DashBroadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        dash.addRect(0,0,Utils.dp2px(2),Utils.dp2px(10), Path.Direction.CCW);
        paint.setStrokeWidth(Utils.dp2px(3));
        paint.setStyle(Paint.Style.STROKE);
    }


    //绘制的图形会先测量，测量后的结果和实际显示的不一样会执行onSizeChanged的方法
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //sin入参是弧度不是角度
        //Math.sin()
        //
        rectF = new RectF();
        rectF.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
        path.addArc(rectF,90 + ANGLE / 2, 360 - ANGLE);
        pathMeasure.setPath(path,false);
        dashPathEffect = new PathDashPathEffect(dash,(pathMeasure.getLength() - Utils.dp2px(2))/20,0, PathDashPathEffect.Style.ROTATE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
        paint.setPathEffect(dashPathEffect);
        canvas.drawArc(rectF,90 + ANGLE / 2, 360 - ANGLE,false,paint);
        paint.setPathEffect(null);
        canvas.drawLine(getWidth()/2,getHeight()/2,getWidth()/2 + (float)Math.cos(Math.toRadians(getAngleMark(5))) * LENGTH,getHeight() /2 + (float)Math.sin(Math.toRadians(getAngleMark(5))) * LENGTH,paint);
    }


    private float getAngleMark(int mark){
        return 90 + ANGLE /2 + (360 - ANGLE)/20 * mark;
    }

}
