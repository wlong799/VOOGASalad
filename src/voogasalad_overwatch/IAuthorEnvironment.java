package voogasalad_overwatch;

/**
 * Visualize all of the setters for every class
 * @author
 *
 */
public interface IAuthorEnvironment {

	void addGame(IGame game);
	
	void setCurrentGame(IGame game);
	
	IGame getCurrentGame();
	
	
	void addLevel(ILevel level);
	
	void setCurrentLevel(ILevel level);
	
	ILevel getCurrentLevel();
	
	void setPhysicsEngine(IPhysicsEngine physicsEngine);
	
	IPhysicsEngine getPhysicsEngine();
	
	void load();
	
	void setLanguage(String lang);
}
