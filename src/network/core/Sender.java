package network.core;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.BlockingQueue;

import network.messages.Message;

/**
 * The worker thread that tries to push messages in the outGoingBuffer
 * of the connection
 * 
 * @author CharlesXu
 */
public class Sender extends Thread {
	
	private Connection connection;
	private BlockingQueue<Message> outGoingBuffer;
	
	/**
	 * Create a sender thread that synchronously write messages
	 * to the other end of the connection
	 * @param conn the connection through which the message is sent
	 * @param outGoingBuffer buffer for messages to be sent but have yet to send
	 */
	public Sender(Connection conn,
				  BlockingQueue<Message> outGoingBuffer) {
		this.connection = conn;
		this.outGoingBuffer = outGoingBuffer;
	}
	
	@Override
	public void run() {
		while (!connection.isClosed()) {
			try {
				ObjectOutputStream outputStream =
						new ObjectOutputStream(connection.getSocket().getOutputStream());
				Message msg = outGoingBuffer.take();
				outputStream.writeObject(msg);
				outputStream.flush();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} catch (IOException e) {
				connection.close();
			}
		}
	}
}
