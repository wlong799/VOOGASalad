package game_object;

import voogasalad_overwatch.Collision;
import voogasalad_overwatch.ISprite;
import voogasalad_overwatch.Position;

/**
 * Base class for all sprites providing common functionalities.
 * @author Jay
 */
public class AbstractSprite implements ISprite {

	Position myPosition;
	
	@Override
	public void setPosition(Position pos) {
		myPosition = pos;
	}

	@Override
	public Position getPosition() {
		return myPosition;
	}
	
	@Override
	public void setDead(boolean dead) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void collided(ISprite otherSprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHP(double hp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCollisionProperty(Collision coll) {
		// TODO Auto-generated method stub
		
	}

}
