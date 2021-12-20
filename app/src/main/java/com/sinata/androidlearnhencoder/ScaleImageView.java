package com.sinata.androidlearnhencoder;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2021
 * Company:成都博智维讯信息技术股份有限公司
 *
 * @author jingqiang.cheng
 * @date 2021/12/10
 */
public class ScaleImageView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, Runnable {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float bitmapWidth = Utils.dp2px(120);
    private float OVER_SCALE_FACTOR = 1.5f;
    private float originalOffsetX, originalOffsetY;
    private Bitmap bitmap;
    private float smallScale, bigScale;
    private boolean big;
    private float scaleFraction;
    private ObjectAnimator scaleAnimator;
    GestureDetectorCompat detector;
    private float offsetX;
    private float offsetY;
    private Scroller scroller;

    public ScaleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = Utils.getAvatar(getResources(), (int) bitmapWidth);
        detector = new GestureDetectorCompat(context, this);
        scroller = new Scroller(context);
    }

    public float getScaleFraction() {
        return scaleFraction;
    }

    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
    }


    private ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "scaleFraction", 0f, 1f);
        }
        scaleAnimator.setDuration(500);
        return scaleAnimator;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originalOffsetX = (getWidth() - bitmap.getWidth()) / 2f;
        originalOffsetY = (getHeight() - bitmap.getHeight()) / 2f;
        if (bitmap.getWidth() / bitmap.getHeight() > getWidth() / getHeight()) {
            smallScale = getWidth() / (bitmap.getWidth() * 1.0f);
            bigScale = getHeight() / (bitmap.getHeight() * 1.0f) * OVER_SCALE_FACTOR;
        } else {
            smallScale = getHeight() / (bitmap.getHeight() * 1.0f);
            bigScale = getWidth() / (bitmap.getWidth() * 1.0f) * OVER_SCALE_FACTOR;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);
        float scale = smallScale + (bigScale - smallScale) * scaleFraction;
        canvas.scale(scale, scale, getWidth() / 2f, getHeight() / 2f);
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        //ACTION_UP  true事件被消费
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        //预按下结束  总是会发生的

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        return false;
    }

    /**
     * e1 down按下事件
     * e2 当前事件
     * distanceX 上一个点到当前的这个点的X偏移
     * distanceY 上一个点到当前的这个点的Y偏移
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //onMove()当移动的时候这个方法会被调用
        if (big) {
            offsetX -= distanceX;
            offsetY -= distanceY;
            diffOffset();
            invalidate();
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        //当长按的时候会被触发

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //快速滑动
        if (big) {
            scroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY, -(int) (bitmap.getWidth() * bigScale - getWidth() / 2f), (int) (bitmap.getWidth() * bigScale - getWidth() / 2f),
                    -(int) (bitmap.getHeight() * bigScale - getHeight() / 2f), (int) (bitmap.getHeight() * bigScale - getHeight() / 2f));
            postOnAnimation(this);
        }
        return false;
    }

    @Override
    public void run() {
        if (scroller.computeScrollOffset()) {
            offsetX = scroller.getCurrX();
            offsetY = scroller.getCurrY();
            diffOffset();
            invalidate();
            postOnAnimation(this);
        }
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {

        big = !big;
        if (big) {
            offsetX = (e.getX() - getWidth() / 2f) * (1 - bigScale / smallScale);
            offsetY = (e.getY() - getHeight()) / 2f * (1 - bigScale / smallScale);
            diffOffset();
            getScaleAnimator().start();
        } else {
            getScaleAnimator().reverse();
        }
        invalidate();
        return false;
    }

    private void diffOffset() {
        offsetX = Math.min((bitmap.getWidth() * bigScale - getWidth()) / 2f, offsetX);
        offsetX = Math.max(-(bitmap.getWidth() * bigScale - getWidth()) / 2f, offsetX);
        offsetY = Math.min((bitmap.getHeight() * bigScale - getHeight()) / 2f, offsetY);
        offsetY = Math.max(-(bitmap.getHeight() * bigScale - getHeight()) / 2f, offsetY);
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}
