package network.messages;

import network.core.Connection;

public class SessionLeaseGranted extends SystemOperation<String> {

	private static final long serialVersionUID = 1691395748367532519L;
	private static final String STRING_NAME = "Session Lease Granted";
	
	@Override
	public String toString() {
		return STRING_NAME;
	}

	@Override
	public void execute(Connection conn) {
		conn.setLastActiveMillis(System.currentTimeMillis());
	}
}
