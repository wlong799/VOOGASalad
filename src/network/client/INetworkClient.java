package network.client;

import java.util.Queue;

import network.INetworkConfig;
import network.messages.Message;

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
	 * @return a queue of messages read ordered in time
	 */
	Queue<Message> read(Class<?> messageType);
	
	/**
	 * Send a message to all its peers through the central server
	 * @param msg a Message to be broadcast to all its peers
	 */
	void broadcast(Message msg);
	
	//TODO cx15 unicast
	//TODO cx15 close connection
	
	/**
	 * Disconnect from server and gracefully clean up
	 */
	void disconnect();
}
