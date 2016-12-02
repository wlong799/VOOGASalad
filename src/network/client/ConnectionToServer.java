package network.client;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import network.Connection;
import network.exceptions.MessageCreationFailureException;
import network.messages.Message;
import network.messages.MessageType;

/**
 * The connection to server does a handshake with server to provide
 * the userName of the client
 * @author CharlesXu
 */
public class ConnectionToServer extends Connection {
	
	public ConnectionToServer(BlockingQueue<Message> incomingBuffer,
							  Socket socket,
							  boolean isLeaseHolder,
							  String userName) {
		super(incomingBuffer, socket, isLeaseHolder);
		try {
			// TODO cx15 duplicated names
			send(MessageType.HANDSHAKE.build(userName));
		} catch (MessageCreationFailureException e) {
			this.close();
		}
	}

}
