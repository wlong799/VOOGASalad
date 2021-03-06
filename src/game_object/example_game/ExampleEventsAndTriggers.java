package game_object.example_game;

import game_object.acting.ActionName;
import game_object.acting.ActionTrigger;
import game_object.acting.KeyEvent;
import game_object.core.ISprite;
import javafx.scene.input.KeyCode;

/**
 * A class showing examples of an actual game. 
 * This class is not supposed to be used directly. 
 * @author Yilun
 *
 */
public class ExampleEventsAndTriggers {

	public static KeyEvent UP_KEY_EVENT;
	public static KeyEvent DOWN_KEY_EVENT;
	public static KeyEvent LEFT_KEY_EVENT;
	public static KeyEvent RIGHT_KEY_EVENT;
	public static KeyEvent SPACE_KEY_EVENT;
	public static KeyEvent ENTER_KEY_EVENT;
	
	public static ISprite MOVABLE_SPRITE;
	
	public static ActionTrigger MOVE_LEFT_TRIGGER;
	public static ActionTrigger MOVE_RIGHT_TRIGGER;
	public static ActionTrigger FLY_UP_TRIGGER;
	public static ActionTrigger FLY_DOWN_TRIGGER;
	public static ActionTrigger JUMP_TRIGGER;
	public static ActionTrigger SHOOT_TRIGGER;
	
	static {
		UP_KEY_EVENT = new KeyEvent(KeyCode.UP);
		DOWN_KEY_EVENT = new KeyEvent(KeyCode.DOWN);
		LEFT_KEY_EVENT = new KeyEvent(KeyCode.LEFT);
		RIGHT_KEY_EVENT = new KeyEvent(KeyCode.RIGHT);
		SPACE_KEY_EVENT = new KeyEvent(KeyCode.SPACE);
		ENTER_KEY_EVENT = new KeyEvent(KeyCode.ENTER);
		
		MOVE_LEFT_TRIGGER = new ActionTrigger(LEFT_KEY_EVENT, MOVABLE_SPRITE, ActionName.MOVE_LEFT);
		MOVE_RIGHT_TRIGGER = new ActionTrigger(RIGHT_KEY_EVENT, MOVABLE_SPRITE, ActionName.MOVE_RIGHT);
		JUMP_TRIGGER = new ActionTrigger(SPACE_KEY_EVENT, MOVABLE_SPRITE,  ActionName.JUMP);
		SHOOT_TRIGGER = new ActionTrigger( ENTER_KEY_EVENT, MOVABLE_SPRITE, ActionName.SHOOT);
	}
	
}
