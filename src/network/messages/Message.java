package network.messages;

import java.io.Serializable;

import network.client.Multiplexer;

/**
 * Defines a universal wrapper for any object as payload to be
 * sent through network. Objects sent are automatically flatten and
 * reconstructed but is treated as a blob. Sender information is
 * immutable and could only be retrieved.
 * 
 * @author CharlesXu
 */
public interface Message extends Serializable {

	/**
	 * Get the payload wrapped in the message
	 * @return the payload object
	 */
	Object getPayload();
	
	/**
	 * Get the sender of the message
	 * @return the sender of the message
	 */
	String getSender();
	
	/**
	 * There are many types of messages and each type might be handled
	 * differently by the receiver. By Implementing this method differently
	 * all subclasses of Message puts itself on the right buffer without
	 * a big if tree.
	 * @param mux a Multiplexer that provides access to all queues
	 */
	void multiplex(Multiplexer mux);
}
