package game_object.powerup;

import java.util.ArrayList;

import game_object.character.Upgrader;

public class ReplenishHealthPowerUp extends PowerUp {

	public ReplenishHealthPowerUp(double x, double y, ArrayList<String> imgPaths) {
		super(x, y, imgPaths);
	}

	@Override
	public void affect(Upgrader u) {
		u.replenishHealth();
	}

	
	
}
