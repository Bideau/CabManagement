package com.example.quentin.myapplication;

import android.graphics.Point;
import android.util.Log;

import com.example.quentin.myapplication.Network.AsyncWebSocket;

import org.json.JSONException;
import org.json.JSONObject;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.simple.*;
import org.json.simple.parser.*;
import com.example.quentin.myapplication.structures.*;


import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Quentin on 20/10/2015.
 */
public class Drawer extends Observable implements AsyncWebSocket {
    private String tagDrawer = "DRAWER";
    private boolean mapInit = false;
    private Area field;
    public Point cabPosition;

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
}
