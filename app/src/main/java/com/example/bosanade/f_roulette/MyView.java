package com.example.bosanade.f_roulette;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by bosanade on 2018/04/21.
 */
public class MyView extends View {

    Paint paint1,paint2,paint3,paint4;
    private Paint mPaint = new Paint();


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint1 = new Paint();
        paint2 = new Paint();
        paint3 = new Paint();
        paint4 = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        RectF rectf1 = new RectF(0, 0, 720, 720);
        RectF rectf2 = new RectF(0, 0, 720, 720);
        RectF rectf3 = new RectF(0, 0, 720, 720);
        RectF rectf4 = new RectF(0, 0, 720, 720);

        paint1.setColor(Color.argb(255, 255, 236, 185));
        paint2.setColor(Color.argb(255, 172, 226, 180));
        paint3.setColor(Color.argb(255, 184, 178, 234));
        paint4.setColor(Color.argb(255, 171, 231, 255));

        rectf1.offset(0, 0);
        rectf1.offset(0, 0);
        rectf2.offset(0, 0);
        rectf3.offset(0, 0);
        rectf4.offset(0, 0);

        canvas.drawArc(rectf1, 0, 90, true, paint1);
        canvas.drawArc(rectf2, 90, 90, true, paint2);
        canvas.drawArc(rectf3, 180, 90, true, paint3);
        canvas.drawArc(rectf4, 270, 90, true, paint4);

        mPaint.setTextSize(50);

        canvas.drawText("すし", 180, 180, mPaint);
        canvas.drawText("カレー", 180, 540, mPaint);
        canvas.drawText("中華", 450, 180, mPaint);
        canvas.drawText("焼き肉", 450, 540, mPaint);
    }
}
