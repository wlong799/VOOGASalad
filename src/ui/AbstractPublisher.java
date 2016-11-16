package ui;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractPublisher implements Publisher {
	
	private Set<Subscriber> subscribers;
	
	public AbstractPublisher() {
		subscribers = new HashSet<Subscriber>();
	}
	
	public void addSubscriber(Subscriber client) {
		subscribers.add(client);
	}
	
	public void notifySubscribers() {
		subscribers.stream().forEach(client -> client.didUpdate(this));
	}

}
