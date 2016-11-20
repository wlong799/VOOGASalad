package game_object;

import java.util.Arrays;
import java.util.List;

import game_object.block.StaticBlock;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.constants.GameObjectConstants;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.level.Level;

/**
 * Use this generator to get a Level with some predefined sprites.
 * @author Jay
 */
public class LevelGenerator {
	
	private static final List<String> path = Arrays.asList(GameObjectConstants.RIBBON_PIG_FILE);
	
	/**
	 * A hero in the air, and a big chalk of block as ground.
	 * @return
	 */
	public static Level getTestLevelA() {
		Level level = new Level();
		Hero hero = new Hero(new Position(30, 30), new Dimension(40, 60), path);
		hero.setVelocity(new Velocity(50,0));
		Enemy enemy = new Enemy(new Position(300,400),new Dimension(40, 60), path);
		
		StaticBlock smackDown = new StaticBlock(new Position(340, 50), new Dimension(100, 200), path);
		StaticBlock ground = new StaticBlock(new Position(0, 500), new Dimension(2000, 500), path);
		level.addHero(hero);
		level.addEnemy(enemy);
		level.addStaticBlock(ground);
		level.addStaticBlock(smackDown);
		return level;
	}
	
	/**
	 * A hero in the air but with a xVelocity, and a big chalk of block as ground.
	 */
	public static Level getTestLevelB() {
		Level level = new Level();
		Hero hero = new Hero(new Position(30, 30), new Dimension(40, 60), path);
		hero.setVelocity(new Velocity(50, 0));
		StaticBlock ground = new StaticBlock(new Position(0, 500), new Dimension(2000, 500), path);
		level.addHero(hero);
		level.addStaticBlock(ground);
		return level;
	}
	
	
	
}
