package game_object.powerup;

import java.util.List;

import game_engine.collision.CollisionEngine.CollisionDirection;
import game_object.character.Hero;
import game_object.constants.DefaultConstants;
import game_object.core.AbstractSprite;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.simulation.ICollisionBody;

abstract class AbstractPowerUp extends AbstractSprite implements IPowerUp {
	
	private static final long serialVersionUID = -7945452345477161682L;

	protected AbstractPowerUp(Position position, Dimension dimension, List<String> imagePaths) {
		super(position, dimension, imagePaths);
		myCategoryBitMask = DefaultConstants.POWER_CATEGORY_BIT_MASK;
		myCollisionBitMask = DefaultConstants.HERO_CATEGORY_BIT_MASK;
	}
	
	@Override
	public void onCollideWith(ICollisionBody otherBody, CollisionDirection collisionDirection) {
		otherBody.onCollideWith(this, collisionDirection);
	}

	@Override
	public void onCollideWith(Hero h, CollisionDirection collisionDirection) {
		setValid(false);
	}
}
