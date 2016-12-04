package network.messages;

import network.client.Multiplexer;

/**
 * Message that wraps users communication
 * @author CharlesXu
 */
public class ChatMessage extends AbstractMessage<String> {

	private static final long serialVersionUID = -3340751700831119309L;
	
	/**
	 * Create a chat message with sender information and text
	 * @param sender userName of the creator of the message
	 * @param text payload wrapped in this message
	 */
	public ChatMessage(String sender, String text) {
		super(sender, text);
	}

	/**
	 * Refers to {@link Message#multiplex(Multiplexer mux)}
	 */
	@Override
	public void multiplex(Multiplexer mux) {
		mux.getMessageQueue(MessageType.CHAT).add(this);
	}
}
