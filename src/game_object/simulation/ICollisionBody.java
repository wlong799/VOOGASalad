package game_object.simulation;

public interface ICollisionBody {
	
	void setCategoryBitMask(int categoryBitMask);
	
	int getCategoryBitMask();
	
	void setCollisionBitMask(int collisionBitMask);
	
	int getCollisionBitMask();
	
}
