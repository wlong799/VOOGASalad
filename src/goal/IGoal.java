package goal;

import game_engine.transition.WinStatus;

/**
 * interface for a goal of a level
 * 
 * @author Charlie Wang, Jay
 */
public interface IGoal {
	
	public GoalType getGoalType();
	
	/**
	 * Check if goal is met.
	 * @return boolean (true indicates met)
	 */
	public boolean checkGoal();
	
	/**
	 * Indicate when this goal is met, what it means to the game.
	 * E.g. if position goal is met, we won. If No health goal is met, it means we lost.
	 * @return
	 */
	public WinStatus getResult();
}
