package game_object.character;

import game_object.core.ISprite;
import game_object.weapon.Weapon;

/**
 * Character interface
 * @author Jay
 */
public interface ICharacter extends ISprite, IMortal, IMover {

	Weapon getCurrentWeapon();

	void setCurrentWeapon(Weapon currentWeapon);
	
}
