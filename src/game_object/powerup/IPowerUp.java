package game_object.powerup;

import game_object.character.IUpgrader;
import game_object.core.ISprite;

/**
 * A powerup that would affect an upgrader to give it certain extra abilities.
 * @author Jay
 */
public interface IPowerUp extends ISprite {
	
	void affect(IUpgrader upgrader);
	
}
