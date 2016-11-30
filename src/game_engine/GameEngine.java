package game_engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import game_engine.collision.AbstractCollisionEngine;
import game_engine.collision.CollisionEngine;
import game_engine.collision.ICollisionEngine;
import game_engine.inputcontroller.InputController;
import game_engine.physics.AbstractPhysicsEngine;
import game_engine.physics.IPhysicsEngine;
import game_engine.physics.PhysicsEngineWithFriction;
import game_engine.physics.PhysicsParameterSetOptions;
import game_engine.transition.WinStatus;
import game_object.acting.KeyEvent;
import game_object.background.Background;
import game_object.character.Hero;
import game_object.core.AbstractSprite;
import game_object.core.ISprite;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.level.Level;
import game_object.simulation.IPhysicsBody;
import game_object.visualization.ISpriteVisualization;
import goal.IGoal;
import goal.time.TimeGoal;

/**
 * Game engine that takes a level as input
 * 
 * @author Charlie Wang
 */
public class GameEngine implements IGameEngine {
	private IPhysicsEngine myPhysicsEngine;
	private ICollisionEngine myCollisionEngine;
	private InputController myInputController;

	private double myElapsedTime;

	private Level myCurrentLevel;
	private List<ISprite> mySprites;
	
	//for suppressing log output
	private boolean logSuppressed = false;

	public GameEngine(Level level) {
		myCurrentLevel = level;
		myPhysicsEngine = new PhysicsEngineWithFriction(myCurrentLevel);
		myCollisionEngine = new CollisionEngine();
		myInputController = new InputController(level);
		init();
	}

	private void init() {
		setElements(myCurrentLevel);
		myCurrentLevel.init();
	}
	
	public void suppressLogDebug() {
		logSuppressed = true;
		myCollisionEngine.suppressLogDebug();
	}

	private void endCheck() {
		WinStatus ws = checkWin();
		if (ws != WinStatus.GO_ON) {
			if (myCurrentLevel == null) {
				shutdown();
			}
			init();
		}
	}

	@Override
	public void shutdown() {
		// TODO: go to game over screen/close stage
	}

	@Override
	public void update(double elapsedTime) {
		setElapsedTime(elapsedTime);
		executeInput();
		myPhysicsEngine.setExisted(myInputController.getInputExist());
		for (ISprite s : mySprites) {
			updateNewParameters(s);
		}
		myCollisionEngine.checkCollisions(myCurrentLevel.getHeros(), myCurrentLevel.getEnemies(),
				myCurrentLevel.getStaticBlocks());
		updateScrolling();
		endCheck();
	}

	private void updateScrolling() {
		Hero pivotHero = myCurrentLevel.getHeros().get(0);
		if (pivotHero != null) {
			AbstractSprite.getStaticPivotPosition().setX(pivotHero.getPosition().getX());
			AbstractSprite.getStaticPivotPosition().setY(pivotHero.getPosition().getY());
		}
	}
	
	private void updateNewParameters(IPhysicsBody body) {
		if (body.getAffectedByPhysics()) {
//			double newX = myPhysicsEngine.calculateNewHorizontalPosition(body, myElapsedTime);
//			double newY = myPhysicsEngine.calculateNewVerticalPosition(body, myElapsedTime);
//			double newVx = myPhysicsEngine.calculateNewHorizontalVelocity(body, myElapsedTime);
//			double newVy = myPhysicsEngine.calculateNewVerticalVelocity(body, myElapsedTime);
//			myPhysicsEngine.updatePositionAndVelocity(newX, newVx, newY, newVy, body);
			Position newPosition = myPhysicsEngine.calculateNewPosition(body, myElapsedTime);
			Velocity newVelocity = myPhysicsEngine.calculateNewVelocity(body, myElapsedTime);
			myPhysicsEngine.updatePositionAndVelocity(newPosition, newVelocity, body);
		}
	}

	@Override
	public List<ISpriteVisualization> getSprites() {
		return myCurrentLevel.getAllSpriteVisualizations();
	}

	private WinStatus checkWin() {
		List<IGoal> myGoals = new ArrayList<IGoal>();
		for (IGoal g : myGoals) {
			if (g instanceof TimeGoal) {
				((TimeGoal) g).setCurrentTime(0);
			}
			if (g.checkGoal()) {
				return g.getResult();
			}
		}
		return WinStatus.GO_ON;
	}

	private void setElements(Level level) {
		mySprites = level.getAllSprites();
	}

	private void setElapsedTime(double elapsedTime) {
		myElapsedTime = elapsedTime;
	}

	public double getElapsedTime() {
		return myElapsedTime;
	}

	public void setPhysicsEngine(AbstractPhysicsEngine physicsEngine) {
		myPhysicsEngine = physicsEngine;
	}

	public void setCollisionEngine(AbstractCollisionEngine collisionEngine) {
		myCollisionEngine = collisionEngine;
	}

	public void setParameter(PhysicsParameterSetOptions parameter, double value) {
		myPhysicsEngine.setParameters(parameter, value);
	}

	private void executeInput() {
		myInputController.executeInput();
	}

	public void printOutput() {
		if (logSuppressed) return;
		for (ISprite s : mySprites) {
			System.out.println("x = " + s.getPosition().getX() + " ; y = " + s.getPosition().getY());
			System.out.println("vx = " + s.getVelocity().getXVelocity() + " ; vy = " + s.getVelocity().getYVelocity());
		}
		System.out.println();
	}

	@Override
	public void setInputList(Set<KeyEvent> list) {
		myInputController.setInputList(list);
	}

	@Override
	public Background getBackground() {
		return myCurrentLevel.getBackground();
	}

}