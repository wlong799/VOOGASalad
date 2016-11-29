package network.exceptions;

public class MessageCreationFailureException extends Exception {

	private static final long serialVersionUID = -1969195280505755385L;

	public MessageCreationFailureException() {}
	
	public MessageCreationFailureException(String msg) {
		super(msg);
	}
	
}
