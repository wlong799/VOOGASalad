package game_object.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game_object.constants.DefaultConstants;
import game_object.level.Level;

/**
 * A class representing a game.
 * @author Jay
 */
public class Game {
	
	private List<Level> myLevels;
	private Dimension myScreenSize;

	private Level myFirstSceneAsLevel;
	//private TransitionMenu myFirstSceneAsMenu;
	
	private Level myCurrentLevel;
	
	public Game() {
		myLevels = new ArrayList<>();
		myScreenSize = new Dimension(
			DefaultConstants.GAME_WIDTH,
			DefaultConstants.GAME_HEIGHT
		);
	}
	
	public Dimension getScreenSize() {
		return myScreenSize;
	}
	
	/**
	 * Return false if this level's id conflicts with the existing ones.
	 * @param level
	 * @return true if add succeeds, false otherwise
	 */
	public boolean addLevel(Level level) {
		for (Level oldLevel : myLevels) {
			if (oldLevel.getId().equals(level.getId())) {
				return false;
			}
		}
		myLevels.add(level);
		return true;
	}
	
	public void removeLevel(Level level) {
		myLevels.remove(level);
	}
	
	public List<Level> getAllLevelsReadOnly() {
		return Collections.unmodifiableList(myLevels);
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
