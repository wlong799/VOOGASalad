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
				  BlockingQueue<Message> outGoingBuffer)
						  throws IOException {
		this.connection = conn;
		this.outGoingBuffer = outGoingBuffer;
		this.socket = socket;
	}
	
	@Override
	public void run() {
		while (!connection.isClosed()) {
			try {
				this.outputStream = new ObjectOutputStream(socket.getOutputStream());
				Message msg = outGoingBuffer.take();
				System.out.println("message " + msg + " sent");
				outputStream.writeObject(msg);
				outputStream.flush();
			} catch (IOException | InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
