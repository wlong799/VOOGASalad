package game_object.block;

import java.util.List;
import game_engine.collision.CollisionEngine.CollisionDirection;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.constants.DefaultConstants;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.simulation.ICollisionBody;

public class Block extends AbstractBlock {
	
	private static final long serialVersionUID = -7636114925401610656L;

	public Block(Position position, Dimension dimension, List<String> imagePaths) {
		super(position, dimension, imagePaths);
		myCategoryBitMask = DefaultConstants.BLOCK_CATEGORY_BIT_MASK;
		myCollisionBitMask =
				DefaultConstants.HERO_CATEGORY_BIT_MASK |
				DefaultConstants.ENEMY_CATEGORY_BIT_MASK |
				DefaultConstants.HERO_PROJECTILE_CATEGORY_BIT_MASK |
				DefaultConstants.ENEMY_PROJECTILE_CATEGORY_BIT_MASK;
		myAffectedByPhysics = false;
	}

	@Override
    public void onCollideWith(ICollisionBody otherBody, CollisionDirection collisionDirection){
        otherBody.onCollideWith(this, collisionDirection.opposite());
    }
	
    @Override
    public void onCollideWith (Hero h, CollisionDirection collisionDirection) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onCollideWith (Enemy e, CollisionDirection collisionDirection) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onCollideWith (Block b, CollisionDirection collisionDirection) {
        // TODO Auto-generated method stub
        
    }

}
