package network.messages.system;

import network.core.Connection;

// TODO cx15 jdoc
public class SetStartingID extends SystemOperation<Long> {
	
	private static final long serialVersionUID = -6527612158675736004L;
	private static final String STRING_NAME = "Set Starting ID";
	
	public SetStartingID(String sender, Long id) {
		super(sender, id, STRING_NAME);
	}

	@Override
	public void execute(Connection conn) {
		conn.setStartingSequenceNumber(this.getPayload());
	}

}
