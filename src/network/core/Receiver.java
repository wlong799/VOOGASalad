package network.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

import network.messages.Message;
import network.messages.SystemOperation;
import network.server.Daemon;

/**
 * The worker thread that reads from connection to server and 
 * put messages read into client supplied buffer.
 * 
 * @author CharlesXu
 */
public class Receiver extends Thread {
	
	private Connection connection;
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
				ObjectInputStream objectInputStream =
						new ObjectInputStream(socket.getInputStream());
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
