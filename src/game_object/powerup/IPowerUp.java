package game_object.powerup;

import game_object.character.IUpgrader;

/**
 * A powerup that would affect an upgrader to give it certain extra abilities.
 * @author Jay
 */
public interface IPowerUp {
	
	void affect(IUpgrader upgrader);
	
}
