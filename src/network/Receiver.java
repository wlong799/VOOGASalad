package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;


public class Receiver extends Thread {
	
	private Connection connection;
	private ObjectInputStream objectInputStream;
	private Socket socket;
	private BlockingQueue<Message> inComingBuffer;
	
	public Receiver(Socket socket,
					Connection conn,
					BlockingQueue<Message> inComingBuffer)
			throws IOException {
		this.connection = conn;
		this.socket = socket;
		this.inComingBuffer = inComingBuffer;
		System.out.println("Receiver");
	}

	@Override
	public void run() {
		while (!connection.isClosed()) {
			try {
				this.objectInputStream =
						new ObjectInputStream(socket.getInputStream());
				Message msg = (Message) objectInputStream.readObject();
				System.out.println("received msg: " + msg);
				inComingBuffer.put(msg);
				System.out.println("receiver put");
			} catch (IOException | ClassNotFoundException | InterruptedException e) {
				// TODO cx15 properly close connection
				e.printStackTrace();
			}
        }
	}
}
