package goal.health;

import game_engine.transition.WinStatus;
import game_object.character.ICharacter;

public class NoHealth extends HealthGoal{
	
	public NoHealth(ICharacter hero) {
		super(hero);
		setResult();
	}
	
	@Override
	public boolean checkGoal() {
		return (myCharacter.getCurrentHP() <= 0);
	}

	@Override
	protected void setResult() {
		ws = WinStatus.LOST;
	}

}
