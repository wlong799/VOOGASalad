package game_engine.transition;

import game_object.framework.Game;
import game_object.level.Level;

/**
 * Used on games that only have these three states
 *  that returns a level when a goal is reached
 * 
 * @author Charlie Wang
 */
public abstract class AbstractTransitionManager implements ITransitionManager {

	protected Game myGame;
	protected Level returnLevel;

	public AbstractTransitionManager(Game game) {
		myGame = game;
	}

	public Level readWinStatus(WinStatus ws) {
		if (ws == WinStatus.WON)
			levelWon();
		else if (ws == WinStatus.LOST)
			levelLost();
		else if (ws == WinStatus.GOON)
			levelGoOn();
		
		return returnLevel;
	}

	public void levelWon() {
		returnLevel = levelWonReturn();
	}

	public void levelLost() {
		returnLevel = levelLostReturn();
	}

	public void levelGoOn() {
		returnLevel = levelGoOnReturn();
	}

	public abstract Level levelWonReturn();

	public abstract Level levelLostReturn();

	public abstract Level levelGoOnReturn();

}
