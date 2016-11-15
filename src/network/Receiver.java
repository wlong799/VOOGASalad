package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;


public class Receiver extends Thread {
	
	private Connection connection;
	private ObjectInputStream objectInputStream;
	private BlockingQueue<Message> inComingBuffer;
	
	public Receiver(Socket socket,
					Connection conn,
					BlockingQueue<Message> inComingBuffer)
			throws IOException {
		this.connection = conn;
		this.objectInputStream =
				new ObjectInputStream(socket.getInputStream());
		this.inComingBuffer = inComingBuffer;
	}

	@Override
	public void run() {
		while (!connection.isClosed()) {
			try {
				Message msg = (Message) objectInputStream.readObject();
				System.out.println("received msg: " + msg);
				inComingBuffer.put(msg);
			} catch (IOException | ClassNotFoundException | InterruptedException e) {
				// TODO cx15 properly close connection
				e.printStackTrace();
			}
        }
	}
}
