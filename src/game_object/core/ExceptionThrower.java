package game_object.core;

public class ExceptionThrower {

	public static void notYetSupported() {
		throw new IllegalArgumentException("Not supported yet.");
	}
	
	public static void needToBeOverriden() {
		throw new IllegalArgumentException("This method should be overriden in order to be called.");
	}
}
