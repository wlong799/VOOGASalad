package goal.health;

import game_engine.transition.WinStatus;
import game_object.character.ICharacter;
import goal.GoalType;

public class BeatBoss extends HealthGoal {

	public BeatBoss(ICharacter boss) {
		super(boss);
	}

	@Override
	public boolean checkGoal() {
		return (myCharacter.getCurrentHP() <= 0);
	}

	@Override
	public WinStatus getResult() {
		return WinStatus.WON;
	}
	
	@Override
	public final GoalType getGoalType() {
		return GoalType.BEAT_BOSS;
	}

}
