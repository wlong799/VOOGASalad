package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import network.messages.Message;

/**
 * The worker thread that tries to push messages in the outGoingBuffer
 * to server who is going to forward them to all subscribers
 * 
 * @author CharlesXu
 */
public class Sender extends Thread {
	
	private Connection connection;
	private Socket socket;
	private BlockingQueue<Message> outGoingBuffer;
	
	public Sender(Socket socket,
				  Connection conn,
				  BlockingQueue<Message> outGoingBuffer) {
		this.connection = conn;
		this.outGoingBuffer = outGoingBuffer;
		this.socket = socket;
	}
	
	@Override
	public void run() {
		while (!connection.isClosed()) {
			try {
				ObjectOutputStream outputStream =
						new ObjectOutputStream(socket.getOutputStream());
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
