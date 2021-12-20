package com.sinata.androidlearnhencoder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
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
public class AvatarView extends View {
    private static final float WIDTH = Utils.dp2px(80);
    private static final float LENGTH = Utils.dp2px(10);
    private Bitmap avatar;

    private Rect mSRCRect = new Rect();
    private Rect mDSTRect = new Rect();
    private RectF mOverRect = new RectF();
    private RectF cut = new RectF();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Xfermode xfermode;
    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        avatar = Utils.getAvatar(getResources(),(int) WIDTH);
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mSRCRect.set(0, 0, (int) WIDTH, (int) WIDTH);
        mDSTRect.set((int) (getWidth() / 2 - WIDTH), (int) (getHeight() / 2 - WIDTH), (int) (getWidth() / 2 + WIDTH), (int) (getHeight() / 2 + WIDTH));
        mOverRect.set((int) (getWidth() / 2 - WIDTH - LENGTH), (int) (getHeight() / 2 - WIDTH - LENGTH), (int) (getWidth() / 2 + WIDTH + LENGTH), (int) (getHeight() / 2 + WIDTH + LENGTH));
        cut.set((int) (getWidth() / 2 - WIDTH), (int) (getHeight() / 2 - WIDTH), (int) (getWidth() / 2 + WIDTH), (int) (getHeight() / 2 + WIDTH));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(mOverRect,paint);
        int saved = canvas.saveLayer(cut,paint);
        canvas.drawOval(cut,paint);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(avatar, mSRCRect, mDSTRect, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saved);
    }


}
