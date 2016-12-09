package game_object.core;

import java.util.*;

/**
 * Class for holding children sprites 
 * @author Yilun
 *
 */
public class ChildSprites {

	private List<ISprite> mySprites;

	private boolean myChanged;
	
	public ChildSprites() {
		mySprites = new ArrayList<>();
		myChanged = false;
	}
	
	public boolean addSprite(ISprite sprite) {
		myChanged = true;
		return mySprites.add(sprite);
	}
	
	public boolean removeSprite(ISprite sprite) {
		myChanged = true;
		return mySprites.remove(sprite);
	}
	
	public boolean isChanged() {
		return myChanged;
	}

	public void setChanged(boolean changed) {
		myChanged = changed;
	}

	/**
	 * Get all sprites. 
	 * @return
	 */
	public List<ISprite> getSprites() {
		return mySprites;
	}

}
