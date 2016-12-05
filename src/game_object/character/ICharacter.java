package game_object.character;

import game_object.core.ISprite;
import game_object.weapon.Weapon;
import game_object.weapon.WeaponModel;

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
