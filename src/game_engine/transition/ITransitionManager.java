package game_engine.transition;

import game_object.level.Level;

/**
 * Interface for classes that handles the level transitions of a game
 * 
 * @author Charlie Wang
 */
public interface ITransitionManager {
	
	/**
	 * Actions to take when the winning goal is triggered
	 */
	public void levelWon();
	
	/**
	 * Actions to take when the losing goal is triggered
	 */
	public void levelLost();
	
	/**
	 * Actions to take when neither winning nor losing goal is triggered
	 */
	public void levelGoOn();
	
	public Level readWinStatus(WinStatus ws);
}
