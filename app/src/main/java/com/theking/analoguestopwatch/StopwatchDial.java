package com.theking.analoguestopwatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.shapes.ArcShape;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.logging.LogRecord;

public class StopwatchDial extends View {
    int h,w,stop=0,isRunning=0;

    int sec=0,min=0,hr=0;
    int secAngle=0,minAngle=0;
    Paint paint =new Paint(),minPaint=new Paint();
    RectF rectF=new RectF(w/2,w/2,w/4+100,w/4+100);
    public StopwatchDial(Context context) {
        super(context);

    }

    public StopwatchDial(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StopwatchDial(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(50);
        h=MeasureSpec.getSize(heightMeasureSpec);
        w=MeasureSpec.getSize(widthMeasureSpec);
        minPaint.setColor(Color.RED);
        minPaint.setStyle(Paint.Style.STROKE);
        minPaint.setStrokeWidth(50);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(w/2-250,w/4,w/2+250,w/4+500,270,-(360-secAngle),false,paint);
        canvas.drawArc(w/2-150,w/4+200,w/2+150,w/4+500,270,-(360-minAngle),false,minPaint);

    }
    public void timer(){
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
               sec++;
               if(sec==60) {
                   sec=0;
                   min++;
                   if(min==60) {
                       min=0;
                       hr++;
                   }
               }
               secAngle=sec*6;
               minAngle=min*6;
               invalidate();
               if(isRunning==1) handler.postDelayed(this,1000);
            }
        });
    }
    public void start() {
        isRunning=1;
        timer();
    }
    public void stop() {
        isRunning=0;
    }
    public void reset() {
        sec=hr=min=0;
        secAngle=minAngle=0;
        invalidate();
    }
}
