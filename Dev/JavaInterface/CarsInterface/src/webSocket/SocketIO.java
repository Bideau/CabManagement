package webSocket;

import structures.map.*;
import structures.taxi.CabInfo;
import hud.InterfaceMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import json.ParserCabInfo;
import json.ParserJSON;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.json.simple.*;
import org.json.simple.parser.*;

/**
 * Basic Echo Client Socket
 */
@WebSocket(maxTextMessageSize = 64 * 1024)
public class SocketIO {

	private ArrayList<Area> listArea;
	private InterfaceMap interface1;

	private boolean initialize = false;	
	private boolean socketOpened = true;
	private final CountDownLatch closeLatch;

	private Session session;

	public SocketIO() {
		this.closeLatch = new CountDownLatch(1);
		this.listArea = new ArrayList<Area>();
	}

	public boolean awaitClose(int duration, TimeUnit unit) throws InterruptedException {
		return this.closeLatch.await(duration, unit);
	}

	@OnWebSocketClose
	public void onClose(int statusCode, String reason) {
		System.out.printf("Connection closed: %d - %s%n", statusCode, reason);
		this.session = null;
		this.closeLatch.countDown();
	}

	@OnWebSocketConnect
	public void onConnect(Session session) {
		System.out.printf("Got connect: %s%n", session);
		this.session = session;
		try {
			sendString("I'm connected !");
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	// The onMessage(String msg) receives the responses from the remote server WebSocket
	// and outputs them to the console.
	@OnWebSocketMessage
	public void onMessage(String msg) {
		System.out.printf("Receive msg: %s%n", msg);

		// The first Json is the initialisation of the map (Vertex, streets, bridges)
		if(isJSONValid(msg) && initialize == false){
			System.out.println("msg is Json + Initialize\n");
			ReceiveInitialize(msg);
			initialize = true;
			// After the initialisation, all the JSON are CabInfo to draw the cab
		}else if(isJSONValid(msg) && initialize == true){
			System.out.println("msg is Json + CabInfo\n");
			ReceiveCabInfo(msg);
			// If it's not a JSON frame is left untreated
		}else if(!isJSONValid(msg)){
			System.out.println("msg is not Json\n");
		}
	}

	public void sendString(String msg){
		try {
			Future<Void> fut;
			if(session != null){
				fut = session.getRemote().sendStringByFuture(msg);
				fut.get(4, TimeUnit.SECONDS);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	// Draw Initialize map
	public void ReceiveInitialize(String msg){
		System.out.println("Json\n");

		// Create a new object for parse the initialize String JSON
		ParserJSON parserJson = new ParserJSON();
		parserJson.parsingFrame(msg);

		// Get the list of all areas
		this.listArea = parserJson.getListArea();

		// West zone is the first element
		interface1 = new InterfaceMap(this.listArea.get(0),this);
		interface1.DrawInterface();
	}

	public void ReceiveCabInfo(String msg){

		// Create a new object for parse the initialize String JSON
		ParserCabInfo parserTaxi = new ParserCabInfo();
		
		CabInfo cab = null;

		try {

			// Set the informations in the object cab
			cab = parserTaxi.parsingFrame(msg);

		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Name : " + cab.getLocNow().getLocation());

		// Draw the cab's position
		interface1.getMap().getCabPainter().setCabInfo(cab);
		
		// Repaint for the cab position
		interface1.getMap().repaint();
		
		System.out.println("REPAINT\n");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isJSONValid(String msg) {
		try {
			@SuppressWarnings("unused")
			JSONObject json = (JSONObject) new JSONParser().parse(msg);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//System.out.println("NOT JSON");
			return false;
		}
		return true;
	}

	public boolean isSocketOpened() {
		return socketOpened;
	}

	public void setSocketOpened(boolean socketOpened) {
		this.socketOpened = socketOpened;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
