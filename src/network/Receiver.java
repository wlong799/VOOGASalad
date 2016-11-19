package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

import network.server.Daemon;


public class Receiver extends Thread {
	
	private Connection connection;
	private ObjectInputStream objectInputStream;
	private Socket socket;
	private BlockingQueue<Message> inComingBuffer;
	
	private static final Logger LOGGER =
			Logger.getLogger( Daemon.class.getName() );
	
	public Receiver(Socket socket,
					Connection conn,
					BlockingQueue<Message> inComingBuffer) {
		this.connection = conn;
		this.socket = socket;
		this.inComingBuffer = inComingBuffer;
	}

	@Override
	public void run() {
		while (!connection.isClosed()) {
			try {
				this.objectInputStream =
						new ObjectInputStream(socket.getInputStream());
				Message msg = (Message) objectInputStream.readObject();
				LOGGER.info("Receiver " + this.getId() + " received msg: " + msg);
				inComingBuffer.put(msg);
			} catch (IOException | ClassNotFoundException | InterruptedException e) {
				// TODO cx15 properly close connection
				e.printStackTrace();
			}
        }
	}
}
