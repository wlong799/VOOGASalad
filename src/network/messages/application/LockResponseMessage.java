package network.messages.application;

import network.client.Multiplexer;
import network.messages.AbstractMessage;
import network.messages.MessageType;


// TODO cx15 jdoc
public class LockResponseMessage extends AbstractMessage<String>{

	private static final long serialVersionUID = -716146167354977950L;
	
	public LockResponseMessage(String sender, String lockHolder) {
		super(sender, lockHolder);
	}

	@Override
	public void multiplex(Multiplexer mux) {
		mux.getMessageQueue(MessageType.LOCK_RESPONSE).add(this);
	}

}
