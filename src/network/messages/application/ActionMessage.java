package network.messages.application;

import authoring.share.action.IAction;
import network.client.Multiplexer;
import network.messages.AbstractMessage;
import network.messages.Message;
import network.messages.MessageType;

/**
 * Message that wraps users action including but not limited to 
 * dragging, adding on the authoring environment
 * @author CharlesXu
 */
public class ActionMessage extends AbstractMessage<IAction> {

	private static final long serialVersionUID = -7644957424037670368L;
	
	/**
	 * Create a action message with sender information and action
	 * @param sender userName of the creator of the message
	 * @param action payload wrapped in this message
	 */
	public ActionMessage(String sender, IAction action) {
		super(sender, action);
	}

	/**
	 * Refers to {@link Message#multiplex(Multiplexer mux)}
	 */
	@Override
	public void multiplex(Multiplexer mux) {
		mux.getMessageQueue(MessageType.ACTION).add(this);
	}

}
