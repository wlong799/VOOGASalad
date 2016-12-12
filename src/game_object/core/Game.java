package game_object.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game_engine.enemyai.EnemyLevelTypes;
import game_object.constants.DefaultConstants;
import game_object.constants.GameObjectConstants;
import game_object.level.Level;
import game_object.statistics.GameStatistics;

/**
 * A class representing a game.
 * @author Jay
 */
public class Game {

	private final String myId;
	private String myImagePath;
	private String myDescription;
	private List<Level> myLevels;
	private Dimension myScreenSize;
	private int myFPS;
	private GameStatistics myGameStats;
	private EnemyLevelTypes myEnemyDifficulty;

	private Level myFirstSceneAsLevel;
	//private TransitionMenu myFirstSceneAsMenu;

	private Level myCurrentLevel;

	public Game(String id) {
		myId = id;
		myLevels = new ArrayList<>();
		myScreenSize = new Dimension(
			DefaultConstants.GAME_WIDTH,
			DefaultConstants.GAME_HEIGHT
		);
		myFPS = 60;
		myEnemyDifficulty = EnemyLevelTypes.MEDIUM;
		myGameStats = new GameStatistics(this);
		initDefaultGameInfo();
	}

	/* mock */
	private void initDefaultGameInfo() {
		myImagePath = GameObjectConstants.BLUE_SNAIL_FILE;
		myDescription = GameObjectConstants.LOREM_IPSUM;
	}

	/* Game basics */
	public void setEnemyDifficulty(EnemyLevelTypes enemyDifficulty) {
		myEnemyDifficulty = enemyDifficulty;
	}

	public EnemyLevelTypes getEnemyDifficulty() {
		return myEnemyDifficulty;
	}

	public Dimension getScreenSize() {
		return myScreenSize;
	}

	public String getId() {
		return myId;
	}

	public void setDescription(String description) {
		myDescription = description;
	}

	public String getDescription() {
		return myDescription;
	}

	public void setImagePath(String imagePath) {
		myImagePath = imagePath;
	}

	public String getImagePath() {
		return myImagePath;
	}

	/* FPS setting */
	public void setFPS(int fPS) {
		myFPS = fPS;
	}

	public int getFPS() {
		return myFPS;
	}

	/* Game Statistics */
	public GameStatistics getGameStats() {
		return myGameStats;
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

	// NOTE: SHOULD ONLY USE THIS IF ABSOLUTELY NECESSARY! OTHERWISE USE THE OTHER METHODS PROVIDED
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
