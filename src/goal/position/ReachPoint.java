package goal.position;

import game_engine.transition.WinStatus;
import game_object.character.ICharacter;
import game_object.core.Position;

public class ReachPoint extends PositionGoal {

	public ReachPoint(ICharacter character, Position pos) {
		super(character, pos);
		setResult();
	}

	@Override
	public boolean checkGoal() {
		double height = myCharacter.getDimension().getHeight();
		double width = myCharacter.getDimension().getWidth();
		double x = myCharacter.getPosition().getX();
		double y = myCharacter.getPosition().getY();
		return (myPos.getX() < x + width / 2 && 
				myPos.getX() > x - width / 2 && 
				myPos.getY() < y + height / 2 && 
				myPos.getY() > x - height / 2);
	}

	@Override
	protected void setResult() {
		ws = WinStatus.WON;
	}

}
