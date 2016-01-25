package com.induce.vaios.inducedev;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.davemorrissey.labs.subscaleview.ImageSource;


/**
 * Created by Vaios on 25/01/2016.
 */
public class MapDraw extends Activity implements View.OnTouchListener {
    private RelativeLayout rl_Main;

     BlueDotView mImageView;
     static final float dotRadius = 1.0f;
    PointF point = new PointF();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapdraw);
        //rl_Main = (RelativeLayout) findViewById(R.id.rl_main);
        //rl_Main.addView(new MyView(this));
        mImageView = (BlueDotView) findViewById(R.id.bluedotview);
        point.x = 900f;
        point.y = 600f;
        mImageView.setDotCenter(point);
        mImageView.setRadius(15.0f);
        mImageView.postInvalidate();

        //mImageView.setRadius(2.0f);
        mImageView.setImage(ImageSource.resource(R.drawable.floor));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                point.x = event.getX();
                point.y = event.getY();

        }

        return true;
    }


    class MyView extends View {


        Paint paint = new Paint();
        Point point = new Point();

        public MyView(Context context) {
            super(context);
            paint.setColor(Color.RED);
            paint.setStrokeWidth(15);
            paint.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.floor);

            int h = 500; // Height in pixels
            int w = 500; // Width in pixels
            Bitmap scaled = Bitmap.createScaledBitmap(b, h, w, true);


            canvas.drawBitmap(scaled, 0, 0, paint);
            canvas.drawCircle(point.x, point.y, 100, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    point.x = event.getX();
                    point.y = event.getY();

            }
            invalidate();
            return true;

        }

    }

    class Point {
        float x, y;
    }

}
