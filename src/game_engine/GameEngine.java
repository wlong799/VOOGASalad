package game_engine;

import java.util.ArrayList;
import java.util.List;

import game_engine.collision.AbstractCollisionEngine;
import game_engine.collision.CollisionEngine;
import game_engine.collision.ICollisionEngine;
import game_engine.inputcontroller.InputController;
import game_engine.physics.AbstractPhysicsEngine;
import game_engine.physics.IPhysicsEngine;
import game_engine.physics.PhysicsEngine;
import game_engine.physics.PhysicsEngineWithFriction;
import game_engine.physics.PhysicsParameterSetOptions;
import game_engine.transition.AbstractTransitionManager;
//import game_engine.transition.ITransitionManager;
import game_engine.transition.WinStatus;
import game_object.acting.KeyEvent;
import game_object.core.ISprite;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.level.Level;
import game_object.simulation.IPhysicsBody;
import goal.IGoal;
import goal.time.TimeGoal;

/**
 * 
 * @author Charlie Wang
 */
public class GameEngine implements IGameEngine {
	private IPhysicsEngine myPhysicsEngine;
	private ICollisionEngine myCollisionEngine;
	//private ITransitionManager myTransitionManager;
	private InputController myInputController;

	private double myElapsedTime;

	private Level myCurrentLevel;
	private List<ISprite> mySprites;
	// private List<Hero> myHeroes;
	// private List<Enemy> myEnemies;
	// private List<StaticBlock> myBlocks;

	public GameEngine(Level level) {
		myCurrentLevel = level;
		myPhysicsEngine = new PhysicsEngineWithFriction();
		myCollisionEngine = new CollisionEngine();
		myInputController = new InputController(level);
		// myTransitionManager = new TransitionManager(game, myCurrentLevel);
		init();
	}

//	private void menu() {
//		// TODO: pass all the objects in the menu level to the game player team
//		// return: flag that its good to go (first level) (tentative)
//		// might be dispensable
//	}

	@Override
	public void init() {
		setElements(myCurrentLevel);
	}

	private void endCheck() {
		WinStatus ws = checkWin();
		if (ws != WinStatus.GOON) {
			// myCurrentLevel = myTransitionManager.readWinStatus(ws);
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
		//printOutput();
		endCheck();
	}

	private void updateNewParameters(IPhysicsBody body) {
		if (body.getAffectedByPhysics()) {
			Position newPosition = myPhysicsEngine.calculateNewPosition(body, myElapsedTime);
			Velocity newVelocity = myPhysicsEngine.calculateNewVelocity(body, myElapsedTime);
			myPhysicsEngine.updatePositionAndVelocity(newPosition, newVelocity, body);
		}
	}

	@Override
	public List<ISprite> getSprites() {
		return mySprites;
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
		return WinStatus.GOON;
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

	public void setInputList(List<KeyEvent> list) {
		myInputController.setInputList(list);
	}

	public void setPhysicsEngine(AbstractPhysicsEngine physicsEngine) {
		myPhysicsEngine = physicsEngine;
	}

	public void setCollisionEngine(AbstractCollisionEngine collisionEngine) {
		myCollisionEngine = collisionEngine;
	}

	public void setTransitionManager(AbstractTransitionManager transitionManager) {
		//myTransitionManager = transitionManager;
	}

	public void setParameter(PhysicsParameterSetOptions parameter, double value) {
		myPhysicsEngine.setParameters(parameter, value);
	}

	private void executeInput() {
		myInputController.executeInput();
	}

	public void printOutput() {
		for (ISprite s : mySprites) {
			System.out.println("x = " + s.getPosition().getX() + " ; y = " + s.getPosition().getY());
			System.out.println("vx = " + s.getVelocity().getXVelocity() + " ; vy = " + s.getVelocity().getYVelocity());
		}
		System.out.println();
	}

}
