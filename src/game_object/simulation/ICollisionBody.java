package game_object.simulation;

import game_object.block.StaticBlock;
import game_object.character.Enemy;
import game_object.character.Hero;

public interface ICollisionBody extends IBodyWithPosition {
	
	void setCategoryBitMask(int categoryBitMask);
	
	int getCategoryBitMask();
	
	void setCollisionBitMask(int collisionBitMask);
	
	int getCollisionBitMask();
	
	void onCollideWith(ICollisionBody otherBody);
	
	void onCollideWith(Hero h);
	
	void onCollideWith(Enemy e);
	
	void onCollideWith(StaticBlock b);
	
}
