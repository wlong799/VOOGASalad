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
	public void levelWon() {
		//myLevel = myLevel.getNextLevel();
	}

	@Override
	public void levelLost() {
		//
	}

	@Override
	public void levelGoOn() {
		return;
	}

}
