package authoring.share.action;

public abstract class AbstractAction implements IAction {
	
	private static final long serialVersionUID = 6764291756069414746L;
	private long myID;
	
	public AbstractAction(long id) {
		myID = id;
	}
	
	public long getID() {
		return myID;
	}

}
