package game_object.character;

import java.util.ArrayList;
import java.util.List;
import game_engine.collision.CollisionEngine.CollisionDirection;
import game_object.block.Block;
import game_object.constants.DefaultConstants;
import game_object.constants.GameObjectConstants;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.powerup.IPowerUp;
import game_object.simulation.ICollisionBody;
import game_object.weapon.WeaponModel;


public class Hero extends AbstractCharacter implements IUpgrader {

    private boolean myHasProjectile = false;

    public Hero (Position position, Dimension dimension, List<String> imagePaths) {
        super(position, dimension, imagePaths);
        myCategoryBitMask = DefaultConstants.HERO_CATEGORY_BIT_MASK;
        myCollisionBitMask =
			DefaultConstants.BLOCK_CATEGORY_BIT_MASK |
		    DefaultConstants.ENEMY_CATEGORY_BIT_MASK |
		    DefaultConstants.PROJECTILE_CATEGORY_BIT_MASK |
		    DefaultConstants.POWER_CATEGORY_BIT_MASK;
    }

    @Override
    public void shoot () {
        //ExceptionThrower.notYetSupported();
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

    public void changeSize (double multiplier) {
        myDimension.setHeight(multiplier * myDimension.getHeight());
        myDimension.setWidth(multiplier * myDimension.getWidth());
    }

    @Override
    public void setHasProjectile (boolean hasProjectile) {
        myHasProjectile = hasProjectile;
    }

    @Override
    public boolean getHasProjectile () {
        return myHasProjectile;
    }
    
    @Override
    public void onCollideWith(ICollisionBody otherBody, CollisionDirection collisionDirection) {
    	otherBody.onCollideWith(this, collisionDirection);
    }
    
    @Override
    public void onCollideWith(Hero h, CollisionDirection collisionDirection) {
        
    }

    @Override
    public void onCollideWith(Enemy e, CollisionDirection collisionDirection) {
    	
    }

    
    @Override
    public void onCollideWith(Block b, CollisionDirection collisionDirection) {
        super.onCollideWith(b, collisionDirection);
    }

    @Override
    public void onCollideWith(IPowerUp p, CollisionDirection collisionDirection) {
        p.affect(this);
    }

    public static Hero generateDefaultHero() {
		final double DEFAULT_X = 100, DEFAULT_Y = 100;
		final double DEFAULT_H = 40, DEFAULT_W = 40;
		ArrayList<String> heroImages = new ArrayList<>();
		heroImages.add(GameObjectConstants.BLUE_SNAIL_LEFT);
		heroImages.add(GameObjectConstants.BLUE_SNAIL_RIGHT);
		return new Hero(
			new Position(DEFAULT_X, DEFAULT_Y),
			new Dimension(DEFAULT_H, DEFAULT_W),
			heroImages
		);
    }
}
