package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import network.Connection;
import network.messages.Message;

/**
 * The central coordination service. Its main purpose is to observe
 * and define a global total order of messages and broadcast them 
 * to all participants/subscribers. 
 * 
 * @author CharlesXu
 */
public class Coordinator {
	
	private static final Logger LOGGER =
			Logger.getLogger( Coordinator.class.getName() );
	
	private ServerSocket serverSocket;
	private List<ConnectionToClient> connectionPool;
	private BlockingQueue<Message> messageQueue;
	private boolean hasStopped;
	
	public Coordinator(int port)
			throws IOException, InterruptedException {
		serverSocket = new ServerSocket(port);
		connectionPool = new ArrayList<>();
		messageQueue = new LinkedBlockingQueue<>();
		hasStopped = false;
        new Daemon(this).start();
        new Postman(this).start();
	}
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void addConnection(ConnectionToClient conn) {
		synchronized (connectionPool) {
			connectionPool.add(conn);
		}
	}
	
	public boolean removeConnection(Connection conn) {
		synchronized (connectionPool) {
			return connectionPool.remove(conn);
		}
	}
	
	public void broadcast(Message msg) {
		synchronized (connectionPool) {
			for (Connection conn : connectionPool) {
				if (!conn.isClosed()) {
					conn.send(msg);
				}
			}
		}
		LOGGER.info("Broadcasted to all: " + msg);
	}
	
	public BlockingQueue<Message> getMessageQueue() {
		return messageQueue;
	}
	
	public boolean hasStopped() {
		return hasStopped;
	}
	
	public void shutdown() {
		if (!hasStopped) {
			try {
				hasStopped = true;
				synchronized (connectionPool) {
					for (Connection conn : connectionPool)
						conn.close();
					connectionPool.clear();
				}
				serverSocket.close();
			} catch (IOException e) {
				// socket already closed, do nothing
			}
		}
	}
}
