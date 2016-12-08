package game_engine.inputcontroller;

import java.util.Set;

import game_object.acting.Event;
import game_object.acting.KeyEvent;
/**
 * 
 * @author 
 *input controller for a character. Checks the inputs that are being pressed and determines what happens to objects because of this
 */
public interface IInputController {
	/**
	 * set the key inputs for the character
	 * @param list
	 */
	public void setInputList(Set<Event> list);
	
	/**
	 * determines what the given input does to the object and executes
	 */
	public void executeInput();
	
	/**
	 * @return whether left / right is pressed
	 */
	public boolean getInputExist();
	
	/**
	 * sets the current time; use for the random shooting of enemies
	 */
	public void setCurrentTime(double time);
}
