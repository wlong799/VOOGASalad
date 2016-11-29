package game_object.constants;

import authoring.view.components.Component;
import game_object.GameObjectType;

public class GameObjectConstants {

    public static final String BLUE_SNAIL_LEFT = "img/sprites/blue_snail_left.png";
    public static final String BLUE_SNAIL_RIGHT = "img/sprites/blue_snail_right.png";
    public static final String ELIZA_LEFT = "img/sprites/eliza_left.png";
    public static final String ELIZA_RIGHT = "img/sprites/eliza_right.png";
    public static final String ORANGE_MUSHROOM_FILE = "img/sprites/orange_mushroom.png";
    public static final String RIBBON_PIG_FILE = "img/sprites/ribbon_pig.png";
    public static final String SLIME_FILE = "img/sprites/slime.png";
    public static final String STONE_BLOCK_FILE = "img/sprites/stone_block.png";
    public static final String BUSH_FILE = "img/sprites/bush.png";
    public static final String BRICK_FILE = "img/sprites/brick.png";

    public static final Component BLUE_SNAIL = new Component(GameObjectType.HERO, BLUE_SNAIL_LEFT, BLUE_SNAIL_RIGHT, "Blue Snail", "A blue snail hero.");
    public static final Component ELIZA = new Component(GameObjectType.HERO, ELIZA_LEFT, ELIZA_RIGHT, "Eliza", "Eliza, a hero.");
    public static final Component ORANGE_MUSHROOM = new Component(GameObjectType.HERO, ORANGE_MUSHROOM_FILE, "Orange Mushroom", "An orange mushroom hero.");
    public static final Component RIBBON_PIG = new Component(GameObjectType.HERO, RIBBON_PIG_FILE, "Ribbon Pig", "A ribbon pig hero.");
    public static final Component SLIME = new Component(GameObjectType.HERO, SLIME_FILE, "Slime", "A slime hero.");
    public static final Component STONE_BLOCK = new Component(GameObjectType.STATIC_BLOCK, STONE_BLOCK_FILE, "Stone", "A stony block.");
    public static final Component BUSH = new Component(GameObjectType.STATIC_BLOCK, BUSH_FILE, "Bush", "A bush block.");
    public static final Component BRICK = new Component(GameObjectType.STATIC_BLOCK, BRICK_FILE, "Brick", "A brick block.");

    public static final String UPLOAD = "img/upload.png";
}
