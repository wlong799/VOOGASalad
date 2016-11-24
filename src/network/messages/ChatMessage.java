package network.messages;

import network.client.Multiplexer;

/**
 * @author CharlesXu
 */
public class ChatMessage extends AbstractMessage<String> {

	private static final long serialVersionUID = -3340751700831119309L;
	
	public ChatMessage(String text) {
		super(text);
	}
	
	public ChatMessage(String sender, String text) {
		super(sender, text);
	}

	@Override
	public void multiplex(Multiplexer mux) {
		mux.getMessageQueue(MessageType.CHAT).add(this);
	}
}
