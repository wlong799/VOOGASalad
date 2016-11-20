package game_object.core;

public class ExceptionThrower {

	public static void notYetSupported() {
		throw new RuntimeException("Not supported yet.");
	}
	
	public static void needToBeOverriden() {
		throw new RuntimeException("This method should be overriden in order to be called.");
	}
	
	public static void illegalArgs(String s) {
		throw new IllegalArgumentException(s);
	}
}
