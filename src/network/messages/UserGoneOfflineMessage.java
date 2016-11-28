package network.messages;

import network.client.Multiplexer;

public class UserGoneOfflineMessage extends AbstractMessage<String> {

	// TODO cx15 store in the payload which user is gone
	private static final long serialVersionUID = 267564981803032815L;

	public UserGoneOfflineMessage(String text) {
		super(text);
	}
	
	public UserGoneOfflineMessage(String sender, String text) {
		super(sender, text);
	}

	@Override
	public void multiplex(Multiplexer mux) {
		mux.getMessageQueue(MessageType.USER_GONE_OFFLINE).add(this);
	}
}
