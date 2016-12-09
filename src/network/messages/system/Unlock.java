package network.messages.system;

import network.core.Connection;

//TODO cx15 jdoc
public class Unlock extends SystemOperation<Long> {

	private static final long serialVersionUID = -661059034958767563L;
	private static final String STRING_NAME = "Unlock";
	
	public Unlock(String sender, Long id) {
		super(sender, id, STRING_NAME);
	}

	@Override
	public void execute(Connection conn) {
		conn.unlock(this.getPayload(), this.getSender());
	}

}
