package game_object.character;

import java.util.ArrayList;
import java.util.List;

import game_engine.collision.CollisionEngine.CollisionDirection;
import game_object.block.Block;
import game_object.collision.AttackCollisionStrategy;
import game_object.collision.ICollisionStrategy;
import game_object.collision.MotionCollisionStrategy;
import game_object.constants.DefaultConstants;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.simulation.ICollisionBody;
import game_object.weapon.Projectile;
import game_object.weapon.WeaponModel;

public class Enemy extends AbstractCharacter {

	private static final long serialVersionUID = -6360150217648638907L;
	private static final double DEFAULT_SCORE_VALUE = 100;
	
	private boolean myHasAI = false;
	private List<ICollisionStrategy<Enemy, Hero>> myHeroStrategyList;
    private AttackCollisionStrategy<Enemy, Hero> myAttackByHeroCollisionStrategy;
    private MotionCollisionStrategy<Enemy, Hero> myPushByHeroCollsionStrategy;
    private double myScoreValue; // the value for this enemy
    private boolean myCanShoot;
    private boolean myCanProjectileFollowHero;
    
	public Enemy(Position position, Dimension dimension, List<String> imagePaths) {
		super(position, dimension, imagePaths);
		myCategoryBitMask = DefaultConstants.ENEMY_CATEGORY_BIT_MASK;
		myCollisionBitMask =
			DefaultConstants.HERO_CATEGORY_BIT_MASK |
			DefaultConstants.BLOCK_CATEGORY_BIT_MASK | 
			DefaultConstants.HERO_PROJECTILE_CATEGORY_BIT_MASK;
		setupDefaultStrategy();
		myScoreValue = DEFAULT_SCORE_VALUE;
	}
	
	private void setupDefaultStrategy() {
		myAttackByHeroCollisionStrategy = new AttackCollisionStrategy<>();
	    myPushByHeroCollsionStrategy = new MotionCollisionStrategy<>();
	    myHeroStrategyList = new ArrayList<>();
	    myHeroStrategyList.add(myAttackByHeroCollisionStrategy);
	    myHeroStrategyList.add(myPushByHeroCollsionStrategy);
		myAttackByHeroCollisionStrategy.setTopDamage(DefaultConstants.HERO_BODY_DAMAGE);
		myAttackByHeroCollisionStrategy.setBottomDamage(DefaultConstants.HERO_BODY_DAMAGE);
		myPushByHeroCollsionStrategy.setHorizontalBounce(true);
		myPushByHeroCollsionStrategy.setVerticalBounce(true);
	}

	public void setShoot(boolean shoot) {
		myCanShoot = shoot;
		if (shoot) {
			setCurrentWeapon(
				WeaponModel.generateDefaultWeaponModel().newWeaponInstance(
					this, new Dimension(DefaultConstants.DEFAULT_WEAPON_WIDTH, DefaultConstants.DEFAULT_WEAPON_HEIGHT)
				)
			);
		} else {
			setCurrentWeapon(null);
		}
	}
	
	public boolean canShoot() {
		return myCanShoot;
	}
	
	public void setCanProjectileFollowHero(boolean canProjectileFollowHero) {
		myCanProjectileFollowHero = canProjectileFollowHero;
		setShoot(true);
		getCurrentWeapon().getModel().getProjectileModel().setFollowHero(canProjectileFollowHero);
	}
	
	public boolean getCanProjectileFollowHero() {
		return myCanProjectileFollowHero;
	}
	
	public double getScoreValue() {
		return myScoreValue;
	}

	public void setScoreValue(double scoreValue) {
		myScoreValue = scoreValue;
	}

	
	public void setHasAI(boolean hasAI) {
		myHasAI = hasAI;
	}
	
	public boolean hasAI() {
		return myHasAI;
	}
	
	/* Collision Strategies */
	public AttackCollisionStrategy<Enemy, Hero> getAttackByHeroCollisionStrategy() {
		return myAttackByHeroCollisionStrategy;
	}
	
	public MotionCollisionStrategy<Enemy, Hero> getPushByHeroCollsionStrategy() {
		return myPushByHeroCollsionStrategy;
	}
	    
	/* ICollisionBody Implementations */
	@Override
    public void onCollideWith(ICollisionBody otherBody, CollisionDirection collisionDirection){
        otherBody.onCollideWith(this, collisionDirection.opposite());
    }

    @Override
    public void onCollideWith (Enemy e, CollisionDirection collisionDirection) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onCollideWith (Block b, CollisionDirection collisionDirection) {
        // TODO Auto-generated method stub
        
        if(collisionDirection == CollisionDirection.LEFT || collisionDirection == CollisionDirection.RIGHT){
            getVelocity().setXVelocity(-getVelocity().getXVelocity());
        }
        super.onCollideWith(b, collisionDirection);
    }

    @Override
    public void onCollideWith (Projectile p, CollisionDirection collisionDirection) {
        // TODO Auto-generated method stub
        this.setValid(false);
        // die?
        //p.getModel().
    }
	
    public void onCollideWith(Hero h, CollisionDirection collisionDirection) {
        System.out.println(collisionDirection);
        
    	for (ICollisionStrategy<Enemy, Hero> strategy : myHeroStrategyList) {
    	    System.out.println(strategy.isValid());
    		if (strategy.isValid()) {
    		    
    			strategy.applyCollision(this, h, collisionDirection);
    		}
    	}
    }
	
}
