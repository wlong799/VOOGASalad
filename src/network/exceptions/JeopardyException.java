package network.exceptions;

/**
 * Lost connection to server. Consistency cannot be maintained!
 * @author CharlesXu
 */
public class JeopardyException extends Exception {

	private static final long serialVersionUID = -3226542240801771473L;

	public JeopardyException() {}
	
	public JeopardyException(String msg) {
		super(msg);
	}
}
