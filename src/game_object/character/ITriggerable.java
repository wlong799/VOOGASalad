package game_object.character;

import java.util.ArrayList;

import game_object.acting.ActionTrigger;

@Deprecated //seems not used.
public interface ITriggerable {

	ArrayList<ActionTrigger> getActionTriggers();
	void setActionTriggers(ArrayList<ActionTrigger> ats);
	
}
