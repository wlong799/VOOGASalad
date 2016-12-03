package network.messages;

import network.client.Multiplexer;
import network.core.Connection;

/**
 * Special type of message that corresponds to some privileged
 * operation to be execute on the counter party
 * @author CharlesXu
 */
public abstract class SystemOperation<T> extends AbstractMessage<T> {

	private static final long serialVersionUID = -8441978966737291513L;
	
	public SystemOperation() {}
	
	public SystemOperation(T payload) {
		super(payload);
	}
	
	public abstract void execute(Connection conn);
	
	@Override
	public void multiplex(Multiplexer mux) {
		// default is no op
	}
}
