package webSocket;

import java.net.URI;
import java.util.concurrent.TimeUnit;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

/**
 * Example of a simple Echo Client.
 */
public class Client {

	public static void main(String[] args) {
		String destUri = "ws://172.30.0.193:8080";
		if (args.length > 0) {
			destUri = args[0];
		}
		WebSocketClient client = new WebSocketClient();
		SocketIO socket = new SocketIO();
		try {
			client.start();
			URI echoUri = new URI(destUri);
			ClientUpgradeRequest request = new ClientUpgradeRequest();
			client.connect(socket, echoUri, request);
			System.out.printf("Connecting to : %s%n", echoUri);
			socket.awaitClose(5, TimeUnit.SECONDS);
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
