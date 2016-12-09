package game_object.character;


/**
 * An interface representing all the sprites/character that will move
 * (either by MovingEnging(AI) or by key input)
 * @author Jay, Charlie
 */
public interface IMover {
	
	double getMovingUnit();
	
	void setMovingUnit(double movingUnit);

	double getJumpingUnit();

	void setJumpingUnit(double jumpingUnit);
	
	void moveLeft();
	
	void moveRight();
	
	void moveUp();
	
	void moveDown();
	
	void jumpUp();
	
	void setMaxNumberOfJumps(int maxNumberOfJumps);
	
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
