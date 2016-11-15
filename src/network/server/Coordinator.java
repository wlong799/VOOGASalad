package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import network.Connection;
import network.Message;
import network.client.NetworkClient;
import network.exceptions.ServerDownException;

public class Coordinator {
	
	private ServerSocket serverSocket;
	private List<Connection> connectionPool;
	private BlockingQueue<Message> messageQueue;
	private Daemon daemon;
	
	public Coordinator(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		connectionPool = new ArrayList<>();
		messageQueue = new LinkedBlockingQueue<>();
		daemon = new Daemon(this);
        daemon.start();
	}
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void addConnection(Connection conn) {
		connectionPool.add(conn);
	}
	
	public BlockingQueue<Message> getMessageQueue() {
		return messageQueue;
	}

	public static void main(String[] args) {
		try {
			Coordinator cor = new Coordinator(9999);
			NetworkClient c1 = new NetworkClient();
			NetworkClient c2 = new NetworkClient();
		} catch (IOException | ServerDownException e) {
			e.printStackTrace();
		}
	}
}
