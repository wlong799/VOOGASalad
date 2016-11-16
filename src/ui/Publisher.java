package ui;

public interface Publisher {
	
	void addSubscriber(Subscriber client);
	void notifySubscribers();
	
}
