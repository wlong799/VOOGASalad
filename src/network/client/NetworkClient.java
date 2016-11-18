package network.client;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import network.Connection;
import network.Message;
import network.exceptions.ServerDownException;

public class NetworkClient {
	
	// TODO cx15 read config from a file
	public static final String SERVER_NAME = "127.0.0.1";
	public static final int SERVER_PORT = 9999;
	
	private Socket socket;
	private BlockingQueue<Message> inComingBuffer;
	private Queue<Message> shared;
	private Connection connectionToServer;
	private Reader reader;
	
	public NetworkClient() throws ServerDownException {
		try {
			socket = new Socket(SERVER_NAME, SERVER_PORT);
			inComingBuffer = new LinkedBlockingQueue<>();
			connectionToServer = new Connection(inComingBuffer, socket);
			shared = new LinkedList<>();
			reader = new Reader(inComingBuffer, shared, connectionToServer);
			reader.start();
		} catch (IOException e) {
			throw new ServerDownException();
		}
	}
	
	/**
	 * To read messages periodically from server that is non-blocking.
	 * @return a queue of messages that arrived after
	 * last invocation to read() 
	 */
	public Queue<Message> read() {
		synchronized(shared) {
			Queue<Message> ret = shared;
			shared = new LinkedList<>();
			return ret;
		}
	}
	
	public void send(Message msg) throws InterruptedException {
		connectionToServer.send(msg);
	}
}
