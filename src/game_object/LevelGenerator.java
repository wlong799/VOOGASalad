package game_object;


import java.util.ArrayList;

import game_object.acting.ActionName;
import game_object.acting.ActionTrigger;
import game_object.acting.KeyEvent;
import game_object.block.StaticBlock;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.constants.GameObjectConstants;
import game_object.core.Dimension;
import game_object.core.Game;
import game_object.core.ImageStyle;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.level.Level;
import goal.AbstractGoal;
import goal.position.ReachPointGoal;
import javafx.scene.input.KeyCode;

/**
 * Use this generator to get a Level with some predefined sprites.
 * @author Jay
 */
public class LevelGenerator {
	
	private static Game testGame = new Game();

	/**
	 * A hero in the air, and a big chalk of block as ground.
	 * The hero can move left, right, jump, (and upon acquiring power-up, can shoot).
	 * @return
	 */

	public static Game getTestGame() {
		Game game = new Game();
		game.addLevel(getTestLevelA());
		game.addLevel(getTestLevelB());
		game.setCurrentLevel(getTestLevelA());
		game.setFirstSceneAsLevel(game.getCurrentLevel());
		return game;
	}
	
	public static Level getTestLevelA() {
		ArrayList<String> heroImages = new ArrayList<>();
		heroImages.add(GameObjectConstants.BLUE_SNAIL_LEFT);
		heroImages.add(GameObjectConstants.BLUE_SNAIL_RIGHT);

		ArrayList<String> enemyImages = new ArrayList<>();
		enemyImages.add(GameObjectConstants.ORANGE_MUSHROOM_FILE);

		ArrayList<String> blockImages = new ArrayList<>();
		blockImages.add(GameObjectConstants.MARIO_GROUND_FILE);

		Level level = new Level(testGame, "TestLevelA");

		level.getLevelDimension().setWidth(800);
		level.getLevelDimension().setHeight(2000);


		Hero hero = new Hero(new Position(165, 100), new Dimension(40, 60), heroImages);
		hero.setVelocity(new Velocity(40, -80));
		hero.setImageStyle(ImageStyle.FIT);

		Enemy enemy = new Enemy(new Position(300,400),new Dimension(40, 60), enemyImages);
		enemy.setImageStyle(ImageStyle.FIT);

		StaticBlock smackDown = new StaticBlock(new Position(340, 50), new Dimension(100, 200), blockImages);
		smackDown.setImageStyle(ImageStyle.TILE);

		StaticBlock ground = new StaticBlock(new Position(0, 500), new Dimension(2000, 500), blockImages);
		ground.setImageStyle(ImageStyle.TILE);
		StaticBlock goalBlock = new StaticBlock(new Position(400,450),new Dimension(50,50),blockImages);

		// NewWeaponPowerUp powerUp = new NewWeaponPowerUp(new Position(), dimension, imagePaths, w)

		level.addSprite(hero);
		//level.addSprite(enemy);
		level.addSprite(ground);
		level.addSprite(smackDown);
		level.addSprite(goalBlock);
		AbstractGoal goal = new ReachPointGoal(hero,new Position(400,450));
		level.getAllGoals().add(goal);
		level.setNextLevel(getTestLevelB());

		KeyEvent leftEvent = new KeyEvent(KeyCode.A);
		KeyEvent rightEvent = new KeyEvent(KeyCode.D);
		KeyEvent spaceBarEvent = new KeyEvent(KeyCode.W);
		KeyEvent shootEvent = new KeyEvent(KeyCode.J);

		level.getAllTriggers().add(new ActionTrigger(leftEvent, hero, ActionName.MOVE_LEFT));
		level.getAllTriggers().add(new ActionTrigger(rightEvent, hero, ActionName.MOVE_RIGHT));
		level.getAllTriggers().add(new ActionTrigger(spaceBarEvent, hero, ActionName.JUMP));
		level.getAllTriggers().add(new ActionTrigger(shootEvent, hero, ActionName.SHOOT));
		level.init();
		return level;
	}

	/**
	 * A hero in the air but with a xVelocity, and a big chalk of block as ground.
	 * The hero can move left, right, and jump.
	 */
	public static Level getTestLevelB() {
		ArrayList<String> heroImages = new ArrayList<>();
		heroImages.add(GameObjectConstants.BLUE_SNAIL_LEFT);
		heroImages.add(GameObjectConstants.BLUE_SNAIL_RIGHT);

		ArrayList<String> blockImages = new ArrayList<>();
		blockImages.add(GameObjectConstants.MARIO_GROUND_FILE);

		Level level = new Level(testGame, "TestLevelB");
		level.getLevelDimension().setWidth(2000);
		level.getLevelDimension().setHeight(800);

		Hero hero = new Hero(new Position(30, 30), new Dimension(40, 60), heroImages);
		hero.setVelocity(new Velocity(50, 0));
		hero.setImageStyle(ImageStyle.FIT);

		StaticBlock ground = new StaticBlock(new Position(0, 500), new Dimension(2000, 200), blockImages);
		ground.setImageStyle(ImageStyle.TILE);

		level.addSprite(hero);
		level.addSprite(ground);

		KeyEvent leftEvent = new KeyEvent(KeyCode.A);
		KeyEvent rightEvent = new KeyEvent(KeyCode.D);
		KeyEvent spaceBarEvent = new KeyEvent(KeyCode.W);

		level.getAllTriggers().add(new ActionTrigger(leftEvent, hero, ActionName.MOVE_LEFT));
		level.getAllTriggers().add(new ActionTrigger(rightEvent, hero, ActionName.MOVE_RIGHT));
		level.getAllTriggers().add(new ActionTrigger(spaceBarEvent, hero, ActionName.JUMP));
		level.init();
		return level;
	}
	
}
