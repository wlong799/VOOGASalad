package network.messages;

import java.lang.reflect.InvocationTargetException;

import network.exceptions.MessageCreationFailureException;
import network.exceptions.ReflectionFoundNoMatchesException;
import network.utils.ReflectionUtils;

/**
 * The Enumeration of all types of messages. It uses reflection
 * to dynamically create new instance of message. It makes no need for
 * big if tree, and hides the implementation details from user such that
 * when Message class changes, the users are not affected at all. 
 * 
 * @author CharlesXu
 */
public enum MessageType {
	
	CHAT ("network.messages.ChatMessage"),
	PLAYER_MODEL ("TODO"), // TODO cx15 broadcast the player model to all other players
	USER_GONE_OFFLINE ("network.messages.UserGoneOfflineMessage"),
	
	JOIN ("TODO"),
	DISCONNECT ("network.messages.Disconnect"),
	SESSION_LEASE ("network.messages.SessionLease"),
	SESSION_LEASE_GRANTED ("network.messages.SessionLeaseGranted");
	
	private String className;
	
	private MessageType(String className) {
		this.className = className;
	}
	
	public Message build(Object... payload)
			throws MessageCreationFailureException{
		Message msg = null;
		try {
			msg = (Message) ReflectionUtils.newInstanceOf(className, payload);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| ClassNotFoundException | ReflectionFoundNoMatchesException e) {
			throw new MessageCreationFailureException();
		}
		return msg;
	}

}
