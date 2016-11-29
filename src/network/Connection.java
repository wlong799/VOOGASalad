package network;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import network.exceptions.JeopardyException;
import network.messages.Message;

/**
 * A logical connection to another party. Use two threads as
 * sender and receiver to synchronize read/write requests. 
 * 
 * <p>We use session lease to detect failures or network partition.
 * Exactly one end of the connection will become the lease holder who
 * is responsible for the renewal of the session lease. Failure to do
 * so within bounded time leads to a state called Jeopardy where all
 * relevant resources on server will be reclaimed and client must
 * reconnect before proceeding to read/write messages.
 * 
 * @author CharlesXu
 */
public class Connection {
	
	private static final Logger LOGGER =
			Logger.getLogger( Connection.class.getName() );
	
	private Socket socket;
	private BlockingQueue<Message> outGoingBuffer;
	private boolean isClosed;
	private long lastActiveMillis;
	private String userName;
	
	public Connection(BlockingQueue<Message> incomingBuffer, 
					  Socket socket,
					  boolean isLeaseHolder) {
		this.socket = socket;
		this.outGoingBuffer = new LinkedBlockingQueue<>();
		this.isClosed = false;
		this.lastActiveMillis = System.currentTimeMillis();
		new Receiver(socket, this, incomingBuffer).start();
		new Sender(socket, this, outGoingBuffer).start();
		new LeaseTimer(this, isLeaseHolder).start();
	}
	
	public void send(Message msg) {
		if (!isClosed) {
			try {
				outGoingBuffer.put(msg);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public synchronized boolean isClosed() {
		return isClosed;
	}
	
	public synchronized long getLastActiveMillis() {
		return lastActiveMillis;
	}

	public synchronized void setLastActiveMillis(long lastActiveMillis) {
		this.lastActiveMillis = lastActiveMillis;
	}
	
	/**
	 * Close the socket as connection to the party on the other side.
	 * Reader and sender thread will exit. 
	 * @throws IOException when try to close a socket that is
	 * 		   already closed.
	 */
	public synchronized void close() {
		if (!isClosed) {
			isClosed = true;
			try {
				while(!outGoingBuffer.isEmpty()) {}
				socket.close();
			} catch (IOException e) {
				// the socket is already closed, possibly by another thread
				// nothing and child threads will gracefully exit
			}
			LOGGER.info("Connection closed");
		}
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
}
