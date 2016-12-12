package game_object.collision;

import game_object.simulation.ICollisionBody;

public abstract class AbstractCollisionStrategy<A extends ICollisionBody, B extends ICollisionBody>
	implements ICollisionStrategy<A, B> {

	protected boolean myValid = true;
	
	@Override
	public void setValid(boolean valid) {
		myValid = valid;
	}
	
	@Override
	public boolean isValid() {
		return myValid;
	}
	

}
