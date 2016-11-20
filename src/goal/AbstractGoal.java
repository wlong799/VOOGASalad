package goal;

import game_engine.transition.WinStatus;

public abstract class AbstractGoal implements IGoal{
	protected WinStatus ws;
	
	public abstract boolean checkGoal();
	
	public WinStatus getResult() {
		return ws;
	}
	
	protected abstract void setResult();
}
