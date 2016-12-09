package network.core;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

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
 * <p> Each connection is identified by a userName which is attached
 * to all the messages sent through the connection.
 * 
 * @author CharlesXu
 */
public abstract class Connection {
	
	private static final Logger LOGGER =
			Logger.getLogger( Connection.class.getName() );
	
	private Socket socket;
	private BlockingQueue<Message> outGoingBuffer;
	private boolean isClosed;
	private long lastActiveMillis;
	private String userName;
	
	/**
	 * Create a connection with incoming and outgoing message buffers
	 * and initialize sender and receiver threads to synchronously read
	 * and write through the connected <tt>socket</tt>.
	 * @param incomingBuffer Upper layer application provided receiver buffers
	 * @param socket A successfully established socket to the counter party
	 * @param isLeaseHolder if true the lease holder is responsible for lease renewal
	 */
	public Connection(BlockingQueue<Message> incomingBuffer, 
					  Socket socket,
					  boolean isLeaseHolder) {
		this.socket = socket;
		this.outGoingBuffer = new LinkedBlockingQueue<>();
		this.isClosed = false;
		this.lastActiveMillis = System.currentTimeMillis();
		new Receiver(this, incomingBuffer).start();
		new Sender(this, outGoingBuffer).start();
		new LeaseTimer(this, isLeaseHolder).start();
	}
	
	/**
	 * Send a message through the connection by placing it on
	 * the outgoing/sender message buffer
	 * @param msg the message to be sent
	 */
	public void send(Message msg) {
		if (!isClosed) {
			try {
				outGoingBuffer.put(msg);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	/**
	 * Check if the connection is closed
	 * @return true is connection is closed
	 */
	public synchronized boolean isClosed() {
		return isClosed;
	}
	
	/**
	 * Get the time in millisecond when the last message is sent or received 
	 * @return the last active time in millisecond
	 */
	public synchronized long getLastActiveMillis() {
		return lastActiveMillis;
	}

	/**
	 * Set the time in millisecond when the last message is sent or received 
	 * @param lastActiveMillis a new value for the last active time in millisecond
	 */
	public synchronized void setLastActiveMillis(long lastActiveMillis) {
		this.lastActiveMillis = lastActiveMillis;
	}
	
	/**
	 * Close the connection by gracefully shut down and reclaim resources
	 * and inform the counter party.
	 * 
	 * On our side, socket will be closed. 
	 * Reader, sender, leaseTimer threads will exit. 
	 * 
	 * @throws IOException when try to close a socket that is already closed.
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
	
	/**
	 * @return the socket to the counter party.
	 */
	public Socket getSocket() {
		return socket;
	}
	
	/**
	 * Update the userName of this connection
	 * @param userName new user name of the connection
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Get the userName of this connection
	 * @return the userName of this connection
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Acquire a lock on the object identified using <tt>id</tt>. If lock
	 * already held by other client, return the holder's userName.
	 * 
	 * @param id identifies the server object to be locked
	 * @return the userName of the client that currently holding the lock
	 */
	public String trylock(String id) {
		return null;
	}
	
	/**
	 * Release the lock on the object identified using <tt>id</tt>.
	 * If the lock is held by client different from the request issuer
	 * or if the lock is free, this call is a no-op.
	 * @param id identifies the server object to be locked
	 */
	public void unlock(String id) {
		return;
	}
}
