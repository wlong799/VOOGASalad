package network.messages;

import network.Connection;
import network.client.Multiplexer;

/**
 * Special type of message that contains no payload but
 * an operation to be execute on the counter party
 * @author CharlesXu
 */
public abstract class SystemOperation extends AbstractMessage {

	private static final long serialVersionUID = -8441978966737291513L;

	public SystemOperation() {}
	
	public SystemOperation(String sender) {
		super(sender);
	}
	
	@Override
	public String getPayload() {
		return null;
	}

	@Override
	public void multiplex(Multiplexer mux) {
		//no op
	}
	
	public abstract void execute(Connection conn);
}
