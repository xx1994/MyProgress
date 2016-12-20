package com.xx.myviewdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/12/14.
 * 自定义圆形进度条
 */
public class CircleProgressView extends View {
    /**
     * 圆的半径,圆心X，Y轴位置
     */
    private int circleRadius = 100;
    private float pointX, pointY;
    //灰色圆形画笔
    private Paint mPaint;
    //进度圆形画笔
    private Paint mSchedulePaint;
    //文字画笔
    private Paint mTextPaint;
    //文字大小
    private float textSize;
    //设置当前进度
    private float schedule;

    public CircleProgressView(Context context) {
        super(context);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.rgb(0xe9, 0xe9, 0xe9));
        // 设置画笔风格为描边
        mPaint.setStyle(Paint.Style.STROKE);
        // 设置描边宽度
        mPaint.setStrokeWidth(10);

        mSchedulePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSchedulePaint.setColor(Color.rgb(0xf8, 0x60, 0x30));
        // 设置画笔风格为描边
        mSchedulePaint.setStyle(Paint.Style.STROKE);
        // 设置描边宽度
        mSchedulePaint.setStrokeWidth(8);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.rgb(0xf8, 0x60, 0x30));
        textSize = 100 * 0.6f;
        mTextPaint.setTextSize(textSize);
        // 设置画笔风格为描边
        mTextPaint.setStyle(Paint.Style.FILL);
        // 设置描边宽度
        mTextPaint.setStrokeWidth(2);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //圆心位置
        pointX = w / 2;
        pointY = h / 2;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        //绘制原始背景圆圈
        canvas.drawCircle(pointX, pointY, circleRadius, mPaint);
        //测量字的宽度
        float textWidth = mTextPaint.measureText((int) schedule + "%");
        //绘制进度文字
        canvas.drawText((int) schedule + "%", pointX - textWidth / 2, pointY + textSize / 2, mTextPaint);
        //绘制进度的圆圈
        canvas.drawArc(pointX - 100, pointY - 100, pointX + 100, pointY + 100, -90, (schedule / 100) * 360, false, mSchedulePaint);
    }

    //设置进度
    public void setSchedule(float schedule) {
        this.schedule = schedule;
        postInvalidate();
    }
}
