package network.messages;

import network.core.Connection;

/**
 * An operation that responds to the renewal of session lease.
 * @author CharlesXu
 */
public class SessionLeaseGranted extends SystemOperation<String> {

	private static final long serialVersionUID = 1691395748367532519L;
	private static final String STRING_NAME = "Session Lease Granted";

	public SessionLeaseGranted(String sender) {
		super(sender, STRING_NAME);
	}
	
	/**
	 * Update the last active time of the connection on the other end
	 */
	@Override
	public void execute(Connection conn) {
		conn.setLastActiveMillis(System.currentTimeMillis());
	}
}
