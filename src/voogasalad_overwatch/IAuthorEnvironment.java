package voogasalad_overwatch;

/**
 * Supports creating and modifying game date
 * @author
 *
 */
public interface IAuthorEnvironment {

	/**
	 * add a game to the scene that the user
	 * can modify
	 * @param game interface
	 */
	void addGame(IGame game);
	
	/**
	 * Set which game that is open should
	 * be in the front and able for the user
	 * to iteract with
	 * @param game interface
	 */
	void setCurrentGame(IGame game);
	
	/**
	 * Returns the game that the 
	 * user is currently interacting with/
	 * modifying
	 * @return game interface
	 */
	IGame getCurrentGame();
		
	/**
	 * add a certain level to the current game
	 * @param level
	 */
	void addLevel(ILevel level);
	
	/**
	 * set a level to be the one able to
	 * modify and have the user interact
	 * @param level interface
	 */
	void setCurrentLevel(ILevel level);
	
	/**
	 * returns the current level that the user
	 * is interacting with
	 * @return level interface
	 */
	ILevel getCurrentLevel();
	
	/**
	 * define the physics that should influene the
	 * game interactions
	 * @param physicsEngine
	 */
	void setPhysicsEngine(IPhysicsEngine physicsEngine);
	
	/**
	 * returns the current physics that are 
	 * being applied to the level
	 * @return physicsengine interface
	 */
	IPhysicsEngine getPhysicsEngine();
	
	void load();
	
	void setLanguage(String lang);
}
