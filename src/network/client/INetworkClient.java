package network.client;

import java.util.Queue;

import network.Message;

/**
 * The Client API for access to network to talk to 
 * other participants in the game
 * @author CharlesXu
 */
public interface INetworkClient {
	
	/**
	 * Read all messages that arrived after last invocation to read() 
	 * and returns an empty queue if no new messages received.
	 * @return a queue of messages read ordered in time
	 */
	Queue<Message> read();
	
	/**
	 * Send a message to all its peers through the central server
	 * @param msg a Message to be broadcast to all its peers
	 */
	void broadcast(Message msg);
	
	//TODO cx15 unicast
}
