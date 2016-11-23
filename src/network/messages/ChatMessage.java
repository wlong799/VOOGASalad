package network.messages;

import network.client.Multiplexer;

public class ChatMessage extends AbstractMessage {

	private static final long serialVersionUID = -3340751700831119309L;
	
	private String payload;
	
	public ChatMessage(String obj) {
		payload = obj;
	}
	
	public ChatMessage(String sender, String obj) {
		super(sender);
		payload = obj;
	}
	
	@Override
	public String getPayload() {
		return payload;
	}
	
	@Override
	public String toString() {
		return payload.toString();
	}

	@Override
	public void multiplex(Multiplexer mux) {
		mux.getMessageQueue(this.getClass()).add(this);
	}
}
