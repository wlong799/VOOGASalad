package game_object.core;

public class DefaultConstants {
	
	public static final double MOVING_UNIT = 50;
	public static final double JUMPING_UNIT = 100;
	public static final int MAX_JUMP = 2;
	
	public static final int VOID_CATEGORY_BIT_MASK = 0;
	public static final int HERO_CATEGORY_BIT_MASK = 1 << 0;
	public static final int ENEMY_CATEGORY_BIT_MASK = 1 << 1;
	public static final int BLOCK_CATEGORY_BIT_MASK = 1 << 2;
	
	public static final int CHARACTER_MAX_HP = 100;
	public static final int ENEMY_BODY_DAMAGE = 30;
	
//	public static final int GROUND_BLOCK_CATEGORY_BIT_MASK = 1 << 3;
//	public static final int FLOATING_BLOCK_CATEGORY_BIT_MASK = 1 << 4;
	
}
