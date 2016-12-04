package network.messages;

import network.core.Connection;

/**
 * To tell the other party to terminate this connection. 
 * It has no payload to deliver.
 * 
 * @author CharlesXu
 */
public class Disconnect extends SystemOperation<String> {

	private static final long serialVersionUID = 6061899659650210954L;
	private static final String STRING_NAME = "Disconnect";
	
	public Disconnect(String sender) {
		super(sender, STRING_NAME);
	}

	@Override
	public void execute(Connection conn) {
		conn.close();
	}
}
