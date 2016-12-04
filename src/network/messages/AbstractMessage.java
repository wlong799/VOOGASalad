package network.messages;

/**
 * An partial implementation of the Message interface to
 * aggregate common code of all sub classes
 * 
 * @author CharlesXu
 * @param <T> the type of payload to be carried in this message
 */
public abstract class AbstractMessage<T> implements Message {
	
	private static final long serialVersionUID = -1904304542591909587L;
	
	private String sender;
	private T payload;
	
	public AbstractMessage() {}

	/**
	 * Canonical constructor for a message with a sender and payload
	 * @param sender the userName of sender
	 * @param payload the object wrapped by this message
	 */
	public AbstractMessage(String sender, T payload) {
		this.sender = sender;
		this.payload = payload;
	}
	
	/**
	 * Refers to {@link Message#getSender()}
	 */
	@Override
	public String getSender() {
		return sender;
	}
	
	/**
	 * Refers to {@link Message#getPayload()}
	 */
	@Override
	public T getPayload() {
		return payload;
	}
	
	/**
	 * Return a string representation of this message.
	 * Most likely for logging purposes
	 */
	@Override
	public String toString() {
		return payload.toString();
	}
}
