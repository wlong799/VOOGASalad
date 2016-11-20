package game_object.constants;

import authoring.view.components.Component;
import game_object.GameObjectType;

public class GameObjectConstants {
	
	public static final String BLUE_SNAIL_FILE = "img/blue_snail.png";
	public static final String ELIZA_FILE = "img/eliza.png";
	public static final String ORANGE_MUSHROOM_FILE = "img/orange_mushroom.png";
	public static final String RIBBON_PIG_FILE = "img/ribbon_pig.png";
	public static final String SLIME_FILE = "img/slime.png";
	public static final String STONE_BLOCK_FILE = "img/stone_block.png";
	
	public static final Component BLUE_SNAIL = new Component(GameObjectType.Hero, "Blue Snail", BLUE_SNAIL_FILE);
	public static final Component ELIZA = new Component(GameObjectType.Hero, "Eliza", ELIZA_FILE);
	public static final Component ORANGE_MUSHROOM = new Component(GameObjectType.Hero, "Orange Mushroom", ORANGE_MUSHROOM_FILE);
	public static final Component RIBBON_PIG = new Component(GameObjectType.Hero, "Ribbon Pig", RIBBON_PIG_FILE);
	public static final Component SLIME = new Component(GameObjectType.Hero, "Slime", SLIME_FILE);
	public static final Component STONE_BLOCK = new Component(GameObjectType.StaticBlock, "Stone Block", STONE_BLOCK_FILE);
	
}
