package network;


public class Message {
	
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
