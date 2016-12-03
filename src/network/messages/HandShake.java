package network.messages;

import network.core.Connection;

public class HandShake extends SystemOperation<String> {

	private static final long serialVersionUID = -3672890055390191151L;
	private static final String STRING_NAME = "Hand Shake";
	
	public HandShake(String userName) {
		super(userName);
	}
	
	@Override
	public String toString() {
		return STRING_NAME;
	}

	@Override
	public void execute(Connection conn) {
		conn.setUserName(getPayload());
	}
}
