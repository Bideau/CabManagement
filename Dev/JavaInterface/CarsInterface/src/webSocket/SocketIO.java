package webSocket;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

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

	private boolean initialize = false;
	
	private final CountDownLatch closeLatch;

	private Session session;

	public SocketIO() {
		this.closeLatch = new CountDownLatch(1);
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
		System.out.printf("Got msg: %s%n", msg);
		
		// The first Json is the initialisation of the map (Vertex, streets, bridges)
		if(isJSONValid(msg) && initialize == false){
			ReceiveInitialize();
			initialize = true;
		// After the initialisation, all the JSON are CabInfo to draw the cab
		}else if(isJSONValid(msg) && initialize == true){
			ReceiveCabInfo();
		// If it's not a JSON frame is left untreated
		}else if(!isJSONValid(msg)){
			System.out.println("msg is not Json\n");
		}
	}

	public void sendString(String msg){
		try {
			Future<Void> fut;
			fut = session.getRemote().sendStringByFuture(msg);
			fut.get(4, TimeUnit.SECONDS);

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void ReceiveInitialize(){
		System.out.println("Json\n");
	}
	
	public void ReceiveCabInfo(){
		System.out.println("CabInfo\n");
	}

	public boolean isJSONValid(String msg) {
		try {
			JSONObject json = (JSONObject) new JSONParser().parse(msg);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		return true;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
