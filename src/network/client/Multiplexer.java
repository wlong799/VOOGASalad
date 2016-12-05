package network.client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import network.messages.Message;
import network.messages.MessageType;

/**
 * The Multiplexer maintains multiple receiver message queues and distribute
 * the incoming messages to the right queue based on its type. 
 * 
 * @author CharlesXu
 */
public class Multiplexer {
	
	private Map<MessageType, Queue<Message>> queues;
	
	/**
	 * Initialize a message queue for each type of message known to the system
	 */
	public Multiplexer() {
		synchronized(Multiplexer.class) {
			queues = new HashMap<>();
			for(MessageType type : MessageType.values()) {
				queues.put(type, new LinkedList<Message>());
			}
		}
	}
	
	/**
	 * Get the message queue of the specified type by key
	 * @param key specifies a MessageType whose queue is demanded
	 * @return a queue of messages all of the same type as key
	 */
	public synchronized Queue<Message> getMessageQueue(MessageType key) {
		return queues.get(key);
	}

	/**
	 * Clear the message queue associated with message type <tt>key</tt>
	 * such that the queue becomes empty.
	 * @param key specifies a MessageType whose queue is to be cleared
	 */
	public synchronized void flush(MessageType key) {
		queues.put(key, new LinkedList<Message>());
	}
}
