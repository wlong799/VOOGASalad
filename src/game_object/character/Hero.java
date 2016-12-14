package game_object.character;

import java.util.ArrayList;
import java.util.List;

import game_engine.collision.CollisionEngine.CollisionDirection;
import game_object.collision.AttackCollisionStrategy;
import game_object.collision.ICollisionStrategy;
import game_object.collision.MotionCollisionStrategy;
import game_object.constants.DefaultConstants;
import game_object.constants.GameObjectConstants;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.powerup.IPowerUp;
import game_object.simulation.ICollisionBody;
import game_object.weapon.Projectile;
import game_object.weapon.WeaponModel;


public class Hero extends AbstractCharacter implements IUpgrader {

	private static final long serialVersionUID = -3734934429487355104L;
	private List<ICollisionStrategy<Hero, Enemy>> myEnemyStrategyList;
    private AttackCollisionStrategy<Hero, Enemy> myAttackByEnemyCollisionStrategy;
    private MotionCollisionStrategy<Hero, Enemy> myPushByEnemyCollsionStrategy;
    private double myTotalScore;
    

	public Hero (Position position, Dimension dimension, List<String> imagePaths) {
        super(position, dimension, imagePaths);
        myCategoryBitMask = DefaultConstants.HERO_CATEGORY_BIT_MASK;
        myCollisionBitMask =
			DefaultConstants.BLOCK_CATEGORY_BIT_MASK |
		    DefaultConstants.ENEMY_CATEGORY_BIT_MASK |
		    DefaultConstants.PROJECTILE_CATEGORY_BIT_MASK |
		    DefaultConstants.POWER_CATEGORY_BIT_MASK;
        setupDefaultStrategy();
    }

    public double getTotalScore() {
		return myTotalScore;
	}

	public void setTotalScore(double totalScore) {
		myTotalScore = totalScore;
	}
	
	public void incrementScore(double score) {
		myTotalScore += score;
	}
	
	public void incrementScore(Enemy enemy) {
		incrementScore(enemy.getScoreValue());
	}
	
    private void setupDefaultStrategy() {
        myAttackByEnemyCollisionStrategy = new AttackCollisionStrategy<>();
        myPushByEnemyCollsionStrategy = new MotionCollisionStrategy<>();
        myEnemyStrategyList = new ArrayList<>();
        myEnemyStrategyList.add(myAttackByEnemyCollisionStrategy);
        myEnemyStrategyList.add(myPushByEnemyCollsionStrategy);
    	myAttackByEnemyCollisionStrategy.setRightDamage(DefaultConstants.ENEMY_BODY_DAMAGE);
    	myAttackByEnemyCollisionStrategy.setLeftDamage(DefaultConstants.ENEMY_BODY_DAMAGE);
    	myPushByEnemyCollsionStrategy.setHorizontalBounce(true);
    	myPushByEnemyCollsionStrategy.setVerticalBounce(true);
    }
    
    /* Collision Strategies */
    public AttackCollisionStrategy<Hero, Enemy> getAttackByEnemyCollisionStrategy() {
		return myAttackByEnemyCollisionStrategy;
	}
    
    public MotionCollisionStrategy<Hero, Enemy> getPushByEnemyCollsionStrategy() {
		return myPushByEnemyCollsionStrategy;
	}

    /* Upgrader */
    @Override
    public void replenishHealth () {
        setCurrentHP(getMaxHP());
    }

    @Override
    public void obtainWeapon (WeaponModel weaponModel, Dimension dim) {
        setCurrentWeapon(weaponModel.newWeaponInstance(this, dim));
    }

    @Override
    public void speedUp (double percent) {
        setMovingUnit(getMovingUnit() * (1 + percent));
    }
    
    @Override
    public void onCollideWith(ICollisionBody otherBody, CollisionDirection collisionDirection) {
    	otherBody.onCollideWith(this, collisionDirection.opposite());
    }

    @Override
    public void onCollideWith(Enemy e, CollisionDirection collisionDirection) {
    	for (ICollisionStrategy<Hero, Enemy> strategy : myEnemyStrategyList) {
    		if (strategy.isValid()) {
    			strategy.applyCollision(this, e, collisionDirection);
    		}
    	}
    }

    @Override
    public void onCollideWith(IPowerUp p, CollisionDirection collisionDirection) {
        p.affect(this);
    }

    public static Hero generateDefaultHero() {
		final double DEFAULT_X = 100, DEFAULT_Y = 100;
		final double DEFAULT_H = 40, DEFAULT_W = 40;
		ArrayList<String> heroImages = new ArrayList<>();
		heroImages.add(GameObjectConstants.BLUE_SNAIL_FILE);
		return new Hero(
			new Position(DEFAULT_X, DEFAULT_Y),
			new Dimension(DEFAULT_H, DEFAULT_W),
			heroImages
		);
    }

}
