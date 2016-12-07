package goal.position;

import game_object.character.ICharacter;
import game_object.core.ISprite;
import goal.AbstractGoal;

public abstract class PositionGoal extends AbstractGoal{
	
	protected ICharacter myCharacter;
	protected ISprite myDestinationSprite;
	
	public PositionGoal(ICharacter character, ISprite sprite) {
		 myCharacter = character;
		 myDestinationSprite = sprite;
	}
	
}
