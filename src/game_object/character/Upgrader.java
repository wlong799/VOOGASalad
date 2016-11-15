package game_object.character;

import game_object.weapon.Weapon;

public interface Upgrader {

	// set the character to full health
	void replenishHealth();
	
	// set the weapon of the character to the specified weapon
	void getWeapon(Weapon w);
	
	// increase the speed of the character. 
	// percent is **change** amount. So a percent of 0.1 is 1.1x original speed
	// and a percent of -0.1 is 0.9x original speed (slows down)
	void speedUp(double percent);
	
}
