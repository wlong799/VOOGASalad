package game_object.level;

public class AbstractLevel implements ILevel {

	int myLevelIdx;
	
	@Override
	public int getLevelIdx() {
		return myLevelIdx;
	}

	@Override
	public void setLevelIdx(int idx) {
		myLevelIdx = idx;
	}

	
	
}
