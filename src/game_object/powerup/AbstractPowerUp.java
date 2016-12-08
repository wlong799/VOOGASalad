package game_object.powerup;

import java.util.List;
import game_object.core.AbstractSprite;
import game_object.core.Dimension;
import game_object.core.Position;

abstract class AbstractPowerUp extends AbstractSprite implements IPowerUp {
	
	protected AbstractPowerUp(Position position, Dimension dimension, List<String> imagePaths) {
		super(position, dimension, imagePaths);
	}

}
