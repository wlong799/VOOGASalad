package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class Sender extends Thread {
	
	private Connection connection;
	private Socket socket;
	private BlockingQueue<Message> outGoingBuffer;
	private ObjectOutputStream outputStream;
	
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
				this.outputStream =
						new ObjectOutputStream(socket.getOutputStream());
				Message msg = outGoingBuffer.take();
				outputStream.writeObject(msg);
				outputStream.flush();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} catch (IOException e) {
				// TODO cx15 disconnect and abort
				e.printStackTrace();
			}
		}
	}
}
