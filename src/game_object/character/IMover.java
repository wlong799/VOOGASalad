package game_object.character;


/**
 * An interface representing all the sprites/character that will move
 * (either by MovingEnging(AI) or by key input)
 * @author Jay
 */
public interface IMover {
	void moveLeft();
	void moveRight();
	void moveUp();
	void moveDown();
	void jumpUp();
	int getMaxNumberOfJumps();
	void resetCurrentJumps();
	void setFacingLeft(boolean left);
	boolean isFacingLeft();
}
