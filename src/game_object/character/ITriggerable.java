package game_object.character;

import java.util.ArrayList;

import game_object.acting.ActionTrigger;

public interface ITriggerable {

	ArrayList<ActionTrigger> getActionTriggers();
	void setActionTriggers(ArrayList<ActionTrigger> ats);
	
}
