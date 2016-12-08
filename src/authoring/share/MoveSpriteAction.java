package authoring.share;

import authoring.AuthoringController;
import authoring.share.exception.ShareEditException;
import authoring.view.canvas.SpriteView;

/**
 * @author billyu
 * move a sprite view to another position
 * precondition: the target sprite view exists on every client
 */
public class MoveSpriteAction extends AbstractAction {
	
	private double myNewX;
	private double myNewY;
	
	public MoveSpriteAction(long id, double newX, double newY) {
		super(id);
		myNewX = newX;
		myNewY = newY;
	}

	@Override
	public void apply(AuthoringController controller) throws ShareEditException {
		SpriteView targetView = controller.getSpriteViewWithID(this.getID());
		if (targetView == null) {
			throw new ShareEditException("Sprite View not found");
		}
		controller.getCanvasViewController().setAbsolutePosition(targetView, myNewX, myNewY);
	}

}
