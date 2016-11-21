package goal;

import game_engine.transition.WinStatus;

/**
 * interface for a goal of a level
 * 
 * @author Charlie Wang
 */
public interface IGoal {
	
	public boolean checkGoal();
	
	public WinStatus getResult();
}
