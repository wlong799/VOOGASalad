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
	
	double getWeaponX();

	void setWeaponDisplacementX(double weaponDisplacementX);

	double getWeaponY();

	void setWeaponDisplacementY(double weaponDisplacementY);
	
}
