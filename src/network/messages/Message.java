package network.messages;

import java.io.Serializable;

import network.client.Multiplexer;

/**
 * Defines a universal wrapper for any object as payload to be
 * sent through network
 * @author CharlesXu
 */
public interface Message extends Serializable {

	// TODO cx15 session lease / keepAlive hearbeat with each client
	// TODO cx15 extends Message and build multiplexer for different types of msg
	
	Object getPayload();
	
	String getSender();
	
	void setSender(String sender);
	
	void multiplex(Multiplexer mux);

}
