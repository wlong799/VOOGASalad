package goal.health;

import game_engine.transition.WinStatus;
import game_object.character.ICharacter;
import goal.GoalType;

public class NoHealth extends HealthGoal{
	
	public NoHealth(ICharacter hero) {
		super(hero);
	}
	
	@Override
	public boolean checkGoal() {
		return (myCharacter.getCurrentHP() <= 0);
	}

	@Override
	public WinStatus getResult() {
		return WinStatus.LOST;
	}
	
	@Override
	public final GoalType getGoalType() {
		return GoalType.NO_HEALTH;
	}

}
