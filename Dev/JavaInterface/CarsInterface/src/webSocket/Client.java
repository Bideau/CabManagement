package webSocket;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.StatusCode;

public class Client {

	private static SocketIO socket;
	
	// Main : The program starts here
	public static void main(String[] args) {
		
		// IP adress and port of the Python server
		String destUri = "ws://172.30.0.193:8000";
		
		// Take the informations of arguments if they are
		if (args.length > 0) {
			destUri = args[0];
		}
		
		// Creation of webSocket
		WebSocketClient client = new WebSocketClient();
		socket = new SocketIO();

		@SuppressWarnings("unused") Session session = null;
		try {
			client.start();
			URI echoUri = new URI(destUri);
			ClientUpgradeRequest request = new ClientUpgradeRequest();

			// Connexion between client and server
			client.connect(socket, echoUri, request);

			// Wait 2s
			Thread.sleep(2000);
			
			socket.sendString("Initialisation");
			
			while(socket.isSocketOpened()){
				// Wait 2s
				Thread.sleep(2000);
			}
			
			System.out.println("Sortie de la boucle d'ecoute");
			
			socket.awaitClose(2, TimeUnit.SECONDS);
			
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
		
		Client.getSocket().sendString(cabRequest);
	}

	public static SocketIO getSocket() {
		return socket;
	}

	public static void setSocket(SocketIO socket) {
		Client.socket = socket;
	}

	public String createJsonTrameForDestination(String area, String vertex){
		String cabRequest;

		cabRequest = "{\"area\": \"" + area +"\",\"vertex\": \"" + vertex + "\"}";

		System.out.println("cabRequest : " + cabRequest);
		return cabRequest;

	}
}
