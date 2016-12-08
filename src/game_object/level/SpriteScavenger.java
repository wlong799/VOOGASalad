package game_object.level;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpriteScavenger {
	
	private Set<Object> myScavengeSet;
	
	public SpriteScavenger() {
		myScavengeSet = new HashSet<>();
	}
	
	public boolean scavengingNeeded() {
		return !myScavengeSet.isEmpty();
	}
	
	public void mark(Object sprite) {
		myScavengeSet.add(sprite);
	}
	
	public void scavengeList(List<? extends Object> list) {
		for (Object sprite : myScavengeSet) {
			list.remove(sprite);
		}
	}
	
	public void clear() {
		myScavengeSet.clear();
	}
	
}
