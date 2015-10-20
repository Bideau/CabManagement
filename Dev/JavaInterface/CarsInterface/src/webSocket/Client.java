package webSocket;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.StatusCode;

/**
 * Example of a simple Echo Client.
 */
public class Client {

	public static void main(String[] args) {
		String destUri = "ws://172.30.0.193:8000";
		if (args.length > 0) {
			destUri = args[0];
		}
		// Creation of webSocket
		WebSocketClient client = new WebSocketClient();
		SocketIO socket = new SocketIO();

		@SuppressWarnings("unused") Session session = null;
		try {
			client.start();
			URI echoUri = new URI(destUri);
			ClientUpgradeRequest request = new ClientUpgradeRequest();

			client.connect(socket, echoUri, request);

			Thread.sleep(2000);
			
			socket.sendString("Initialisation");

			Thread.sleep(30000);
			
			socket.awaitClose(5, TimeUnit.SECONDS);
			
			socket.getSession().close(StatusCode.NORMAL, "I'm done");

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

	public void sendVertex(String areaDestination, String vertexDestination) {
		// TODO Auto-generated method stub
		String cabRequest;

		cabRequest = createJsonTrameForDestination(areaDestination, vertexDestination);

	}

	public String createJsonTrameForDestination(String area, String vertex){
		String cabRequest;

		cabRequest = "{\"area\": \"" + area +"\",\"vertex\": \"" + vertex + "\"}";

		System.out.println("cabRequest : " + cabRequest);
		return cabRequest;

	}
}
