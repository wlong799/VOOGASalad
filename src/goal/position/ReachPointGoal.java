package goal.position;

import game_engine.collision.Boundary;
import game_engine.transition.WinStatus;
import game_object.character.ICharacter;
import game_object.core.ISprite;
import goal.GoalType;

public class ReachPointGoal extends PositionGoal {

	public ReachPointGoal(ICharacter character, ISprite sprite) {
		super(character, sprite);
	}

	@Override
	public boolean checkGoal() {
		Boundary charBoundary = new Boundary(myCharacter.getPosition(),myCharacter.getDimension());
		Boundary goalBoundary = new Boundary(myDestinationSprite.getPosition(),myDestinationSprite.getDimension());
		return charBoundary.overlaps(goalBoundary);
	}
	
	@Override
	public final GoalType getGoalType() {
		return GoalType.REACH_POINT;
	}
	
	@Override
	public WinStatus getResult() {
		return WinStatus.WON;
	}

}
