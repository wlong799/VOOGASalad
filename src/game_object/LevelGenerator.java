package game_object;

import game_object.block.StaticBlock;
import game_object.character.Hero;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.level.Level;

/**
 * Use this generator to get a Level with some predefined sprites.
 * @author Jay
 */
public class LevelGenerator {
	
	/**
	 * A hero in the air, and a big chalk of block as ground.
	 * @return
	 */
	public static Level getTestLevelA() {
		Level level = new Level();
		Hero hero = new Hero(new Position(30, 30), new Dimension(40, 60), null);
		StaticBlock ground = new StaticBlock(new Position(0, 500), new Dimension(2000, 500), null);
		level.addHero(hero);
		level.addStaticBlock(ground);
		return level;
	}
	
	/**
	 * A hero in the air but with a xVelocity, and a big chalk of block as ground.
	 */
	public static Level getTestLevelB() {
		Level level = new Level();
		Hero hero = new Hero(new Position(30, 30), new Dimension(40, 60), null);
		hero.setVelocity(new Velocity(50, 0));
		StaticBlock ground = new StaticBlock(new Position(0, 500), new Dimension(2000, 500), null);
		level.addHero(hero);
		level.addStaticBlock(ground);
		return level;
	}
	
	
	
}
