package com.example.quentin.testandroidwebsocketclient;

import android.os.AsyncTask;
import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Quentin on 19/10/2015.
 */
public class WebSocket extends AsyncTask<String, String, String> {

    private String tagWebSocket = "WebSocket";
    private WebSocketClient webSocketCli;
    private URI uri;
    public String messageJSONInAString; //Debug
    public String httpResult; // Debug



    @Override
    protected String doInBackground(String... urls) {

        this.connect(urls[0]);

        return null;
    }

    public void disconnect(){
        this.webSocketCli.close();
    }

    private void connect(String path){
        try {
            this.uri = new URI(path);

            this.webSocketCli = new WebSocketClient(this.uri, new Draft_17()) {

                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    Log.d(tagWebSocket,"Connected");
                }

                @Override
                public void onMessage(String message) {
                    Log.d(tagWebSocket,"onMessage");
                    JSONObject messageJSON = null;
                    try {
                        messageJSONInAString = message; //Debug
                        Log.d(tagWebSocket,"MESSAGE RECU: "+message);
                        messageJSON = new JSONObject(message);
                        // TODO What i doing with a message ?
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d(tagWebSocket,"onClose");
                }

                @Override
                public void onError(Exception ex) {
                    Log.d(tagWebSocket,"onError");
                    Log.e(tagWebSocket,ex.getMessage());
                }
            };
            webSocketCli.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e(tagWebSocket,e.getMessage().toString());
        }

    }
    public void send(JSONObject messageJSON){
        Log.e(tagWebSocket,"Send message JSON");
        webSocketCli.send(messageJSON.toString());
    }
    public void send(String messageString){
        Log.e(tagWebSocket,"Send message String");
        webSocketCli.send(messageString);
    }
}
