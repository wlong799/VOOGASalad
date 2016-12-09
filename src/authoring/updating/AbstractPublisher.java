package authoring.updating;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractPublisher implements IPublisher {

    private Set<ISubscriber> mySubscribers;

    public AbstractPublisher() {
        mySubscribers = new HashSet<ISubscriber>();
    }

    public void addSubscriber(ISubscriber client) {
        mySubscribers.add(client);
    }

    public void notifySubscribers() {
        mySubscribers.stream().forEach(client -> client.didUpdate(this));
    }

}
