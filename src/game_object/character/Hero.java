package game_object.character;

import java.util.List;
import game_object.block.StaticBlock;
import game_object.constants.DefaultConstants;
import game_object.core.Dimension;
import game_object.core.ExceptionThrower;
import game_object.core.Position;
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
                             DefaultConstants.PROJECTILE_CATEGORY_BIT_MASK;
    }

    @Override
    public void shoot () {
        ExceptionThrower.notYetSupported();
    }

    @Override
    public void onCollideWith (ICollisionBody otherBody) {

        if (otherBody.getCategoryBitMask() == DefaultConstants.ENEMY_CATEGORY_BIT_MASK) {
            assert (otherBody instanceof Enemy);
            // Enemy enemy = (Enemy) otherBody;
            getVelocity().setYVelocity(-100);
        }
    }

    /* Upgrader -- Not used for now */
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

}
