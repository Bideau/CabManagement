package com.example.quentin.myapplication.Network;

/**
 * Created by Quentin on 20/10/2015.
 */
public interface AsyncWebSocket {
    void onWebSocketMessage(String json);
    void onWebSocketDisconnect();
}
