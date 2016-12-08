package game_object.character;

import game_object.core.ISprite;
import game_object.weapon.Weapon;

/**
 * Character interface
 * @author Jay
 */
public interface ICharacter extends ISprite, IMortal, IMover, IShooter {

	Weapon getCurrentWeapon();

	void setCurrentWeapon(Weapon currentWeapon);
	
	double getWeaponDisplacementX();

	void setWeaponDisplacementX(double weaponDisplacementX);

	double getWeaponDisplacementY();

	void setWeaponDisplacementY(double weaponDisplacementY);
	
}
