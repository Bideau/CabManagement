package com.example.quentin.testandroidpaintereventlistener;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    DrawView drawView;

    public void myToast(String text){
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set full screen view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        drawView = new DrawView(this);
        setContentView(drawView);
        drawView.requestFocus();
    }
}
class DrawView extends View implements View.OnTouchListener {

    List<Point> points = new ArrayList<Point>();
    Paint paint = new Paint();

    public DrawView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);

        this.setOnTouchListener(this);

        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
    }
    @Override
    public void onDraw(Canvas canvas) {
        for (Point point : points) {
            drawX(canvas,point.x,point.y,5, paint);
        }
    }
    private void drawX(Canvas canvas, float x, float y, int size, Paint aPaint){
        canvas.drawLine(x-size,y-size ,x+size ,y+size ,aPaint);
        canvas.drawLine(x+size ,y-size ,x-size ,y+size ,aPaint);
    }
    public boolean onTouch(View view, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            // return super.onTouchEvent(event);
            Point point = new Point();
            point.x = event.getX();
            point.y = event.getY();
            points.add(point);
            invalidate();
        }
        return true;
    }
}
class Point {
    float x, y;

    @Override
    public String toString() {
        return x + ", " + y;
    }
}




