package game_object.powerup;

import game_object.weapon.Weapon;

public interface Upgradable {

	void replenishHealth();
	void getWeapon(Weapon p);
	void speedUp(double percent);
	
}
