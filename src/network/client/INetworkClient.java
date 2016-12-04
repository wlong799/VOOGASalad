package network.client;

import java.util.Queue;

import network.exceptions.SessionExpiredException;
import network.exceptions.MessageCreationFailureException;
import network.messages.Message;
import network.messages.MessageType;

/**
 * The Client API for access to the network to talk to other participants 
 * in the game.
 * 
 * @author CharlesXu
 */
public interface INetworkClient {
	
	/**
	 * Read all messages of the <code>messageType</code> that
	 * arrived after last invocation to read() and returns an
	 * empty queue if no new messages received.
	 * @param type specifies the type of message to be read
	 * @return a queue of messages in ordered by arrival time
	 * @throws SessionExpiredException if lost connection to server
	 */
	Queue<Message> read(MessageType type) throws SessionExpiredException;
	
	/**
	 * Send the payload wrapped in a a message to all its peers
	 * through the central server, auto serialization
	 * @param payload the object to be sent
	 * @param type specifies the type of message to be sent
	 * @throws MessageCreationFailureException if MessageType unknown to the
	 * 											current version of applicaiton
	 * @throws SessionExpiredException if lost connection to server
	 */
	void broadcast(Object payload, MessageType type)
			throws MessageCreationFailureException, SessionExpiredException;
	
	/**
	 * Disconnect from server and gracefully clean up
	 */
	void disconnect();
}
