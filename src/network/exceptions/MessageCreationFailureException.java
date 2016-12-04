package network.exceptions;

/**
 * Fail to create the message as instructed due to unknown message types
 * or parameter mismatch or more than one payload in one message
 * 
 * @author CharlesXu
 */
public class MessageCreationFailureException extends RuntimeException {

	private static final long serialVersionUID = -1969195280505755385L;

	public MessageCreationFailureException() {}
	
	/**
     * Create an exception based on an issue in our code.
     */
	public MessageCreationFailureException(String msg) {
		super(msg);
	}
	
	/**
     * Create an exception based on a caught exception with a different message.
     */
	public MessageCreationFailureException(Throwable cause) {
        super(cause);
    }
	
}
	
