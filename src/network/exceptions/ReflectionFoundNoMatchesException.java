package network.exceptions;

public class ReflectionFoundNoMatchesException extends Exception {
	
	private static final long serialVersionUID = -4651169886533066108L;

	public ReflectionFoundNoMatchesException() {}
	
	public ReflectionFoundNoMatchesException(String msg) {
		super(msg);
	}

}
