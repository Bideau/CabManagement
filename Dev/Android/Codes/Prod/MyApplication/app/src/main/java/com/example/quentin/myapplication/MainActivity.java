package com.example.quentin.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.quentin.myapplication.Network.WebSocket;

public class MainActivity extends Activity{

    private WebSocket webSocket;
    private Drawer drawer;
    private String url = "ws://172.30.0.193:8000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAll();
    }

    private void initAll() {
        // Init drawer
        drawer = new Drawer();

        // Init communication
        webSocket = new WebSocket();
        webSocket.delegate = drawer;
        webSocket.execute(url);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // For getting all areas
        webSocket.send("Initialisation");

        /*DEBUG

        String JsonS = "{ \"areas\": [ { \"name\": \"Quartier Nord\", \"map\": { \"weight\": {\"w\": 1, \"h\": 1}, \"vertices\": [ {\"name\": \"m\", \"x\": 0.5, \"y\": 0.5}, {\"name\": \"b\", \"x\": 0.5, \"y\": 1} ], \"streets\": [ {\"name\": \"mb\", \"path\": [\"m\", \"b\"], \"oneway\": false} ], \"bridges\": [ { \"from\": \"b\", \"to\": { \"area\": \"Quartier Sud\", \"vertex\": \"h\"}, \"weight\": 2 } ] } }, { \"name\": \"Quartier Sud\", \"map\": { \"weight\": {\"w\": 1, \"h\": 1}, \"vertices\": [ {\"name\": \"a\", \"x\": 1, \"y\": 1}, {\"name\": \"m\", \"x\": 0, \"y\": 1}, {\"name\": \"h\", \"x\": 0.5, \"y\": 0} ], \"streets\": [ {\"name\": \"ah\", \"path\": [\"a\", \"h\"], \"oneway\": false}, {\"name\": \"mh\", \"path\": [\"m\", \"h\"], \"oneway\": false} ], \"bridges\": [ { \"from\": \"h\", \"to\": { \"area\": \"Quartier Nord\", \"vertex\": \"b\"}, \"weight\": 2 } ] } } ] }";
        try {
            JSONObject jo = new JSONObject(JsonS);
            drawer.loadMap(jo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
