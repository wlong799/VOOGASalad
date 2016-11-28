package network.messages;

import java.io.Serializable;

import network.client.Multiplexer;

/**
 * Defines a universal wrapper for any object as payload to be
 * sent through network
 * @author CharlesXu
 */
public interface Message extends Serializable {

	Object getPayload();
	
	String getSender();
	
	void setSender(String sender);
	
	/**
	 * There are many types of messages and each type might be handled
	 * differently by the receiver. By Implementing this method differently
	 * all subclasses of Message puts itself on the right buffer without
	 * a big if tree.
	 * @param mux a Multiplexer that provides access to all queues
	 */
	void multiplex(Multiplexer mux);
}
