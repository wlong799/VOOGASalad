package network.messages.system;

import network.core.Connection;

/**
 * The very first message to be sent through a connection once it is established.
 * It is used to exchange configuration settings
 * 
 * @author CharlesXu
 */
public class HandShake extends SystemOperation<String> {

	private static final long serialVersionUID = -3672890055390191151L;
	private static final String STRING_NAME = "Hand Shake";
	private static final String SERVER_PREFIX = "Server of ";
	
	/**
	 * Create hand shake message signed by <tt>userName</tt>
	 * @param userName the sender userName
	 */
	public HandShake(String userName) {
		super(userName, STRING_NAME);
	}

	/**
	 * Set the userName of the connection on the other side
	 */
	@Override
	public void execute(Connection conn) {
		conn.setUserName(SERVER_PREFIX + this.getSender());
	}
}
