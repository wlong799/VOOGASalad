package game_engine.physics;

import game_object.core.Position;
import game_object.core.Velocity;
import game_object.simulation.IPhysicsBody;

/**
 * Structure of a physics engine; calculates and updates new position and velocity
 * 
 * @author Charlie Wang
 */
public interface IPhysicsEngine {
	/**
	 * finds new vertical position of body
	 * @param body that physics is calculated on
	 * @param elapsedTime
	 * @return its x position
	 */
	public double calculateNewVerticalPosition(IPhysicsBody body, double elapsedTime);
	/**
	 * finds new vertical velocity of the body
	 * @param body that physics is calculated on
	 * @param elapsedTime
	 * @return its x velocity
	 */
	public double calculateNewVerticalVelocity(IPhysicsBody body, double elapsedTime);
	/**
	 * finds new horizontal position of the body
	 * @param sprite that physics is calculated on
	 * @param elapsedTime
	 * @return its y position
	 */
	public double calculateNewHorizontalPosition(IPhysicsBody sprite, double elapsedTime);
	/**
	 * finds new horizontal velocity of the body
	 * @param sprite that physics is calculated on
	 * @param elapsedTime
	 * @return its y velocity
	 */
	public double calculateNewHorizontalVelocity(IPhysicsBody sprite, double elapsedTime);
	/**
	 * finds new position of the body 
	 * @param body that physics is calculated on
	 * @param elapsedTime
	 * @return a position class with its x and y position
	 */
	public Position calculateNewPosition(IPhysicsBody body, double elapsedTime);
	/**
	 * finds new velocity of the body
	 * @param body that physics is calculated on
	 * @param elapsedTime
	 * @return a velocity class with its x and y velocity
	 */
	public Velocity calculateNewVelocity(IPhysicsBody body, double elapsedTime);
	/**
	 * update both horizontal position and velocity given new x position and x velocity 
	 * @param newx x position
	 * @param newvx x velocity
	 * @param body that position and velocity are update for
	 */
	public void updateHorizontalPositionAndVelocity(double newx, double newvx, IPhysicsBody body);
        /**
         * update both vertical position and velocity given new y position and y velocity 
         * @param newy y position
         * @param newvy y velocity
         * @param body that position and velocity are update for
         */
	public void updateVerticalPositionAndVelocity(double newy, double newvy, IPhysicsBody body);
	/**
	 * update the total position of the body
	 * @param position new position object
	 * @param body to be updated
	 */
	public void updatePosition(Position position, IPhysicsBody body);
	/**
	 * update the total velocity of the body
	 * @param velocity new velocity object
	 * @param body to be updated
	 */
	public void updateVelocity(Velocity velocity, IPhysicsBody body);
	/**
	 * update the total velocity and position of the body
	 * @param newx new x position
	 * @param newvx new x velocity
	 * @param newy new y position
	 * @param newvy new y velocity
	 * @param body to be updated
	 */
	public void updatePositionAndVelocity(double newx, double newvx, double newy, double newvy, IPhysicsBody body);
	/**
	 * update the total velocity and position of the body
	 * @param position new position object
	 * @param velocity new velocity object
	 * @param body to be updated
	 */
	public void updatePositionAndVelocity(Position position, Velocity velocity, IPhysicsBody body);
	/**
	 * sets the given parameter to the given value
	 * @param option the parameter to test
	 * @param value new parameter amount
	 */
	public void setParameters(PhysicsParameterSetOptions option, double value);
	/**
	 * Whether the left key or right key is pressed to ignore friction
	 * @param exist
	 */
	public void setExisted(boolean exist);
}
