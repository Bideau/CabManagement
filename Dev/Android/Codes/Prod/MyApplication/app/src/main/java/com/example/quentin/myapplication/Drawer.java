package com.example.quentin.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.quentin.myapplication.Network.AsyncWebSocket;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.quentin.myapplication.Network.ParserJSON;
import com.example.quentin.myapplication.structures.*;


import java.util.ArrayList;

/**
 * Created by Quentin on 20/10/2015.
 */
public class Drawer extends View implements AsyncWebSocket, View.OnTouchListener  {
    private String tagDrawer = "DRAWER";
    private boolean mapInit = false;
    private Area field;
    public Point cabPosition = new Point();
    private int fieldHeight;
    private int fieldWidth;
    Paint paint = new Paint();

    public Drawer(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        paint.setAntiAlias(true);

        this.fieldHeight = this.getHeight();
        this.fieldWidth = this.getWidth();

    }

    @Override
    public void onWebSocketMessage(String message) {
        Log.d(tagDrawer, "the drawer received a message");

        // The first Json is the initialisation of the map (Vertex, streets, bridges)
        if(isJSONValid(message) && mapInit == false){
            Log.d(tagDrawer, "Json message received (that's an initializer)");
            loadMap(message);
            mapInit = true;

            // After the initialisation, all the JSON are CabInfo to draw the cab
        }else if(isJSONValid(message) && mapInit == true){
            Log.d(tagDrawer, "Json message received (that's a cabInfo)");
            cabInfoReceived(message);

            // If it's not a JSON frame is left untreated
        }else if(!isJSONValid(message)){
            Log.d(tagDrawer, "That's not a JSON message");
        }else{
            Log.d(tagDrawer,"JSON non pris en charge");
        }
        Log.d(tagDrawer,"Nothing");
    }

    private void drawField(){
        invalidate();
    }

    public void loadMap(String map){

        ArrayList<Area> listArea;

        // Create a new object for parse the initialize String JSON
        ParserJSON parserJson = new ParserJSON();
        parserJson.parsingFrame(map);

        // Get all areas
        listArea = parserJson.getListArea();

        // TODO choosing the area
        int indexArea = 0; //DEBUG

        // Take an area for me
        this.field = listArea.get(indexArea);

        // Draw interface
        this.drawField();
    }

    private void cabInfoReceived(String message){

    }

    @Override
    public void onWebSocketDisconnect(){
        // Close all !
    }

    public boolean isJSONValid(String msg) {

        try {
            new JSONObject(msg);
        } catch (JSONException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {

            // TODO Send client position
            //cabPosition.x =  (int) event.getX();
            //cabPosition.y =  (int) event.getY();

            // Re-Draw
            //invalidate();
        }
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        drawStreets(canvas);
        drawVertices(canvas);
        drawBridges(canvas);
    }

    private void drawStreets(Canvas canvas){
        double baseX, baseY, destX, destY;
        for(Street objStreet : this.field.getMap().getStreets()){
            baseX = objStreet.getFirstVertice().getX();
            baseY = objStreet.getFirstVertice().getY();
            destX = objStreet.getSecondVertice().getX();
            destY = objStreet.getSecondVertice().getX();
            canvas.drawLine((float)baseX*fieldWidth,(float)baseY*fieldHeight,(float)destX*fieldWidth,(float)destY*fieldHeight,this.paint);
        }
    }

    private void drawVertices(Canvas canvas){
        double pointX, pointY, size = 5;
        float topLeftX, topLeftY, bottomRightX, bottomRightY;
        for(Vertex objVertex : this.field.getMap().getVertices()){
            pointX = objVertex.getX()*fieldWidth;
            pointY = objVertex.getY()*fieldHeight;


            topLeftX = (float)(pointX - size);
            topLeftY = (float)(pointY - size);
            bottomRightX = (float)(pointX + size);
            bottomRightY = (float)(pointY + size);
            canvas.drawRect(topLeftX,topLeftY,bottomRightX,bottomRightY,this.paint);
        }
    }

    private void drawBridges(Canvas canvas){
        float circleX, circleY, size = 5;
        for(Bridge objBridge : this.field.getMap().getBridges()){
            circleX = (float)(objBridge.getFromVertice().getX()*fieldWidth);
            circleY = (float)(objBridge.getFromVertice().getY()*fieldHeight);
            canvas.drawCircle(circleX, circleY, size, this.paint);
        }
    }

}
