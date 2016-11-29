package goal.position;

import game_object.character.ICharacter;
import game_object.core.Position;
import goal.AbstractGoal;

public abstract class PositionGoal extends AbstractGoal{
	
	protected ICharacter myCharacter;
	protected Position myPos;
	
	public PositionGoal(ICharacter character, Position pos) {
		 myCharacter = character;
		 myPos = pos;
	}
	
}
