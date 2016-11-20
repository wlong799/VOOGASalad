package goal.time;

import goal.AbstractGoal;

public abstract class TimeGoal extends AbstractGoal{
	
	double myLimit;
	double myCurrentTime;
	
	public TimeGoal(double timeLimit) {
		 myLimit = timeLimit;
	}
	
	@Override
	public abstract boolean checkGoal();

	@Override
	protected abstract void setResult();
	
	public void setCurrentTime(double currentTime) {
		myCurrentTime = currentTime;
	}
}
