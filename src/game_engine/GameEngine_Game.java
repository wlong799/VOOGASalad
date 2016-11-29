package game_engine;

import java.util.ArrayList;
import java.util.List;

import game_engine.collision.CollisionEngine;
import game_engine.collision.ICollisionEngine;
import game_engine.inputcontroller.InputController;
import game_engine.physics.IPhysicsEngine;
import game_engine.physics.PhysicsEngineWithFriction;
import game_engine.physics.PhysicsParameterSetOptions;
import game_engine.transition.ITransitionManager;
import game_engine.transition.TransitionManager;
import game_engine.transition.WinStatus;
import game_object.acting.KeyEvent;
import game_object.core.ISprite;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.framework.Game;
import game_object.level.Level;
import game_object.simulation.IPhysicsBody;
import goal.IGoal;
import goal.time.TimeGoal;

public class GameEngine_Game implements IGameEngine {

	private Level myCurrentLevel;
	private IPhysicsEngine myPhysicsEngine;
	private ICollisionEngine myCollisionEngine;
	private ITransitionManager myTransitionManager;
	private InputController myInputController;
	private List<ISprite> mySprites;
	private double myElapsedTime;
	private boolean runFlag = true;
	private int FPS;

	public GameEngine_Game(Game game) {
		myCurrentLevel = game.getFirstSceneAsLevel();
		myPhysicsEngine = new PhysicsEngineWithFriction(myCurrentLevel);
		myCollisionEngine = new CollisionEngine();
		myInputController = new InputController(myCurrentLevel);
		myTransitionManager = new TransitionManager(game, myCurrentLevel);
		FPS = 120;
		myElapsedTime = 1.0 / FPS;
		init();
	}

//	public void run() {
//		while (runFlag == true) {
//			update(myElapsedTime);
//			draw();
//			endCheck();
//		}
//	}

	@Override
	public void init() {
		setElements(myCurrentLevel);
	}

	@Override
	public void shutdown() {
		runFlag = false;
	}

	public void draw() {

	}

	@Override
	public void update(double elapsedTime) {
		setElapsedTime(elapsedTime);
		executeInput();
		for (ISprite s : mySprites) {
			updateNewParameters(s);
		}
		myCollisionEngine.checkCollisions(myCurrentLevel.getHeros(), myCurrentLevel.getEnemies(),
				myCurrentLevel.getStaticBlocks());
	}

	@Override
	public List<ISprite> getSprites() {
		return mySprites;
	}

	@Override
	public void setInputList(List<KeyEvent> list) {
		myInputController.setInputList(list);
	}

	@Override
	public void setParameter(PhysicsParameterSetOptions option, double value) {
		myPhysicsEngine.setParameters(option, value);
	}

	private void updateNewParameters(IPhysicsBody body) {
		if (body.getAffectedByPhysics()) {
			Position newPosition = myPhysicsEngine.calculateNewPosition(body, myElapsedTime);
			Velocity newVelocity = myPhysicsEngine.calculateNewVelocity(body, myElapsedTime);
			myPhysicsEngine.updatePositionAndVelocity(newPosition, newVelocity, body);
		}
	}

	private void setElements(Level level) {
		mySprites = level.getAllSprites();
	}

	private void endCheck() {
		WinStatus ws = checkWin();
		if (ws != WinStatus.GOON) {
			myCurrentLevel = myTransitionManager.readWinStatus(ws);
			if (myCurrentLevel == null) {
				shutdown();
			}
			init();
		}
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

	private void setElapsedTime(double elapsedTime) {
		myElapsedTime = elapsedTime;
	}

	private void executeInput() {
		myInputController.executeInput();
	}

}
