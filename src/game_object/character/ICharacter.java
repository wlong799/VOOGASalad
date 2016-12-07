package game_object.character;

import game_object.core.ISprite;
import game_object.weapon.WeaponSprite;

/**
 * Character interface
 * @author Jay
 */
public interface ICharacter extends ISprite, IMortal, IMover, IShooter {

	WeaponSprite getCurrentWeapon();

	void setCurrentWeapon(WeaponSprite currentWeapon);
	
	double getWeaponDisplacementX();

	void setWeaponDisplacementX(double weaponDisplacementX);

	double getWeaponDisplacementY();

	void setWeaponDisplacementY(double weaponDisplacementY);
	
}
