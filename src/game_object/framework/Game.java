package game_object.framework;

import java.util.ArrayList;
import java.util.List;

import game_object.level.Level;
import game_object.level.TransitionMenu;

/**
 * A class representing a game.
 * @author Jay
 */
public class Game {
	
	private List<Level> myLevels;

	private Level myFirstSceneAsLevel;
	private TransitionMenu myFirstSceneAsMenu;
	
	public Game() {
		myLevels = new ArrayList<>();
	}
	
	public void addLevel(Level level) {
		myLevels.add(level);
	}
	
	public void removeLevel(Level level) {
		myLevels.remove(level);
	}
	
	/* 
	 * First Scene, it is either a level or a menu.
	 * If getFirstSceneAsLevel() returns a non-null instance of Level,
	 * getFirstSceneAsMenu() will be ignored.
	 */
	public void setFirstSceneAsLevel(Level level) {
		myFirstSceneAsLevel = level;
	}
	
	public Level getFirstSceneAsLevel() {
		return myFirstSceneAsLevel;
	}
	
	public void setFirstSceneAsMenu(TransitionMenu menu) {
		myFirstSceneAsMenu = menu;
	}

	public TransitionMenu getFirstSceneAsMenu() {
		return myFirstSceneAsMenu;
	}
	
}
