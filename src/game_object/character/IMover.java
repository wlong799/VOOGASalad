package game_object.character;

import java.util.ArrayList;

import game_object.acting.ActionTrigger;

/**
 * An interface representing all the sprites/character that will move
 * (either by MovingEnging(AI) or by key input)
 * @author Jay
 */
public interface IMover {
	void moveLeft();
	void moveRight();
	void moveUp();
	void moveDown();
	void jumpUp();
	
	ArrayList<ActionTrigger> getActionTriggers();
	void setActionTriggers(ArrayList<ActionTrigger> ats);
	
}
