package game_object.acting;

public enum ActionName {

	MOVE_LEFT ("moveLeft"),
	MOVE_RIGHT ("moveLeft"),
	JUMP ("jump"),
	SHOOT ("shoot");
	
	private final String myName;
	private ActionName(String name) {
		myName = name;
	}
	
	@Override
	public String toString() {
		return myName;
	}
}
