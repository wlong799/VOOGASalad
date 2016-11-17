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
	
	public Coordinator(int port)
			throws IOException, InterruptedException {
		serverSocket = new ServerSocket(port);
		connectionPool = new ArrayList<>();
		messageQueue = new LinkedBlockingQueue<>();
		daemon = new Daemon(this);
        daemon.start();
        broadcast();
	}
	
	private void broadcast() {
		Thread worker = new Thread() {
			@Override
			public void run() {
				while(true) {
					try {
						Message msg = messageQueue.take();
						synchronized (connectionPool) {
							for (Connection conn : connectionPool) {
								conn.send(msg);
							}
						}
					} catch (InterruptedException e) {
						// TODO cx15
						e.printStackTrace();
					}
				}
			}
		};
		worker.start();
	}
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void addConnection(Connection conn) {
		synchronized (connectionPool) {
			connectionPool.add(conn);
		}
	}
	
	public BlockingQueue<Message> getMessageQueue() {
		return messageQueue;
	}

	public static void main(String[] args) {
		try {
			Coordinator cor = new Coordinator(9999);
			NetworkClient c1 = new NetworkClient();
			NetworkClient c2 = new NetworkClient();
			c1.send(new Message("client 1"));
		} catch (IOException | ServerDownException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
