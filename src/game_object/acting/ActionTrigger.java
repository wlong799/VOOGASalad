package game_object.acting;

import game_object.core.ISprite;

public class ActionTrigger {

	ISprite mySprite;
	Event myEvent;
	ActionName myActionName;
	
	public ActionTrigger(ISprite sprite, Event event, ActionName actionName) {
		mySprite = sprite;
		myEvent = event;
		myActionName = actionName;
	}
	
}
