package network.messages;

import network.client.Multiplexer;

public class UserGoneOfflineMessage extends AbstractMessage<String> {

	private static final long serialVersionUID = 267564981803032815L;
	private static final String STRING_NAME = "Disconnect";
	
	public UserGoneOfflineMessage(String sender) {
		super(sender, STRING_NAME);
	}

	@Override
	public void multiplex(Multiplexer mux) {
		mux.getMessageQueue(MessageType.USER_GONE_OFFLINE).add(this);
	}
}
