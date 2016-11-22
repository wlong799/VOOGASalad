package goal.time;

import game_engine.transition.WinStatus;

public class NoTime extends TimeGoal{
	
	public NoTime(double timeLimit) {
		super(timeLimit);
		setResult();
	}
	
	@Override
	public boolean checkGoal() {
		return (myCurrentTime > myLimit);
	}
	
	@Override
	protected void setResult() {
		ws = WinStatus.WON;
	}
	
}
