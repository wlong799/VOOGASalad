package authoring.updating;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractPublisher implements IPublisher {

    private Set<ISubscriber> subscribers;

    public AbstractPublisher() {
        subscribers = new HashSet<ISubscriber>();
    }

    public void addSubscriber(ISubscriber client) {
        subscribers.add(client);
    }

    public void notifySubscribers() {
        subscribers.stream().forEach(client -> client.didUpdate(this));
    }

}
