package game_object.powerup;

import game_object.character.Upgrader;
import game_object.core.AbstractSprite;
import game_object.core.Position;

import java.util.*;

public abstract class PowerUp extends AbstractSprite {
	
	public PowerUp(double x, double y, ArrayList<String> imgPaths) {
		super(new Position(x, y), imgPaths);
	}
	
	public abstract void affect(Upgrader u);
	
}
