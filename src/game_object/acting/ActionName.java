package game_object.acting;

public enum ActionName {

	MOVE_LEFT ("Move Left"),
	MOVE_RIGHT ("Move Right"),
	JUMP ("Jump"),
	SHOOT ("Shoot");
	
	private final String myName;
	private ActionName(String name) {
		myName = name;
	}
	
	@Override
	public String toString() {
		return myName;
	}
}
