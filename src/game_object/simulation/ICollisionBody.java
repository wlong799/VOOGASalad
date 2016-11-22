package game_object.simulation;

public interface ICollisionBody extends IBodyWithPosition {
	
	void setCategoryBitMask(int categoryBitMask);
	
	int getCategoryBitMask();
	
	void setCollisionBitMask(int collisionBitMask);
	
	int getCollisionBitMask();
	
	void onCollideWith(ICollisionBody otherBody);
}
