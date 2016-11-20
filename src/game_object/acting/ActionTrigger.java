package game_object.acting;

import game_object.core.ISprite;

public class ActionTrigger {

	ISprite mySprite;
	Event myEvent;
	String myActionName;
	
	public ActionTrigger(ISprite s, Event e, String ae) {
		mySprite = s;
		myEvent = e;
		myActionName = ae;
	}
	
}
