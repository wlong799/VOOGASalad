package game_object.constants;

public class DefaultConstants {
	
	public static final double GAME_WIDTH = 900;
	public static final double GAME_HEIGHT = 700;
	public static final double TEST_CONFIGURE_WIDTH = 200;
	public static final double X_SCROLL_THRESHOLD = 400;
	public static final double Y_SCROLL_PERCENT = 4.0 / 7.0;
	
	public static final double MOVING_UNIT = 50;
	public static final double JUMPING_UNIT = 100;
	public static final int MAX_JUMP = 3;
	
	public static final int VOID_CATEGORY_BIT_MASK = 0;
	public static final int HERO_CATEGORY_BIT_MASK = 1 << 0;
	public static final int ENEMY_CATEGORY_BIT_MASK = 1 << 1;
	public static final int BLOCK_CATEGORY_BIT_MASK = 1 << 2;
	public static final int PROJECTILE_CATEGORY_BIT_MASK = 1 << 3;
	public static final int POWER_CATEGORY_BIT_MASK = 1 << 4;
	
	
	public static final double CHARACTER_MAX_HP = 100;
	public static final double ENEMY_BODY_DAMAGE = 5;
	public static final double HERO_BODY_DAMAGE = 50;
	public static final double PROJECTILE_DAMAGE = 100;
	
	
	public static final int LEVEL_WIDTH = 10000;
	public static final int LEVEL_HEIGHT = 1200;
	
}
