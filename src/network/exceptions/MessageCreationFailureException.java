package network.exceptions;

/**
 * Fail to create the message as instructed due to unknown message types
 * or parameter mismatch.
 * 
 * @author CharlesXu
 */
public class MessageCreationFailureException extends Exception {

	private static final long serialVersionUID = -1969195280505755385L;

	public MessageCreationFailureException() {}
	
	public MessageCreationFailureException(String msg) {
		super(msg);
	}
	
	public MessageCreationFailureException(Throwable cause) {
        super(cause);
    }
	
}
	
