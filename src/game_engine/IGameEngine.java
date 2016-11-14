package game_engine;

public interface IGameEngine {

	/**
	 * Main game loop: consists of initialization of elements, step by step update,
	 * 	ending conditions check, and image drawing 
	 */
	public abstract void loop();
	
	/**
	 * Reads in the Game object and makes the initial map set up
	 */
	public abstract void init();

	/**
	 * Checks whether the ending condition (either win/lose) has been met
	 */
	public abstract void endCheck();
	
	/**
	 * Closes engine or transit to other levels
	 */
	public abstract void shutdown();

	/**
	 * Update the current map set-up by checking user input or current object parameters
	 * TODO: need help of PhysicsEngine
	 */
	public abstract void update();

	/**
	 * Draw out the images in each update
	 * TODO: decide how to pass to frontend
	 */
	public abstract void draw();

}
