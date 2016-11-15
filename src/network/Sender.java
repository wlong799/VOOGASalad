package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class Sender extends Thread {
	
	private Connection connection;
	private BlockingQueue<Message> outGoingBuffer;
	private ObjectOutputStream outputStream;
	
	public Sender(Socket socket,
				  Connection conn,
				  BlockingQueue<Message> outGoingBuffer)
						  throws IOException {
		this.connection = conn;
		this.outGoingBuffer = outGoingBuffer;
		this.outputStream = new ObjectOutputStream(socket.getOutputStream());
	}
	
	@Override
	public void run() {
		while (!connection.isClosed()) {
			try {
				outputStream.writeObject("hello from client " + this.getId());
				outputStream.flush();
			} catch (IOException e) {
				// TODO cx15
				e.printStackTrace();
			}
		}
	}
}
