package network.messages.system;

import network.core.Connection;
import network.messages.MessageType;

/**
 * An RPC to call trylock on the server
 * @author CharlesXu
 */
public class Trylock extends SystemOperation<Long> {

	private static final long serialVersionUID = 5043677666500859412L;
	private static final String STRING_NAME = "Trylock";
	
	/**
	 * Create a Trylock message signed with sender's userName
	 * @param sender the userName of the sender
	 * @param id identifies the object to be locked
	 */
	public Trylock(String sender, Long id) {
		super(sender, id, STRING_NAME);
	}

	/**
	 * Try to lock on the object specified by id
	 * Send back the current holder of the lock
	 */
	@Override
	public void execute(Connection conn) {
		String holder = conn.trylock(this.getPayload(), this.getSender());
		conn.send(MessageType.LOCK_RESPONSE
					.build(conn.getUserName(), holder));
	}
}
