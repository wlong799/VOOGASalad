package goal.time;

import goal.AbstractGoal;

public abstract class TimeGoal extends AbstractGoal{
	
	double myLimit;
	double myCurrentTime;
	
	public TimeGoal(double timeLimit) {
		 myLimit = timeLimit;
	}

	public void setCurrentTime(double currentTime) {
		myCurrentTime = currentTime;
	}

}
