package goal.time;

import game_engine.transition.WinStatus;
import goal.GoalType;

public class NoTime extends TimeGoal{
	
	public NoTime(double timeLimit) {
		super(timeLimit);
	}
	
	@Override
	public boolean checkGoal() {
		return (myCurrentTime > myLimit);
	}
	
	@Override
	public WinStatus getResult() {
		return WinStatus.WON;
	}

	@Override
	public GoalType getGoalType() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
