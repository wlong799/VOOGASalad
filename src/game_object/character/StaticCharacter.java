package game_object.character;

import java.util.ArrayList;

/**
 * A base class for all static characters, aka characters that don't move.
 * @author Jay
 */
public abstract class StaticCharacter extends AbstractCharacter {

	public StaticCharacter(double x, double y, ArrayList<String> imgPaths, double maxHP) {
		super(x, y, imgPaths, maxHP);
	}

}
