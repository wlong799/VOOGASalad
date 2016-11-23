package network.client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import network.messages.ChatMessage;
import network.messages.Message;

public class Multiplexer {
	
	private Map<Class<?>, Queue<Message>> queues;
	
	public Multiplexer() {
		synchronized(Multiplexer.class) {
			queues = new HashMap<>();
			queues.put(ChatMessage.class, new LinkedList<Message>());
		}
	}
	
	public synchronized Queue<Message> getMessageQueue(Class<?> key) {
		return queues.get(key);
	}

	public synchronized void flush(Class<?> key) {
		queues.put(key, new LinkedList<Message>());
	}
}
