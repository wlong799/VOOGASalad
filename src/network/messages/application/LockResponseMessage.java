package network.messages.application;

import network.client.Multiplexer;
import network.messages.AbstractMessage;
import network.messages.Message;
import network.messages.MessageType;

/**
 * A response from the server to trylock request by client, which
 * informs the client the user name of the currently holder of the
 * requested lock
 * 
 * @author CharlesXu
 */
public class LockResponseMessage extends AbstractMessage<String>{

	private static final long serialVersionUID = -716146167354977950L;
	
	/**
	 * Create a LockResponseMessage message signed with sender's userName
	 * @param sender the userName of the sender
	 * @param lockHolder the current holder for the lock requested
	 */
	public LockResponseMessage(String sender, String lockHolder) {
		super(sender, lockHolder);
	}

	/**
	 * Refers to {@link Message#multiplex(Multiplexer mux)}
	 */
	@Override
	public void multiplex(Multiplexer mux) {
		mux.getMessageQueue(MessageType.LOCK_RESPONSE).add(this);
	}

}
