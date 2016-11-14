package voogasalad_overwatch;

public interface IGameEngine {
	
	void setCurrentLevel(ILevel level);

	void setPhysicsEngine(IPhysicsEngine physicsEngine);

	void step();
}
