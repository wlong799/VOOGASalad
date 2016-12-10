package authoring.share;

public class IDManager {
	
	private long currentIndex;
	
	public IDManager(long start) {
		currentIndex = start;
	}
	
	public long getNextID() {
		return currentIndex++;
	}

}
