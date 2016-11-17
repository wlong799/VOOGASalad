package game_object.powerup;

import java.util.ArrayList;

import game_object.character.IUpgrader;

public class SpeedUpPowerUp extends PowerUp {

	private double mySpeedUpFactor;
	
	public SpeedUpPowerUp(double x, double y, ArrayList<String> imgPaths, double factor) {
		super(x, y, imgPaths);
		mySpeedUpFactor = factor;
	}

	@Override
	public void affect(IUpgrader u) {
		u.speedUp(mySpeedUpFactor);
	}
	
}
