package network.client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import network.messages.Message;
import network.messages.MessageType;

/**
 * @author CharlesXu
 */
public class Multiplexer {
	
	private Map<MessageType, Queue<Message>> queues;
	
	public Multiplexer() {
		synchronized(Multiplexer.class) {
			queues = new HashMap<>();
			for(MessageType type : MessageType.values()) {
				queues.put(type, new LinkedList<Message>());
			}
		}
	}
	
	public synchronized Queue<Message> getMessageQueue(MessageType key) {
		return queues.get(key);
	}

	public synchronized void flush(MessageType key) {
		queues.put(key, new LinkedList<Message>());
	}
}
