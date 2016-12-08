package authoring.share;

public class IDManager {
	
	private long currentIndex;
	private static final int MAX_ALLOWED_SPRITES = 1000;
	
	public IDManager(int myUserID) {
		currentIndex = myUserID * MAX_ALLOWED_SPRITES;
	}
	
	public long getNextID() {
		return currentIndex++;
	}

}
