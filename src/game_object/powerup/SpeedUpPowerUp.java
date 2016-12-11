package game_object.powerup;

import java.util.List;

import game_object.character.IUpgrader;
import game_object.core.Dimension;
import game_object.core.Position;

public class SpeedUpPowerUp extends AbstractPowerUp {

	private static final long serialVersionUID = 3197118297575460821L;
	private double mySpeedUpFactor;
	
	public SpeedUpPowerUp(Position position, Dimension dimension, List<String> imagePaths, double speedUpFactor) {
		super(position, dimension, imagePaths);
		mySpeedUpFactor = speedUpFactor;
	}

	@Override
	public void affect(IUpgrader u) {
		u.speedUp(mySpeedUpFactor);
	}
	
}
