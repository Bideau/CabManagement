package com.example.quentin.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.quentin.myapplication.Network.WebSocket;

public class MainActivity extends Activity{

    private WebSocket webSocket;
    private Drawer drawer;
    //private String url = "ws://192.168.3.65:8000"; //Rasberry Pi URI
    private String url = "ws://172.30.0.193:8000"; // Test Server URI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAll();
    }

    private void initAll() {
        // Init drawer
        drawer = new Drawer(this);
        setContentView(drawer);
        drawer.requestLayout();
        drawer.requestFocus();

        // Init communication
        webSocket = new WebSocket();
        webSocket.delegate = drawer;
        webSocket.execute(url);

        // Wait a little until the connection is complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // For getting all areas
        webSocket.send("Initialisation");
        drawer.wbSocket = webSocket;
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