package network.integration_tests;

import java.io.IOException;

import network.Message;
import network.client.NetworkClient;
import network.exceptions.ServerDownException;
import network.server.Coordinator;

public class ServerClient {
	public static final String SOME_MSG = "some random massage";
	public static final String SOME_DIFF_MSG = "another random massage";
	
	public static void main(String[] args) {
		try {
			Coordinator cor = new Coordinator(9999);
			NetworkClient c1 = new NetworkClient();
			NetworkClient c2 = new NetworkClient();
			NetworkClient c3 = new NetworkClient();
			c1.broadcast(new Message(SOME_MSG));
			Thread.sleep(1000);
			System.out.println(c2.read().peek().toString());
			System.out.println(c2.read().isEmpty());
			System.out.println(c3.read().peek().toString());
			System.out.println(c3.read().isEmpty());
			c1.broadcast(new Message(SOME_DIFF_MSG));
			Thread.sleep(1000);
			System.out.println(c2.read().peek().toString());
			cor.shutdown();
		} catch (IOException | ServerDownException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
