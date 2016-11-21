package network.exceptions;

/**
 * 
 * @author CharlesXu
 */
public class ServerDownException extends Exception{

	private static final long serialVersionUID = 7322465216127304356L;
	
	public ServerDownException() {}
	
	public ServerDownException(String msg) {
		super(msg);
	}
}
