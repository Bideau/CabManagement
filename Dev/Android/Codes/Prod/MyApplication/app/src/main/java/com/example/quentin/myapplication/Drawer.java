package com.example.quentin.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.quentin.myapplication.Network.AsyncWebSocket;
import com.example.quentin.myapplication.Network.ParserCabInfo;
import com.example.quentin.myapplication.Network.ParserJSON;
import com.example.quentin.myapplication.Network.WebSocket;
import com.example.quentin.myapplication.structures.map.Area;
import com.example.quentin.myapplication.structures.map.Bridge;
import com.example.quentin.myapplication.structures.map.Street;
import com.example.quentin.myapplication.structures.map.Vertex;
import com.example.quentin.myapplication.structures.taxi.CabInfo;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Quentin on 20/10/2015.
 */
public class Drawer extends View implements AsyncWebSocket, View.OnTouchListener  {
    public WebSocket wbSocket;
    private String tagDrawer = "DRAWER";
    private boolean mapInit = false;
    private Area field = null;
    public Point cabPosition = new Point(0,0);
    private int fieldHeight = 1000;
    private int fieldWidth = 500;
    private int objSize = 50;
    private boolean drawCab = false;
    Paint paint = new Paint();

    public Drawer(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        paint.setAntiAlias(true);
    }

    /**
     * On message reception
     */
    @Override
    public void onWebSocketMessage(String message) {
        Log.d(tagDrawer, "the drawer received a message "+ mapInit);

        // Get size of the screen
        this.fieldHeight = this.getHeight();
        this.fieldWidth = this.getWidth();

        // The first Json is the initialisation of the map (vertices, streets, bridges)
        if(isJSONValid(message) && mapInit == false){
            Log.d(tagDrawer, "Json message received (that's an initializer)");
            this.mapInit = true;
            loadMap(message);

        // After the initialisation, all the JSON are CabInfo to draw the cab
        }else if(isJSONValid(message) && mapInit == true){
            Log.d(tagDrawer, "Json message received (that's a cabInfo)");
            cabInfoReceived(message);

        // If it's not a JSON frame, do nothing
        }else if(!isJSONValid(message)){
            Log.d(tagDrawer, "That's not a JSON message");
        }else{
            Log.d(tagDrawer,"JSON not handled");
        }
        Log.d(tagDrawer,"Nothing");
    }

    /**
     * Call a redrawing
     */
    private void drawField(){
        requestLayout();
        postInvalidate(); // Call onDraw(..)
    }

    /**
     * Get a StringJson and initialize the field.
     * Asking for a refreshing too.
     *
     * @param map StringJson of an Area
     */
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

