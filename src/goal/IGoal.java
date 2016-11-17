package goal;

import game_engine.transition.WinStatus;

public interface IGoal {
	
	public boolean checkGoal();
	
	public WinStatus getResult();
}
