package voogasalad_overwatch;

import java.util.Collection;

public interface IGame {
	
	void setLevel(int index, ILevel level);
	
	ILevel getLevel(int index);
	
	void setStartSprites(Collection<ISprite> starts);
	
	void setEndSprites(Collection<ISprite> ends);
	
	void editSprite(Sprite s);
}
