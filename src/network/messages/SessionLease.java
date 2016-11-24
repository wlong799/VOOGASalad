package network.messages;

import network.Connection;

public class SessionLease extends SystemOperation<String> {
	
	private static final long serialVersionUID = -8119096498238633463L;
	private static final String STRING_NAME = "Session Lease";
	
	@Override
	public String toString() {
		return STRING_NAME;
	}

	@Override
	public void execute(Connection conn) {
		// TODO cx15 reset timer
	}

}
