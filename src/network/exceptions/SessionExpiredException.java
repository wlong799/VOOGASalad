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
	
	public SessionExpiredException(String msg) {
		super(msg);
	}
	
	public SessionExpiredException(Throwable cause) {
        super(cause);
    }
}
