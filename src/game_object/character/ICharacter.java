package game_object.character;

import game_object.simulation.ICollisionBody;
import game_object.simulation.IPhysicsBody;
import game_object.weapon.Weapon;

/**
 * Character interface
 * @author Jay
 */
public interface ICharacter extends ICollisionBody, IPhysicsBody, IMortal {

	void setMaxHP(int maxHP);
	
	double getMaxHP();
	
	void setCurrentHP(double currentHP);
	
	double getCurrentHP();
	
	Weapon getCurrentWeapon();

	void setCurrentWeapon(Weapon currentWeapon);
	
}
