package game_object;

public interface CollisionBody {
	
/*
 * Shouldn't need this method since categoryBitMask
 * should be hardcoded in the actual class for each sprite.
 */
//	void getCategoryBitMask(int categoryBitMask);
	int getCategoryBitMask();
	void setCollisionBitMask(int collisionBitMask);
	int getCollisionBitMask();
}
