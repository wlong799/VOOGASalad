package authoring.share.action;

import authoring.AuthoringController;
import authoring.share.exception.ShareEditException;
import authoring.view.canvas.SpriteView;

/**
 * @author billyu
 * move a sprite view to another position
 * precondition: the target sprite view exists on every client
 */
public class MoveSpriteAction extends AbstractAction {
	
	private static final long serialVersionUID = -8847494025410089520L;
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
			System.out.println("in move");
			throw new ShareEditException(controller.getEnvironment().getLanguageResourceBundle().getString("spriteViewNotFound"));
		}
		controller.getCanvasController().setAbsolutePosition(targetView, myNewX, myNewY, false);
	}

}
