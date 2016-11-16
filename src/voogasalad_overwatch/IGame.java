package voogasalad_overwatch;

import java.util.Collection;

/**
 * @author 
 *contains everything that we need for a GameEngine 
 *to run. It contains several Level objects, 
 *and each Level object has some level specific data, 
 *for example sprites and goals.

 */
public interface IGame {
	
	/**
	 * sets the levels
	 * @param index
	 * @param level
	 */
	void setLevel(int index, ILevel level);
	
	/**
	 * @param index
	 * @return level corresponding to the index
	 */
	ILevel getLevel(int index);
	
	/**
	 * sets which sprites should be created at the 
	 * start of the program
	 * @param starts
	 */
	void setStartSprites(Collection<ISprite> starts);
	
	/**
	 * sets which sprites determine the end of a program
	 * @param ends
	 */
	void setEndSprites(Collection<ISprite> ends);
}
