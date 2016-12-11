package game_object.character;

import game_object.block.Block;
import game_object.collision.AttackCollisionStrategy;
import game_object.collision.MotionCollisionStrategy;
import game_object.core.ISprite;
import game_object.weapon.Projectile;
import game_object.weapon.Weapon;

/**
 * Character interface
 * @author Jay
 */
public interface ICharacter extends ISprite, IMortal, IMover, IShooter {

	Weapon getCurrentWeapon();

	void setCurrentWeapon(Weapon currentWeapon);
	
	double getWeaponX();

	void setWeaponDisplacementX(double weaponDisplacementX);

	double getWeaponY();

	void setWeaponDisplacementY(double weaponDisplacementY);
	
	MotionCollisionStrategy<ICharacter, Block> getCollideWithBlockStrategy();
	
	AttackCollisionStrategy<ICharacter, Projectile> getAttackByProjectileStrategy();
	
}
