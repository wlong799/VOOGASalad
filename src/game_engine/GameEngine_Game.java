package game_engine;

import java.util.List;
import java.util.Random;
import java.util.Set;

import game_engine.collision.CollisionEngine;
import game_engine.collision.ICollisionEngine;
import game_engine.inputcontroller.InputController;
import game_engine.physics.IPhysicsEngine;
import game_engine.physics.PhysicsEngineWithFriction;
import game_engine.physics.PhysicsParameterSetOptions;
import game_engine.transition.ITransitionManager;
import game_engine.transition.TransitionManager;
import game_engine.transition.WinStatus;
import game_object.acting.Event;
import game_object.acting.KeyEvent;
import game_object.background.Background;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.core.AbstractSprite;
import game_object.core.Game;
import game_object.core.ISprite;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.level.Level;
import game_object.simulation.IPhysicsBody;
import game_object.visualization.ISpriteVisualization;
import game_object.weapon.Projectile;
import goal.IGoal;
import goal.time.TimeGoal;

public class GameEngine_Game implements IGameEngine {

	private Level myCurrentLevel;
	private IPhysicsEngine myPhysicsEngine;
	private ICollisionEngine myCollisionEngine;
	private ITransitionManager myTransitionManager;
	private InputController myInputController;
	private double myElapsedTime;
	private double myTotalTime;
	private int myFPS;
	private boolean logSuppressed = false;
	private Random myRandom;

	public GameEngine_Game(Game game) {
		myRandom = new Random();
		myCurrentLevel = game.getAllLevelsReadOnly().get(0);
		init();
		game.setCurrentLevel(myCurrentLevel);
		myCurrentLevel.init();
		myPhysicsEngine = new PhysicsEngineWithFriction(myCurrentLevel);
		myCollisionEngine = new CollisionEngine();
		myInputController = new InputController(game);
		myTransitionManager = new TransitionManager(game, myCurrentLevel);
		myFPS = game.getFPS();
		myTotalTime = 0;
	}

	public void suppressLogDebug() {
		logSuppressed = true;
		myCollisionEngine.suppressLogDebug();
	}

	private void init() {
		// setElements(myCurrentLevel);
		myCurrentLevel.init();
	}

	@Override
	public void shutdown() {
		//TODO
		return;
	}

	@Override
	public void update(double elapsedTime) {
		updateTime();
		setElapsedTime(elapsedTime);
		executeInput();
		for (ISprite s : myCurrentLevel.getAllSprites()) {
			updateNewParameters(s);
		}
		// System.out.println(myCurrentLevel.getAllSpriteVisualizations().size());
		if (!logSuppressed) {
			System.out.println(myCurrentLevel.getHeros().get(0));
		}
		myCollisionEngine.checkCollisions(myCurrentLevel.getAllSprites()
		// myCurrentLevel.getProjectiles(),
		);
		updateScrolling();
		endCheck();
	}

	public void updateTime() {
		myTotalTime += 1.0 / myFPS;
	}

	@Override
	public void setInputList(Set<Event> list) {
		myInputController.setInputList(list);
	}

	@Override
	public void setParameter(PhysicsParameterSetOptions option, double value) {
		myPhysicsEngine.setParameters(option, value);
	}

	private void updateScrolling() {
		Hero pivotHero = myCurrentLevel.getHeros().get(0);
		if (pivotHero != null) {
			AbstractSprite.getStaticPivotPosition().setX(pivotHero.getPosition().getX());
			AbstractSprite.getStaticPivotPosition().setY(pivotHero.getPosition().getY());
		}
	}

	private void updateNewParameters(IPhysicsBody body) {
		Position newPosition = myPhysicsEngine.calculateNewPosition(body, myElapsedTime);
		Velocity newVelocity = myPhysicsEngine.calculateNewVelocity(body, myElapsedTime);
		myPhysicsEngine.updatePositionAndVelocity(newPosition, newVelocity, body);
	}

	private void endCheck() {
		WinStatus ws = checkWin();
		if (ws != WinStatus.GO_ON) {
			if (!logSuppressed) {
				System.out.println("transition");
				System.out.println(myCurrentLevel);
				System.out.println(myCurrentLevel);
			}

			myCurrentLevel = myTransitionManager.readWinStatus(ws);
			myPhysicsEngine.setLevel(myCurrentLevel);

			if (myCurrentLevel == null) {
				shutdown();
			}
			init();
		}
	}

	private WinStatus checkWin() {
		List<IGoal> myGoals = myCurrentLevel.getAllGoals();
		for (IGoal g : myGoals) {
			if (g instanceof TimeGoal) {
				((TimeGoal) g).setCurrentTime(myTotalTime);
			}

			if (g.checkGoal()) {
				return g.getResult();
			}
		}
		return WinStatus.GO_ON;
	}

	private void setElapsedTime(double elapsedTime) {
		myElapsedTime = elapsedTime;
	}

	private void executeInput() {
		myInputController.executeInput();
	}

	@Override
	public Background getBackground() {
		return myCurrentLevel.getBackground();
	}
	
	@Override
	public List<ISpriteVisualization> getSprites() {
		return myCurrentLevel.getAllSpriteVisualizations();
	}

}
