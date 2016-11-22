package network.integration_tests;

import java.io.IOException;

import network.Message;
import network.client.NetworkClient;
import network.exceptions.ServerDownException;
import network.server.Coordinator;

/**
 * This integration test runs multiple clients and a server on the same
 * node/machine such that we could defined a strong serial order of events
 * which makes assertion easy.
 * 
 * <p>We avoid JUint test because as its name suggests it is a framework 
 * for unit testing, which is not what we want here. Further, on assertion
 * failure, JUnit terminates its thread only and does not properly clean
 * up the dangling sockets and connection. 
 * 
 * @author CharlesXu
 */
public class ServerClientTest {
	
	public static final String SOME_MSG = "some random massage";
	public static final String SOME_DIFF_MSG = "another random massage";
	
	public static final int PORT = 9999;
	public static final int DELAY_MILLIS = 1000;
	
	public static void main(String[] args) {
		try {
			Coordinator cor = new Coordinator(PORT);
			NetworkClient c1 = new NetworkClient();
			NetworkClient c2 = new NetworkClient();
			NetworkClient c3 = new NetworkClient();
			Thread.sleep(DELAY_MILLIS);
			c1.broadcast(new Message(SOME_MSG));
			Thread.sleep(DELAY_MILLIS);
			System.out.println(c2.read().peek().toString());
			System.out.println(c2.read().isEmpty());
			System.out.println(c3.read().peek().toString());
			System.out.println(c3.read().isEmpty());
			c1.broadcast(new Message(SOME_DIFF_MSG));
			Thread.sleep(DELAY_MILLIS);
			System.out.println(c2.read().peek().toString());
			cor.shutdown();
		} catch (IOException | ServerDownException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
