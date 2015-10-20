package com.example.quentin.testandroidpainter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    public void myToast(String text){
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    public class MyView extends View implements View.OnTouchListener{
        public MyView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
        }

        List<Point> points = new ArrayList<Point>();
        Paint paint = new Paint();

        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub

            super.onDraw(canvas);
            int x = getWidth();
            int y = getHeight();
            int radius;
            radius = 100;
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            // Use Color.parseColor to define HTML colors
            paint.setColor(Color.parseColor("#CD5C5C"));
            canvas.drawCircle(x / 2, y / 2, radius, paint);
            for (Point point : points) {
                canvas.drawCircle(point.x, point.y, 5, paint);
                // Log.d(TAG, "Painting: "+point);
            }
        }

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            // if(event.getAction() != MotionEvent.ACTION_DOWN)
            // return super.onTouchEvent(event);

            Point point = new Point();
            point.x = event.getX();
            point.y = event.getY();
            points.add(point);
            myToast("Circle on :");
            //invalidate();
            return true;
        }
    }
}
class Point {
    float x, y;

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
