package webSocket;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;

/**
 * Example of a simple Echo Client.
 */
public class Client {

	public static void main(String[] args) {
		String destUri = "ws://172.30.0.190:5000";
		if (args.length > 0) {
			destUri = args[0];
		}
		WebSocketClient client = new WebSocketClient();
		SocketIO socket = new SocketIO();
		
		@SuppressWarnings("unused") Session session = null;
		Future<Session> fut;
		 
		RemoteEndpoint remote = null;
		 
		
		
		try {
			client.start();
			URI echoUri = new URI(destUri);
			ClientUpgradeRequest request = new ClientUpgradeRequest();
			
			fut = client.connect(socket, echoUri, request);
			
			session = fut.get();
			
			remote = session.getRemote();
			
			// Blocking Send of a TEXT message to remote endpoint
			try{
			    remote.sendString("Hello World");
			}
			catch (IOException e){
			    e.printStackTrace(System.err);
			}
			
			System.out.printf("Connecting to : %s%n", echoUri);
			socket.awaitClose(10, TimeUnit.SECONDS);
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			try {
				client.stop();
				System.out.println("STOP Client");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
