package network.server;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import network.Message;

public class Connection {
	
	private Socket clientConn;
	private BlockingQueue<Message> totalMessageQueue;
	private BlockingQueue<Message> outGoingBuffer;
	public Connection(Socket clientConn,
			          BlockingQueue<Message> totalMessageQueue) {
		this.clientConn = clientConn;
		this.totalMessageQueue = totalMessageQueue;
	}
	// TODO cx15 sender and receiver

}
