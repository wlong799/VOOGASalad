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
	/**
	 * Check if you have won
	 * @param ws
	 * @return the level you need to go to
	 */
	public Level readWinStatus(WinStatus ws);
	
	public void setLevel(Level level);
}
