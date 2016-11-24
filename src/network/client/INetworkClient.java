package network.client;

import java.util.Queue;

import network.INetworkConfig;
import network.exceptions.JeopardyException;
import network.exceptions.MessageCreationFailureException;
import network.messages.Message;
import network.messages.MessageType;

/**
 * The Client API for access to network to talk to 
 * other participants in the game
 * @author CharlesXu
 */
public interface INetworkClient extends INetworkConfig {
	
	/**
	 * Read all messages of the <code>messageType</code> that
	 * arrived after last invocation to read() and returns an
	 * empty queue if no new messages received.
	 * @param type specifies the type of message to be read
	 * @return a queue of messages in ordered by arrival time
	 * @throws JeopardyException if lost connection to server
	 */
	Queue<Message> read(MessageType type) throws JeopardyException;
	
	/**
	 * Send the payload wrapped in a a message to all its peers
	 * through the central server, auto serialization
	 * @param payload the object ot be sent
	 * @param type specifies the type of message to be sent
	 * @throws MessageCreationFailureException if MessageType unknown to the
	 * 											current version of applicaiton
	 * @throws JeopardyException if lost connection to server
	 */
	void broadcast(Object payload, MessageType type)
			throws MessageCreationFailureException, JeopardyException;
	
	/**
	 * Disconnect from server and gracefully clean up
	 */
	void disconnect();
	
	/**
	 * reconnect to server
	 */
	void reconnect();
}
