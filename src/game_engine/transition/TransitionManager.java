package game_engine.transition;

import game_object.framework.Game;
import game_object.level.Level;

/**
 * Handles level transitions: the most basic rule -- go to the next level
 * 
 * @author Charlie Wang
 */
public class TransitionManager extends AbstractTransitionManager {

	Level myLevel;

	public TransitionManager(Game game, Level currentLevel) {
		super(game);
		myLevel = currentLevel;
	}

	@Override
	public Level levelWonReturn() {
	    System.out.println(myLevel.getNextLevel());
	    myLevel = myLevel.getNextLevel();
	    System.out.println(myLevel.getNextLevel());
	    myGame.setCurrentLevel(myLevel);
	    return myLevel;
		//return myLevel = myLevel.getNextLevel();
	}

	@Override
	public Level levelLostReturn() {
		Level level = myLevel;
		while (level!=null) {
			level=level.getNextLevel();
		}
		return level;
	}

	@Override
	public Level levelGoOnReturn() {
		return null;
	}

}
