package goal.health;

import game_engine.transition.WinStatus;
import game_object.character.ICharacter;

public class BeatBoss extends HealthGoal {

	public BeatBoss(ICharacter boss) {
		super(boss);
		setResult();
	}

	@Override
	public boolean checkGoal() {
		return (myCharacter.getCurrentHP() <= 0);
	}

	@Override
	public void setResult() {
		ws = WinStatus.WON;
	}

}
