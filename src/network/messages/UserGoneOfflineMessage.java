package network.messages;

import network.client.Multiplexer;

/**
 * Tells other clients about the departure of a client so they could
 * safely reclaim all resources for that client also remove all frontend
 * rendering of that
 * 
 * @author CharlesXu
 */
public class UserGoneOfflineMessage extends AbstractMessage<String> {

	private static final long serialVersionUID = 267564981803032815L;
	private static final String STRING_NAME = "Disconnect";
	
	public UserGoneOfflineMessage(String sender) {
		super(sender, STRING_NAME);
	}

	/**
	 * Refers to {@link Message#multiplex(Multiplexer mux)}
	 */
	@Override
	public void multiplex(Multiplexer mux) {
		mux.getMessageQueue(MessageType.USER_GONE_OFFLINE).add(this);
	}
}
