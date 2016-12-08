package game_object.level;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import game_object.core.ISprite;

public class SpriteScavenger {
	
	private Set<ISprite> myScavengeSet;
	
	public SpriteScavenger() {
		myScavengeSet = new HashSet<>();
	}
	
	public boolean scavengingNeeded() {
		return !myScavengeSet.isEmpty();
	}
	
	public void mark(ISprite sprite) {
		myScavengeSet.add(sprite);
	}
	
	public void scavengeList(List<? extends ISprite> list) {
		for (ISprite sprite : myScavengeSet) {
			list.remove(sprite);
		}
	}
	
	public void clear() {
		myScavengeSet.clear();
	}
	
}
