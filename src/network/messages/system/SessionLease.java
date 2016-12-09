package network.messages.system;

import java.util.logging.Logger;

import network.core.Connection;
import network.exceptions.MessageCreationFailureException;
import network.messages.MessageType;

/**
 * A renewal request for session lease. 
 * @author CharlesXu
 */
public class SessionLease extends SystemOperation<String> {
	
	private static final Logger LOGGER =
			Logger.getLogger( SessionLease.class.getName() );
	private static final long serialVersionUID = -8119096498238633463L;
	private static final String STRING_NAME = "Session Lease";

	public SessionLease(String sender) {
		super(sender, STRING_NAME);
	}
	
	/**
	 * Grant the renewal upon receipt of the request
	 */
	@Override
	public void execute(Connection conn) {
		conn.setLastActiveMillis(System.currentTimeMillis());
		try {
			conn.send(MessageType.SESSION_LEASE_GRANTED.build(conn.getUserName()));
		} catch (MessageCreationFailureException e) {
			// trusted code but log this just in case
			LOGGER.info("Failed to reply session lease granted");
		}
	}

}
