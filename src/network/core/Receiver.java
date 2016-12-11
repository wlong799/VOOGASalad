package network.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

import network.messages.Message;
import network.messages.system.SystemOperation;

/**
 * The worker thread that reads from a connection and 
 * put messages read into the incoming message buffer
 * 
 * @author CharlesXu
 */
public class Receiver extends Thread {
	
	private Connection connection;
	private BlockingQueue<Message> inComingBuffer;
	
	private static final Logger LOGGER =
			Logger.getLogger( Receiver.class.getName() );
	
	/**
	 * Create a receiver thread that synchronously read messages
	 * coming into the connection
	 * @param conn the connection to read from
	 * @param inComingBuffer the destination buffer to store message read
	 */
	public Receiver(Connection conn,
					BlockingQueue<Message> inComingBuffer) {
		this.connection = conn;
		this.inComingBuffer = inComingBuffer;
	}

	@Override
	public void run() {
		while (!connection.isClosed()) {
			try {
				ObjectInputStream objectInputStream =
						new ObjectInputStream(connection.getSocket().getInputStream());
				Message msg = (Message) objectInputStream.readObject();
				LOGGER.info("Receiver " + this.getId() +
							" received msg: " + msg + 
							" from " + msg.getSender());
				if (msg instanceof SystemOperation) {
					((SystemOperation<?>)msg).execute(connection);
				} else {
					inComingBuffer.put(msg);
				}
			} catch (IOException | ClassNotFoundException e) {
				// IOException : the socket is closed, or not connected, or input has been shutdown
				// ClassNotFoundException: reflection failed
				connection.close();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
        }
	}
}
