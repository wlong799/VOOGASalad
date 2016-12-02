package network.messages;

import java.util.logging.Logger;

import network.Connection;
import network.exceptions.MessageCreationFailureException;

public class SessionLease extends SystemOperation<String> {
	
	private static final Logger LOGGER =
			Logger.getLogger( SessionLease.class.getName() );
	private static final long serialVersionUID = -8119096498238633463L;
	private static final String STRING_NAME = "Session Lease";
	
	@Override
	public String toString() {
		return STRING_NAME;
	}

	@Override
	public void execute(Connection conn) {
		conn.setLastActiveMillis(System.currentTimeMillis());
		try {
			conn.send(MessageType.SESSION_LEASE_GRANTED.build());
		} catch (MessageCreationFailureException e) {
			// trusted code but log this just in case
			LOGGER.info("Failed to reply session lease granted");
		}
	}

}
