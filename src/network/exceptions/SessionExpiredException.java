package network.exceptions;

/**
 * Lost connection to server. Consistency cannot be maintained!
 * This exception is thrown when a connection was previously successfully
 * established but is now closed due to network partition or failure. 
 * 
 * @author CharlesXu
 */
public class SessionExpiredException extends RuntimeException {

	private static final long serialVersionUID = -3226542240801771473L;

	public SessionExpiredException() {}
	
	/**
     * Create an exception based on an issue in our code.
     */
	public SessionExpiredException(String msg) {
		super(msg);
	}
	
	/**
     * Create an exception based on a caught exception with a different message.
     */
	public SessionExpiredException(Throwable cause) {
        super(cause);
    }
}
