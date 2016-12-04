package network.messages;

import network.client.Multiplexer;
import network.core.Connection;

/**
 * Special type of message that corresponds to some privileged
 * operation to be executed on the counter party
 * 
 * @author CharlesXu
 */
public abstract class SystemOperation<T> extends AbstractMessage<T> {

	private static final long serialVersionUID = -8441978966737291513L;
	
	private String toString;
	
	public SystemOperation() {}
	
	/**
	 * Allows subclasses to pass its String representation to
	 * implement a common toString method
	 * @param sender the userName of the client that sends this message
	 * @param toString String representation of the message
	 */
	public SystemOperation(String sender, String toString) {
		this(sender, null, toString);
	}
	
	/**
	 * Allow payload to be transmitted along with the message
	 * @param sender the userName of the client that sends this message
	 * @param payload extra information needed for the operation
	 * @param toString String representation of the message
	 */
	public SystemOperation(String sender, T payload, String toString) {
		super(sender, payload);
		this.toString = toString;
	}
	
	/**
	 * Defines the effect of the operation on the connection through
	 * which the operation message is sent.
	 * @param conn Connection that this operation acts on
	 */
	public abstract void execute(Connection conn);
	
	/**
	 * Refers to {@link Message#multiplex(Multiplexer)}
	 * <p> The default for a system operation is no-op
	 */
	@Override
	public void multiplex(Multiplexer mux) {
		//no-op
	}
	
	/**
	 * Common toString implementation for all subclasses
	 */
	@Override
	public String toString() {
		return toString;
	}
}
