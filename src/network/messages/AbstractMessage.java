package network.messages;

/**
 * An partial implementation of the Message interface to
 * aggregate common code of all sub classes
 * @author CharlesXu
 * @param <T> the type of payload to be carried in this message
 */
public abstract class AbstractMessage<T> implements Message{
	
	private static final long serialVersionUID = -1904304542591909587L;
	
	private String sender;
	private T payload;
	
	public AbstractMessage() {}
	
	public AbstractMessage(T payload) {
		this(null, payload);
	}
	
	public AbstractMessage(String sender, T payload) {
		this.sender = sender;
		this.payload = payload;
	}
	
	@Override
	public String getSender() {
		return sender;
	}
	
	@Override
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	@Override
	public T getPayload() {
		return payload;
	}
	
	@Override
	public String toString() {
		return payload.toString();
	}
}
