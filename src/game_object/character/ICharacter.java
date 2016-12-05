package game_object.character;

import game_object.core.ISprite;
import game_object.weapon.WeaponModel;

/**
 * Character interface
 * @author Jay
 */
public interface ICharacter extends ISprite, IMortal, IMover, IShooter {

	WeaponModel getCurrentWeapon();

	void setCurrentWeapon(WeaponModel currentWeapon);
	
	double getWeaponDisplacementX();

	void setWeaponDisplacementX(double weaponDisplacementX);

	double getWeaponDisplacementY();

	void setWeaponDisplacementY(double weaponDisplacementY);
	
}
