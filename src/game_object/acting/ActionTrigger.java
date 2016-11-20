package game_object.acting;

import game_object.core.ExceptionThrower;
import game_object.core.ISprite;

/**
 * An action trigger that associates an event with a sprite's action.
 * @author Yilun, Jay
 *
 */
public class ActionTrigger {

	private Event myEvent;
	private final ISprite mySprite;
	private final ActionName myActionName;
	
	public ActionTrigger(Event event, ISprite sprite, ActionName actionName) {
		if (event == null || sprite == null || actionName == null) {
			ExceptionThrower.illegalArgs("event, sprite, and actionName cannot be null");
		}
		myEvent = event;
		mySprite = sprite;
		myActionName = actionName;
	}
	
	public void setEvent(Event event) {
		myEvent = event;
	}
	
	public Event getEvent() {
		return myEvent;
	}
	
	public ISprite getSprite() {
		return mySprite;
	}
	
	public ActionName getActionName() {
		return myActionName;
	}
	
}
