package network.messages.system;

import network.core.Connection;

/**
 * Release the lock on id so subsequent requests to trylock
 * will successfully acquire the lock
 * 
 * @author CharlesXu
 */
public class Unlock extends SystemOperation<Long> {

	private static final long serialVersionUID = -661059034958767563L;
	private static final String STRING_NAME = "Unlock";
	
	/**
	 * Create a Unlock message signed with sender's userName
	 * @param sender the userName of the sender
	 * @param id identifies the object to be unlocked
	 */
	public Unlock(String sender, Long id) {
		super(sender, id, STRING_NAME);
	}

	/**
	 * unlock the object through the connection
	 */
	@Override
	public void execute(Connection conn) {
		conn.unlock(this.getPayload(), this.getSender());
	}

}
