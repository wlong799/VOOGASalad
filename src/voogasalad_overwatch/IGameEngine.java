package voogasalad_overwatch;

/**
 * takes a set of game data and runs the game.
 * At the simplest level, it provides a step() funciton
 * that steps forward by a given elapsed time.
 * This function maps the current state of the game
 * and the user input to the next state of the game.
 * @author 
 *
 */
public interface IGameEngine {

	/**
	 * sets the current level
	 * @param level
	 */
	void seturrentLevel(ILevel level);
	
	/**
	 * sets the current physics which dictate
	 * level interactions
	 * @param physicsEngine
	 */
	void setPhysicsEngine(IPhysicsEngine physicsEngine);
	
	/**
	 * determine how the user interacts with the
	 * game
	 * @param handler
	 */
	void setControlHandler(IControlHandler handler);
	
	/**
	 * start the game running
	 */
	void start();

    /**
     * return all of the current available
     * games
     */
    public IGame getAvailableGames();

}
