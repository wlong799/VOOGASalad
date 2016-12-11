package network.messages.system;

import network.core.Connection;

/**
 * Allows server to remotely set the starting id on the client as the
 * Recipient of this message.
 * 
 * @author CharlesXu
 */
public class SetStartingID extends SystemOperation<Long> {
	
	private static final long serialVersionUID = -6527612158675736004L;
	private static final String STRING_NAME = "Set Starting ID";
	
	/**
	 * Create a SetStartingID message signed with sender's userName
	 * @param sender userName of the creator of the message
	 * @param id the collision-free id to start counting
	 */
	public SetStartingID(String sender, Long id) {
		super(sender, id, STRING_NAME);
	}

	/**
	 * set the id on the connection of the other end
	 */
	@Override
	public void execute(Connection conn) {
		conn.setStartingSequenceNumber(this.getPayload());
	}

}
