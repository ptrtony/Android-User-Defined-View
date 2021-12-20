package com.sinata.androidlearnhencoder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.text.MeasuredText;
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
 * @date 2021/12/2
 */
public class MyTextView extends View {


    private static final float WIDTH = Utils.dp2px(150);
    private static final float PADDING = Utils.dp2px(100);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
    String text = "中国共产党第十九届中央委员会第六次全体会议于11月8日至11日在北京召开，重点研究全面总结了党的百年奋斗的重大成就和历史经验问题。" +
            "一百年来，中国共产党忠实践行初心使命，团结带领全党全军全国各族人民克服重重挑战，在中国这片广袤的大地上绘就了人类发展史上波澜壮阔的壮美画卷。" +
            "不忘初心，方得始终。中国共产党立志于中华民族千秋伟业，百年恰是风华正茂。过去一百年，党向人民、向历史交出了一份优异的答卷。现在，党团结带领中国人民又踏上了实现第二个百年奋斗目标新的赶考之路。" +
            "央视网《联播+》栏目特别策划，从党的十九届六中全会公报看中国共产党的“百年答卷”，与您一同感悟一代又一代共产党人的奋斗历程，一同以史为鉴、开创未来。";
    private Bitmap bitmap;
    private float[] measureWidths = new float[1];
    MeasuredText measuredText;

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.getFontMetrics(fontMetrics);
        bitmap = Utils.getAvatar(getResources(), (int) WIDTH);
        paint.setTextSize(Utils.dp2px(18));
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, getWidth() - WIDTH, PADDING, paint);
        paint.getFontMetrics(fontMetrics);
        float offsetX = 0;
        int length = text.length();
        float offsetY = paint.getFontSpacing();
        for (int start = 0, count; start < length; start += count, offsetY += paint.getFontSpacing()) {
            float textTop = fontMetrics.ascent + offsetY;
            float textBottom = fontMetrics.descent + offsetY;
            float useWidth;
            if (textTop > PADDING && textTop < PADDING + WIDTH || textBottom > PADDING && textBottom < PADDING + WIDTH) {
                useWidth = getWidth() - WIDTH;
            } else {
                useWidth = getWidth();
            }
            count = paint.breakText(text, start, length, false, useWidth, measureWidths);
            canvas.drawText(text, start, start + count, offsetX, offsetY, paint);
        }

    }
}
