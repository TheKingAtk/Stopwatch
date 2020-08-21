package com.theking.analoguestopwatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class StopwatchDial extends View {
    float h,w,isRunning=0;

    int sec=0,min=0,hr=0;
    int secAngle=0,minAngle=0;
    Paint paint =new Paint(),minPaint=new Paint(),textPaint=new Paint();
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
        textPaint.setTextSize(100);
        textPaint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(w/2-250,w/4,w/2+250,w/4+500,270,-(360-secAngle),false,paint);
        canvas.drawArc(w/2-150,w/4+200,w/2+150,w/4+500,270,-(360-minAngle),false,minPaint);
        //canvas.drawText(String.valueOf(sec)+"s",w/2,w/4+100,textPaint);
        canvas.drawText(sec+"s",w/2-(float)(String.valueOf(sec).length()+1)*50/2,w/4+150,textPaint);
        canvas.drawText(String.valueOf(min),w/2-(float)(String.valueOf(min).length())*50/2,w/4+340,textPaint);
        canvas.drawText("min",w/2-(float)(3)*50/2,w/4+420,textPaint);
        canvas.drawText(hr+"hr",w/2-(float)(String.valueOf(hr).length()+2)*50/2,w/4+640,textPaint);
    }
    public void timer(){
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

               if(isRunning==1){

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
                   handler.postDelayed(this,1000);
               }
            }
        });
    }
    public void start() {
        if(isRunning==0) {
            isRunning=1;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    timer();
                }
            },1000);
        }
    }
    public void stop() {
        isRunning=0;
        invalidate();
    }
    public void reset() {
        sec=hr=min=0;
        secAngle=minAngle=0;
        invalidate();
    }
}
