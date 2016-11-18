package game_object.character;

import game_object.core.ISprite;
import game_object.weapon.Weapon;

/**
 * Character interface
 * @author Jay
 */
public interface ICharacter extends ISprite, IMortal, IMover {

	void setMaxHP(int maxHP);
	
	double getMaxHP();
	
	void setCurrentHP(double currentHP);
	
	double getCurrentHP();
	
	Weapon getCurrentWeapon();

	void setCurrentWeapon(Weapon currentWeapon);
	
}
