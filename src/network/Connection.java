package network;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import network.messages.Message;

/**
 * A logical connection to another party. Use two threads as
 * sender and receiver to synchronize read/write requests.
 * @author CharlesXu
 */
public class Connection {
	
	// TODO cx15 handshake to get connection information
	
	private static final Logger LOGGER =
			Logger.getLogger( Connection.class.getName() );
	
	private Socket socket;
	private BlockingQueue<Message> outGoingBuffer;
	private boolean isClosed;
	
	public Connection(BlockingQueue<Message> incomingBuffer, 
					  Socket socket) {
		this.socket = socket;
		this.outGoingBuffer = new LinkedBlockingQueue<>();
		this.isClosed = false;
		new Receiver(socket, this, incomingBuffer).start();
		new Sender(socket, this, outGoingBuffer).start();
	}
	
	public void send(Message msg) {
		try {
			outGoingBuffer.put(msg);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	public synchronized boolean isClosed() {
		return isClosed;
	}

	/**
	 * Close the socket as connection to the party on the other side.
	 * Reader and sender thread will exit. 
	 * @throws IOException when try to close a socket that is
	 * 		   already closed.
	 */
	public synchronized void close() {
		this.isClosed = true;
		try {
			socket.close();
		} catch (IOException e) {
			// the socket is already closed, possibly by another thread
			// nothing and child threads will gracefully exit
		}
		LOGGER.info("Connection closed");
	}
}
