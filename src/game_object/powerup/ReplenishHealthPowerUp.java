package game_object.powerup;

import java.util.List;

import game_object.character.IUpgrader;
import game_object.core.Dimension;
import game_object.core.Position;

public class ReplenishHealthPowerUp extends AbstractPowerUp {

	public ReplenishHealthPowerUp(Position position, Dimension dimension, List<String> imagePaths) {
		super(position, dimension, imagePaths);
	}

	@Override
	public void affect(IUpgrader u) {
		u.replenishHealth();
	}

}
