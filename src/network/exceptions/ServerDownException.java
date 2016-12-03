package network.exceptions;

/**
 * Server is not listening on the port specified. Cannot establish
 * connection between client and server. 
 * 
 * @author CharlesXu
 */
public class ServerDownException extends RuntimeException {

	private static final long serialVersionUID = 7322465216127304356L;
	
	public ServerDownException() {}
	
	public ServerDownException(String msg) {
		super(msg);
	}
	
	public ServerDownException(Throwable cause) {
        super(cause);
    }
}
