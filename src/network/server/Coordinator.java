package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import network.core.Connection;
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
	private Vector<ConnectionToClient> connectionPool;
	private BlockingQueue<Message> messageQueue;
	private boolean hasStopped;
	
	/**
	 * Create a coordinator by starting a daemon thread listening on
	 * <tt>port</tt> and a postman thread to deliver all message received
	 * to all clients. 
	 * @param port the port number on which the server listens
	 * @throws IOException if port already in use
	 */
	public Coordinator(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		connectionPool = new Vector<>();
		messageQueue = new LinkedBlockingQueue<>();
		hasStopped = false;
        new Daemon(this).start();
        new Postman(this).start();
	}
	
	/**
	 * @return the serverSocket used to accept connection
	 */
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	/**
	 * Add a new connection to the connection pool
	 * @param conn the connection to be added
	 */
	public synchronized void addConnection(ConnectionToClient conn) {
		connectionPool.add(conn);
	}
	
	/**
	 * Remove a new connection from the connection pool
	 * @param conn the connection to be removed
	 * @return true if the the connection pool contained the given connection
	 */
	public synchronized boolean removeConnection(Connection conn) {
		return connectionPool.remove(conn);
	}
	
	/**
	 * Send the message to all connections in the pool
	 * @param msg the message to be broadcast
	 */
	public synchronized void broadcast(Message msg) {
		for (Connection conn : connectionPool) {
			if (!conn.isClosed()) {
				conn.send(msg);
			}
		}
		LOGGER.info("Broadcasted to all: " + msg);
	}
	
	/**
	 * @return the master message queue for all incoming messages
	 */
	public BlockingQueue<Message> getMessageQueue() {
		return messageQueue;
	}
	
	/**
	 * @return true if coordinator has been shut down
	 */
	public boolean hasStopped() {
		return hasStopped;
	}
	
	/**
	 * close all connection and sockets and goes down
	 */
	public void shutdown() {
		if (!hasStopped) {
			try {
				hasStopped = true;
				for (Connection conn : connectionPool)
					conn.close();
				connectionPool.clear();
				serverSocket.close();
			} catch (IOException e) {
				// socket already closed, do nothing
			}
		}
	}
}
