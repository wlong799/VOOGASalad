package game_object.character;

import java.util.List;
import game_engine.collision.CollisionEngine.CollisionDirection;
import game_object.block.Block;
import game_object.constants.DefaultConstants;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.simulation.ICollisionBody;
import game_object.weapon.Projectile;

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
    public void onCollideWith(ICollisionBody otherBody, CollisionDirection collisionDirection){
        otherBody.onCollideWith(this, collisionDirection);
    }
	
    @Override
    public void onCollideWith (Hero h, CollisionDirection collisionDirection) {
        // TODO Auto-generated method stub
        System.out.println("invalid");
        if(collisionDirection == CollisionDirection.TOP){
           this.setValid(false);
        }
    }

    @Override
    public void onCollideWith (Enemy e, CollisionDirection collisionDirection) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onCollideWith (Block b, CollisionDirection collisionDirection) {
        // TODO Auto-generated method stub
        //super.onCollideWith(b, collisionDirection);
        if(collisionDirection == CollisionDirection.LEFT || collisionDirection == CollisionDirection.RIGHT){
            getVelocity().setXVelocity(-getVelocity().getXVelocity());
        }
    }

    @Override
    public void onCollideWith (Projectile p, CollisionDirection collisionDirection) {
        // TODO Auto-generated method stub
        //this.setValid(false);
        // die?
        //p.getModel().
    }
	
	
}
