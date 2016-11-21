package game_engine.transition;

import game_object.framework.Game;
import game_object.level.Level;

/**
 * Handles level transitions: 
 * 	the most basic rule -- go to the next level
 * 
 * @author Charlie Wang
 */
public class TransitionManager extends AbstractTransitionManager{

	Level myLevel;
	
	public TransitionManager(Game game, Level currentLevel) {
		super(game);
		myLevel = currentLevel;
	}
	
	@Override
	public Level levelWonReturn() {
		return myLevel = myLevel.getNextLevel();
	}

	@Override
	public Level levelLostReturn() {
		return null;
	}

	@Override
	public Level levelGoOnReturn() {
		return null;
	}

}
