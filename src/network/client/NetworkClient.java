package network.client;

import java.io.IOException;
import java.net.Socket;
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
	private Connection connectionToServer;
	
	public NetworkClient() throws ServerDownException {
		try {
			socket = new Socket(SERVER_NAME, SERVER_PORT);
			inComingBuffer = new LinkedBlockingQueue<>();
			connectionToServer = new Connection(inComingBuffer, socket);
		} catch (IOException e) {
			throw new ServerDownException();
		}
	}
	
	/**
	 * Check if the receiver buffer is not empty
	 * @return true if subsequent read is nonblocking
	 */
	public boolean newMessageArrived() {
		return !inComingBuffer.isEmpty();
	}
	
	/**
	 * This call is blocking when no more cached message i.e. all
	 * messages have been delivered/read. If one wish to call this
	 * method from a single-threaded process, always check
	 * <code>newMessageArrived()</code> before proceeding.
	 * @return one received message from network
	 * @throws InterruptedException 
	 */
	public Message read() throws InterruptedException {
		return inComingBuffer.take();
	}
}
