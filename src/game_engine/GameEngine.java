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
import game_engine.transition.AbstractTransitionManager;
import game_engine.transition.ITransitionManager;
import game_engine.transition.TransitionManager;
import game_engine.transition.WinStatus;
import game_object.acting.KeyEvent;
import game_object.block.IBlock;
import game_object.block.StaticBlock;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.framework.Game;
import game_object.level.Level;
import game_object.level.TransitionMenu;
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
	private ITransitionManager myTransitionManager;
	private InputController myInputController;

	private double myElapsedTime;

	private Level myCurrentLevel;
	private List<Hero> myHeroes;
	private List<Enemy> myEnemies;
	private List<StaticBlock> myBlocks;

	public GameEngine(Level level) {
		myCurrentLevel = level;
		myPhysicsEngine = new PhysicsEngineWithFriction();
		myCollisionEngine = new CollisionEngine();
		myInputController = new InputController();
		// myTransitionManager = new TransitionManager(game, myCurrentLevel);
		init();
	}

	private void menu() {
		// TODO: pass all the objects in the menu level to the game player team
		// return: flag that its good to go (first level) (tentative)
		// might be dispensable
	}

	@Override
	public void init() {
		setElements(myCurrentLevel);
		draw();
	}

	public void endCheck() {
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

		System.out.println("HELLO?");
		setElapsedTime(elapsedTime);
		executeInput();
		for (Hero h : myHeroes) {
			updateNewParameters(h);
		}
		for (Enemy e : myEnemies) {
			updateNewParameters(e);
		}
		for (IBlock b : myBlocks) {
			updateNewParameters(b);
		}
		myCollisionEngine.checkCollisions(myHeroes, myEnemies, myBlocks);
	}

	private void updateNewParameters(IPhysicsBody body) {
		if (body.getAffectedByPhysics()) {
			Position newPosition = myPhysicsEngine.calculateNewPosition(body, myElapsedTime);
			Velocity newVelocity = myPhysicsEngine.calculateNewVelocity(body, myElapsedTime);
			myPhysicsEngine.updatePositionAndVelocity(newPosition, newVelocity, body);
		}
	}

	@Override
	public void draw() {
		// TODO: pass to game play team to render
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
		System.out.println("set elements");
		myHeroes = level.getHeros();
		myEnemies = level.getEnemies();
		myBlocks = level.getStaticBlocks();
		System.out.println("end set elements");
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
		myTransitionManager = transitionManager;
	}

	public void setParameter(String parameter, double value) {
		myPhysicsEngine.setParameters(parameter, value);
	}

	private void executeInput() {
		myInputController.executeInput();
	}

	public void printOutput() {
		for (Hero h : myHeroes) {
			System.out.println("x = " + h.getPosition().getX() + " ; y = " + h.getPosition().getY());
			System.out.println("vx = " + h.getVelocity().getXVelocity() + " ; vy = " + h.getVelocity().getYVelocity());
		}
		for (Enemy e : myEnemies) {
			System.out.println("x = " + e.getPosition().getX() + " ; y = " + e.getPosition().getY());
			System.out.println("vx = " + e.getVelocity().getXVelocity() + " ; vy = " + e.getVelocity().getYVelocity());
		}
		for (IBlock b : myBlocks) {
			System.out.println("x = " + b.getPosition().getX() + " ; y = " + b.getPosition().getY());
			System.out.println("vx = " + b.getVelocity().getXVelocity() + " ; vy = " + b.getVelocity().getYVelocity());
		}
		System.out.println();
	}

}
