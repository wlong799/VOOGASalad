package authoring.updating;

public interface IPublisher {
	
	void addSubscriber(ISubscriber client);
	void notifySubscribers();
	
}
