package network.server;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import network.Connection;
import network.exceptions.MessageCreationFailureException;
import network.messages.Message;
import network.messages.MessageType;

public class ConnectionToClient extends Connection {
	
	private Coordinator coordinator;

	public ConnectionToClient(BlockingQueue<Message> incomingBuffer,
							  Socket socket,
							  boolean isLeaseHolder,
							  Coordinator coordinator) {
		super(incomingBuffer, socket, isLeaseHolder);
		this.coordinator = coordinator;
	}

	@Override
	public synchronized void close() {
		super.close();
		coordinator.removeConnection(this);
		try {
			coordinator.broadcast(MessageType.USER_GONE_OFFLINE.build(
					"User " + this.getUserName() + " disconnected"));
		} catch (MessageCreationFailureException e) {
			// trusted code
		}
	}
}
