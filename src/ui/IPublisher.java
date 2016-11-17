package ui;

public interface IPublisher {
	
	void addSubscriber(ISubscriber client);
	void notifySubscribers();
	
}
