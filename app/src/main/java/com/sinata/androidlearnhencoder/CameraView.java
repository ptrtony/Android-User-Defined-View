package com.sinata.androidlearnhencoder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
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


public class CameraView extends View {
    private static final int IMAGE_WIDTH = (int) Utils.dp2px(200);
    private static final int IMAGE_PADDING = (int) Utils.dp2px(100);
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Camera camera = new Camera();
    private Bitmap image;

    private float topFlip = 0;
    private float bottomFlip = 45;
    private float flipRotation = 20;

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        image = Utils.getAvatar(getResources(), IMAGE_WIDTH);
        camera.setLocation(0, 0, Utils.getZForCamera(getResources()));
    }


    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate(IMAGE_PADDING + IMAGE_WIDTH / 2, IMAGE_PADDING + IMAGE_WIDTH / 2);
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(topFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-IMAGE_WIDTH, -IMAGE_WIDTH, IMAGE_WIDTH, 0);
        canvas.rotate(flipRotation);
        canvas.translate(-(IMAGE_PADDING + IMAGE_WIDTH / 2), -(IMAGE_PADDING + IMAGE_WIDTH / 2));
        canvas.drawBitmap(image, IMAGE_PADDING, IMAGE_PADDING, mPaint);
        canvas.restore();
        canvas.save();
        canvas.translate(IMAGE_PADDING + IMAGE_WIDTH / 2, IMAGE_PADDING + IMAGE_WIDTH / 2);
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(bottomFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-IMAGE_WIDTH, 0, IMAGE_WIDTH, IMAGE_WIDTH);
        canvas.rotate(flipRotation);
        canvas.translate(-(IMAGE_PADDING + IMAGE_WIDTH / 2), -(IMAGE_PADDING + IMAGE_WIDTH / 2));
        canvas.drawBitmap(image, IMAGE_PADDING, IMAGE_PADDING, mPaint);
        canvas.restore();
    }
}
