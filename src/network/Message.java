package network;

import java.io.Serializable;

/**
 * The universal wrapper for any object as payload to be
 * sent through network
 * @author CharlesXu
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 7187322794430736566L;
	
	private Object payload;
	
	public Message(Object obj) {
		payload = obj;
	}
	
	public Object unravel() {
		return payload;
	}
	
	@Override
	public String toString() {
		return payload.toString();
	}
}
