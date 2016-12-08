package game_object.powerup;

import java.util.List;
import game_engine.collision.CollisionEngine.CollisionDirection;
import game_object.character.IUpgrader;
import game_object.core.AbstractSprite;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.simulation.ICollisionBody;

abstract class AbstractPowerUp extends AbstractSprite implements IPowerUp {
	
	protected AbstractPowerUp(Position position, Dimension dimension, List<String> imagePaths) {
		super(position, dimension, imagePaths);
	}

	
	
}
