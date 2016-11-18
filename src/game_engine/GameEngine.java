package game_engine;

import java.util.ArrayList;
import java.util.List;

import game_engine.collision.AbstractCollisionEngine;
import game_engine.collision.CollisionEngine;
import game_engine.inputcontroller.InputController;
import game_engine.physics.AbstractPhysicsEngine;
import game_engine.physics.PhysicsEngine;
import game_engine.transition.AbstractTransitionManager;
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
	private AbstractPhysicsEngine myPhysicsEngine;
	private AbstractCollisionEngine myCollisionEngine;
	private AbstractTransitionManager myTransitionManager;
	private InputController myInputController;

	private double myElapsedTime;

	private Level myCurrentLevel;
	private List<Hero> myHeroes;
	private List<Enemy> myEnemies;
	private List<StaticBlock> myBlocks;

	public GameEngine(Level level) {
		myCurrentLevel = level;
		myPhysicsEngine = new PhysicsEngine();
		myCollisionEngine = new CollisionEngine();
		myInputController = new InputController();
		// myTransitionManager = new TransitionManager(game, myCurrentLevel);
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
			myCurrentLevel = myTransitionManager.readWinStatus(ws);
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
	public void update() {
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
		Position newPosition = myPhysicsEngine.calculateNewPosition(body, myElapsedTime);
		Velocity newVelocity = myPhysicsEngine.calculateNewVelocity(body, myElapsedTime);
		myPhysicsEngine.updatePositionAndVelocity(newPosition, newVelocity, body);
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
		myHeroes = level.getHeros();
		myEnemies = level.getEnemies();
		myBlocks = level.getBlocks();
	}

	public void setElapsedTime(double elapsedTime) {
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

}