    /**
     * Parse a CabInfo and refresh cab's position
     * @param message CabInfo in a string
     */
    private void cabInfoReceived(String message){

        // Create a new object for parse the initialize String JSON
        ParserCabInfo parserTaxi = new ParserCabInfo();

        CabInfo cab = null;
        try {
            // Set the informations in the object cab
            cab = parserTaxi.parsingFrame(message);

        } catch (JsonParseException e1) {
            e1.printStackTrace();
        } catch (JsonMappingException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // Cab on my map ?
        if(this.field.getName().equals(cab.getLocNow().getArea())){
            this.drawCab = true;
            double vertexOrigineX=0,vertexOrigineY=0,vertexDestX=0,vertexDestY=0, x, y;
            int positionRatioBetweenTwoVertex;

            // Research the two vertices between the cab
            for(Vertex object : this.field.getMap().getVertices()){

                if(object.getName().equals(cab.getLocNow().getLocation())){
                    System.out.println("LocNow Found : " + object.getName());
                    vertexOrigineX = object.getX();
                    vertexOrigineY = object.getY();
                }
                if(object.getName().equals(cab.getLocNext().getLocation())){
                    System.out.println("LocNext Found : " + object.getName());
                    vertexDestX = object.getX();
                    vertexDestY = object.getY();
                }
            }

            // Get percentage
            positionRatioBetweenTwoVertex = cab.getLocNow().getProgression();
            Log.d(tagDrawer,"From: ("+vertexOrigineX+","+vertexOrigineY+") to ("+vertexDestX+","+vertexDestY+") Progression: "+positionRatioBetweenTwoVertex);

            // Calculate movement
            x = positionRatioBetweenTwoVertex * (vertexDestX - vertexOrigineX);
            y = positionRatioBetweenTwoVertex * (vertexDestY - vertexOrigineY);
            Log.d(tagDrawer,"Moved of X: "+x+" Y: "+y);

            // Calculate new position
            x = x + vertexOrigineX;
            y = y + vertexOrigineY;

            // Adapt it to the screen
            x = x * fieldWidth;
            y = y * fieldHeight;
            Log.d(tagDrawer,"New position X: "+x+" Y: "+y);

            // Refresh Cab's position
            this.cabPosition.x = (int)x;
            this.cabPosition.y = (int)y;
        }else{
            // If the cab isn't on my screen, don't draw it
            this.drawCab = false;
        }
    }

    /**
     * Called when the webSocket connection is broken
     */
    @Override
    public void onWebSocketDisconnect(){
        // Close all !
        //TODO Close All !
    }

    /**
     *  Verify if the message is a Json
     * @param message string to verify
     * @return true if the message is a JSON
     */
    public boolean isJSONValid(String message) {

        // If we can create a JSON with it that's ok
        try {
            new JSONObject(message);
        } catch (JSONException e) {
            // The JSON exploded
            return false;
        }
        return true;
    }

    /**
     * Called when user clicked on the screen
     * @param view The view where the user clicked
     * @param event the event used
     * @return
     */
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) { // On click released
            Log.d(tagDrawer,"onTOUCH");

            // Get cursor position
            int x = (int) event.getX();
            int y = (int) event.getY();

            // Calculate and send the position
            sendNewRequestPosition(x,y);

            // Re-Draw
            drawField();
        }
        return true;
    }

    /**
     * Calculate the nearest vertex and send it to the server
     * @param x x position
     * @param y y position
     */
    private void sendNewRequestPosition(int x, int y){
        Vertex position = new Vertex();
        double vertexX=0,vertexY=0,distance=0, lessDistance=9999999.9;

        // Research the nearest vertex
        for(int i = 0; i<this.field.getMap().getVertices().size(); i++){
            // Get vertex coordinates
            vertexX = this.field.getMap().getVertices().get(i).getX()*this.fieldWidth;
            vertexY = this.field.getMap().getVertices().get(i).getY()*this.fieldHeight;

            // Distance between cab and vertex points
            distance = Math.sqrt(Math.pow(vertexX - x,2) + Math.pow(vertexY-y,2));

            // The shortest distance ?
            if(distance<lessDistance){
                // Take it
                lessDistance = distance;
                position = this.field.getMap().getVertices().get(i);
            }
        }
        // Send it to the server
        sendMessage(this.field.getName(),position.getName());
    }

    /**
     * Send a request to the server in a JSON format
     * @param areaName Name of the area where the request is
     * @param vertexName Name of the vertex where the request is
     */
    private void sendMessage(String areaName, String vertexName){
        Log.d(tagDrawer,"Sending message...");
        // Send the message
        wbSocket.send("{\"area\": \"" + areaName +"\",\"vertex\": \"" + vertexName + "\"}");
    }

    /**
     * Called when a refresh is requested
     * @param canvas
     */
    @Override
    public void onDraw(Canvas canvas) {
        Log.d(tagDrawer, "DRAWING...");
        // If the field is initialized
        if(this.field != null){
            Log.d(tagDrawer,"Let's Draw...");

            // Get the size of the field
            this.fieldHeight = this.getHeight();
            this.fieldWidth = this.getWidth();

            // Draw all objects
            drawStreets(canvas);
            drawVertices(canvas);
            drawBridges(canvas);
            drawCab(canvas);
        }
    }

    /**
     * Draw all streets
     * @param canvas
     */
    private void drawStreets(Canvas canvas){
        Log.d(tagDrawer,"Draw streets...");

        // Black color
        paint.setColor(Color.BLACK);
        double baseX, baseY, destX, destY;

        // For each street
        for(Street objStreet : this.field.getMap().getStreets()){
            baseX = objStreet.getFirstVertice().getX()*fieldWidth;
            baseY = objStreet.getFirstVertice().getY()*fieldHeight;
            destX = objStreet.getSecondVertice().getX()*fieldWidth;
            destY = objStreet.getSecondVertice().getY()*fieldHeight;

            // Draw line
            canvas.drawLine((float)baseX,(float)baseY,(float)destX,(float)destY,this.paint);
        }
    }

    /**
     * Draw all vertices
     * @param canvas
     */
    private void drawVertices(Canvas canvas){
        Log.d(tagDrawer,"Draw vertices...");

        // Red color
        paint.setColor(Color.RED);
        double pointX, pointY, size = objSize;
        float topLeftX, topLeftY, bottomRightX, bottomRightY;

        // For each vertex
        for(Vertex objVertex : this.field.getMap().getVertices()){
            pointX = objVertex.getX()*fieldWidth;
            pointY = objVertex.getY()*fieldHeight;
            topLeftX = (float)(pointX - size);
            topLeftY = (float)(pointY - size);
            bottomRightX = (float)(pointX + size);
            bottomRightY = (float)(pointY + size);

            // Draw rect
            canvas.drawRect(topLeftX,topLeftY,bottomRightX,bottomRightY,this.paint);
        }
    }

    /**
     * Draw all bridges
     * @param canvas
     */
    private void drawBridges(Canvas canvas){
        Log.d(tagDrawer,"Draw bridges...");

        // Blue color
        paint.setColor(Color.BLUE);
        float circleX, circleY, size = objSize*2;

        // For each bridge
        for(Bridge objBridge : this.field.getMap().getBridges()){
            circleX = (float)(objBridge.getFromVertice().getX()*fieldWidth);
            circleY = (float)(objBridge.getFromVertice().getY()*fieldHeight);

            // Draw a circle
            canvas.drawCircle(circleX, circleY, size, this.paint);
        }
    }

    /**
     * Draw the cab
     * @param canvas
     */
    private void drawCab(Canvas canvas){
        // Green color
        paint.setColor(Color.GREEN);
        if(drawCab){
            // Draw a circle
            canvas.drawCircle(this.cabPosition.x, this.cabPosition.y,this.objSize/2,this.paint);
        }
    }
}