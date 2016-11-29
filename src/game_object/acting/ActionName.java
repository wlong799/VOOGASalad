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
	
	public static ActionName getActionWithName(String s) {
		for (ActionName action : ActionName.values()) {
			if (action.toString().equals(s)) {
				return action;
			}
		}
		return null;
	}
	
}
