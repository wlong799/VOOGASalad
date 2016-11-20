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

/**
 * The central coordination service. Its main purpose is to observe
 * and define a global total order of messages and broadcast them 
 * to all participants/subscribers. 
 * @author CharlesXu
 */
public class Coordinator {
	
	private ServerSocket serverSocket;
	private List<Connection> connectionPool;
	private BlockingQueue<Message> messageQueue;
	private boolean hasStopped;
	
	public Coordinator(int port)
			throws IOException, InterruptedException {
		serverSocket = new ServerSocket(port);
		connectionPool = new ArrayList<>();
		messageQueue = new LinkedBlockingQueue<>();
		hasStopped = false;
        new Daemon(this).start();
        new Postman(connectionPool, messageQueue, this).start();
	}
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void addConnection(Connection conn) {
		synchronized (connectionPool) {
			connectionPool.add(conn);
		}
	}
	
	public boolean removeConnection(Connection conn) {
		synchronized (connectionPool) {
			return connectionPool.remove(conn);
		}
	}
	
	public BlockingQueue<Message> getMessageQueue() {
		return messageQueue;
	}
	
	public boolean hasStopped() {
		return hasStopped;
	}
	
	public void shutdown() {
		try {
			hasStopped = false;
			synchronized (connectionPool) {
				connectionPool.clear();
			}
			serverSocket.close();
		} catch (IOException e) {
			// socket already closed, do nothing
		}
	}
}
