package game_engine;

import java.util.List;
import java.util.Set;

import game_engine.physics.PhysicsParameterSetOptions;
import game_object.acting.KeyEvent;
import game_object.background.Background;
import game_object.visualization.ISpriteVisualization;

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
	 * Update the current map set-up by checking user input or current object
	 * parameters
	 */
	public void update(double elapsedTime);

	/**
	 * Draw out the images in each update
	 */
	public List<ISpriteVisualization> getSprites();

	/**
	 * Let the engine know which input is currently in play
	 * 
	 * @param list
	 *            The list of inputs the user is pressing
	 */
	public void setInputList(Set<KeyEvent> list);

	/**
	 * Change a physics parameter
	 * 
	 * @param option
	 *            Which parameter to change (enum)
	 * @param value
	 * 			  the value to set
	 */
	public void setParameter(PhysicsParameterSetOptions option, double value);

	/**
	 * @return the background picture
	 */
	public Background getBackground();

}
