package game_engine;

import java.util.List;

import game_object.acting.KeyEvent;

/**
 * Basic interface for all game engines
 * 
 * @author Charlie Wang
 */
public interface IGameEngine {
	
	/**
	 * Reads in the Game object and makes the initial map set up
	 */
	public void init();
	
	/**
	 * Closes engine or transit to other levels
	 */
	public void shutdown();

	/**
	 * Update the current map set-up by checking user input or current object parameters
	 */
	public void update(double elapsedTime);

	/**
	 * Draw out the images in each update
	 * TODO: decide how to pass to frontend
	 */
	public void draw();
	
	public void setInputList(List<KeyEvent> list);
	
	public void setElapsedTime(double elapsedTime);

	public void setParameter(String parameter, double value);
	
}
