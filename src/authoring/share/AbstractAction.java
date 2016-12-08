package authoring.share;

public abstract class AbstractAction implements IAction {
	
	private long myID;
	
	public AbstractAction(long id) {
		myID = id;
	}
	
	public long getID() {
		return myID;
	}

}
