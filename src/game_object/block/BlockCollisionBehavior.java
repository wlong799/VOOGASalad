package game_object.block;

public enum BlockCollisionBehavior {

	// all sides are collidable
	ALL_ALL_COLLISION, 
	
	// only when a sprite is colliding the top 
	// surface of the block from the top, the 
	// collision is happening. this is mainly for 
	// character jumping through a block and landing on it. 
	TOP_TOP_COLLISION; 
	
}
