package network.messages;

import network.Connection;

/**
 * Special type of message to terminal a connection. 
 * It has no payload to deliver.
 * @author CharlesXu
 */
public class Disconnect extends SystemOperation<String> {

	private static final long serialVersionUID = 6061899659650210954L;
	private static final String STRING_NAME = "Disconnect";
	
	@Override
	public String toString() {
		return STRING_NAME;
	}

	@Override
	public void execute(Connection conn) {
		conn.close();
		// TODO cx15 tell everyone else
	}
}
