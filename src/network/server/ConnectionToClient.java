package network.server;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

import network.core.Connection;
import network.exceptions.MessageCreationFailureException;
import network.messages.Message;
import network.messages.MessageType;

/**
 * The connection to client does the house keeping such that
 * when the corresponding connection to server is closed either
 * explicitly by the client or implicitly due to timeout, it removes
 * itself from the connection pool and broadcast to the remaining clients
 * about this departure. 
 * 
 * @author CharlesXu
 */
public class ConnectionToClient extends Connection {
	
	private static final Logger LOGGER =
			Logger.getLogger( ConnectionToClient.class.getName() );
	
	private Coordinator coordinator;

	public ConnectionToClient(BlockingQueue<Message> incomingBuffer,
							  Socket socket,
							  boolean isLeaseHolder,
							  Coordinator coordinator) {
		super(incomingBuffer, socket, isLeaseHolder);
		this.coordinator = coordinator;
	}

	/**
	 * Remove the connection from the connection pool and send to all other clients
	 * about the departure of this client
	 */
	@Override
	public synchronized void close() {
		super.close();
		coordinator.removeConnection(this);
		try {
			coordinator.broadcast(MessageType.USER_GONE_OFFLINE.build(this.getUserName()));
		} catch (MessageCreationFailureException e) {
			LOGGER.info("Connection to client " + this.getUserName() +
					"failed to broadcast its departure");
		}
	}
	
	/**
	 * Refers to {@link Connection#trylock(String)}
	 */
	@Override
	public String trylock(String id) {
		return coordinator.trylock(id, this.getUserName());
	}
	
	/**
	 * Refers to {@link Connection#unlock(String)}
	 */
	@Override
	public void unlock(String id) {
		coordinator.unlock(id, this.getUserName());
	}
}
