package network.messages;

import network.exceptions.MessageCreationFailureException;
import network.utils.Reflection;
import network.utils.ReflectionException;

/**
 * The Enumeration of all types of messages. It uses reflection
 * to dynamically create new instance of message. It makes no need for
 * big if tree, and hides the implementation details from user such that
 * when Message class changes, the users are not affected at all. 
 * 
 * @author CharlesXu
 */
public enum MessageType {
	
	// TODO cx15 organzie the message package
	
	CHAT ("network.messages.ChatMessage"),
	USER_GONE_OFFLINE ("network.messages.UserGoneOfflineMessage"),
	EDIT_ACTION ("TODO"), // TODO cx15
	
	TRYLOCK ("TODO"), // TODO cx15
	UNLOCK ("TODO"), // TODO cx15 
	HANDSHAKE ("network.messages.HandShake"),
	DISCONNECT ("network.messages.Disconnect"),
	SESSION_LEASE ("network.messages.SessionLease"),
	SESSION_LEASE_GRANTED ("network.messages.SessionLeaseGranted");
	
	private static final String MORE_THAN_ONE_PAYLOAD =
			"Each Message can only carry zero or one payload";
	
	private String className;
	
	private MessageType(String className) {
		this.className = className;
	}
	
	/**
	 * Create a Message of proper type that carries an optional payload.
	 * @param sender the userName of the client that sends the message
	 * @param payload the optional object to be enclosed by the message
	 * @return The message that wraps the payload
	 * @throws MessageCreationFailureException if reflections on Message subclass failed
	 */
	public Message build(String sender, Object ... payload)
			throws MessageCreationFailureException {
		Message msg = null;
		try {
			if (payload.length == 0)
				msg = (Message) Reflection.createInstance(className, sender);
			else if (payload.length == 1)
				msg = (Message) Reflection.createInstance(className, sender, payload[0]);
			else throw new MessageCreationFailureException(MORE_THAN_ONE_PAYLOAD);
		} catch (ReflectionException e) {
			throw new MessageCreationFailureException(e);
		}
		return msg;
	}

}
