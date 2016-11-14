package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import network.Message;

/**
 * The daemon/dispatcher/master thread on server side, which listens
 * on the serverSocket to accept new connections from clients.
 * @author CharlesXu
 */
public class Daemon extends Thread {
	
	private ServerSocket serverSocket;
	private List<Connection> connectionPool;
	private BlockingQueue<Message> messageQueue;
	
	public Daemon(ServerSocket serverSocket,
				  List<Connection> connectionPool,
				  BlockingQueue<Message> messageQueue) {
		this.serverSocket = serverSocket;
		this.connectionPool = connectionPool;
		this.messageQueue = messageQueue;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Socket clientConn = serverSocket.accept();
				connectionPool.add(new Connection(clientConn, messageQueue));
			} catch (IOException e) {
				// TODO cx15
				e.printStackTrace();
			}
		}
    }
}
