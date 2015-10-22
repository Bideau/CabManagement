package com.example.quentin.myapplication.Network;

import android.os.AsyncTask;
import android.util.Log;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;
import java.net.URI;
import java.net.URISyntaxException;

public class WebSocket extends AsyncTask<String, String, String> {

    private String tagWebSocket = "WebSocket";
    public AsyncWebSocket delegate = null;
    private WebSocketClient webSocketCli;
    private URI uri;

    /**
     * Background tasking here
     * @param urls url to connect
     * @return
     */
    @Override
    protected String doInBackground(String... urls) {
        this.connect(urls[0]);
        return null;
    }

    /**
     * To close the connection
     */
    public void disconnect(){
        this.webSocketCli.close();
    }

    /**
     * Connect to the server
     * @param path URI of the server
     */
    private void connect(String path){
        try {
            this.uri = new URI(path);

            this.webSocketCli = new WebSocketClient(this.uri, new Draft_17()) {

                /**
                 * Callback when the connection is opened
                 * @param handshakedata
                 */
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    Log.d(tagWebSocket,"Connected");
                }

                /**
                 * Callback when a message is received
                 * @param message
                 */
                @Override
                public void onMessage(String message) {

                    Log.d(tagWebSocket,"onMessage: "+message);
                    // Give it to the drawer
                     delegate.onWebSocketMessage(message);
                }

                /**
                 * Callback when the connection is closed
                 * @param code
                 * @param reason
                 * @param remote
                 */
                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d(tagWebSocket,"onClose");
                }

                /**
                 * Callback when an error is detected
                 * @param ex
                 */
                @Override
                public void onError(Exception ex) {
                    Log.d(tagWebSocket,"onError");
                    Log.e(tagWebSocket,ex.getMessage());
                }
            };
            webSocketCli.connect(); // Let's connect

        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e(tagWebSocket,e.getMessage().toString());
        }

    }

    /**
     * Try to send a JSONObject
     * @param messageJSON
     */
    public void send(JSONObject messageJSON){
        Log.e(tagWebSocket,"Send message JSON");
        webSocketCli.send(messageJSON.toString());
    }

    /**
     * Try to send a string
     * @param messageString
     */
    public void send(String messageString){
        Log.e(tagWebSocket,"Send message String");
        webSocketCli.send(messageString);
    }
}