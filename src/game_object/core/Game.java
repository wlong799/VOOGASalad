package game_object.core;

import java.util.ArrayList;
import java.util.List;

import game_object.level.Level;

/**
 * A class representing a game.
 * @author Jay
 */
public class Game {
	
	private List<Level> myLevels;

	private Level myFirstSceneAsLevel;
	//private TransitionMenu myFirstSceneAsMenu;
	
	private Level myCurrentLevel;
	
	public Game() {
		myLevels = new ArrayList<>();
	}
	
	public void addLevel(Level level) {
		myLevels.add(level);
	}
	
	public void removeLevel(Level level) {
		myLevels.remove(level);
	}
	
	public List<Level> getAllLevels() {
		return myLevels;
	}
	
	public void setCurrentLevel(Level currentLevel) {
		myCurrentLevel = currentLevel;
	}
	
	public Level getCurrentLevel() {
		return myCurrentLevel;
	}
	
	/* 
	 * First Scene, it is either a level or a menu. (For now let's just start with a level)
	 * If getFirstSceneAsLevel() returns a non-null instance of Level,
	 * getFirstSceneAsMenu() will be ignored.
	 */
	public void setFirstSceneAsLevel(Level level) {
		myFirstSceneAsLevel = level;
	}
	
	public Level getFirstSceneAsLevel() {
		return myFirstSceneAsLevel;
	}
//	
//	public void setFirstSceneAsMenu(TransitionMenu menu) {
//		myFirstSceneAsMenu = menu;
//	}
//
//	public TransitionMenu getFirstSceneAsMenu() {
//		return myFirstSceneAsMenu;
//	}
	
}
