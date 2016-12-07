package game_object.character;

import java.util.List;

import game_object.constants.DefaultConstants;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.simulation.ICollisionBody;

public class Enemy extends AbstractCharacter {

	// damage this enemy does to the hero if directly collided.
	private double myBodyDamage = DefaultConstants.ENEMY_BODY_DAMAGE;
	
	public Enemy(Position position, Dimension dimension, List<String> imagePaths) {
		super(position, dimension, imagePaths);
		myCategoryBitMask = DefaultConstants.ENEMY_CATEGORY_BIT_MASK;
		myCollisionBitMask =
			DefaultConstants.HERO_CATEGORY_BIT_MASK |
			DefaultConstants.BLOCK_CATEGORY_BIT_MASK | 
			DefaultConstants.PROJECTILE_CATEGORY_BIT_MASK;
	}

	public void setBodyDamage(double bodyDamage) {
		myBodyDamage = bodyDamage;
	}
	
	public double getBodyDamage() {
		return myBodyDamage;
	}

	/* ICollisionBody Getter Implementations */
	@Override
	public void onCollideWith(ICollisionBody otherSprite) {
		getVelocity().setXVelocity(10);
	}
	
}
