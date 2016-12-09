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
	boolean isFacingLeft();
	boolean isJumping();
	void setJumping(boolean jumping);
	boolean isTempJumping();
	void setTempJumping(boolean shooting);
	boolean isShooting();
	void setShooting(boolean jumping);
	boolean isTempShooting();
	void setTempShooting(boolean shooting);
}
