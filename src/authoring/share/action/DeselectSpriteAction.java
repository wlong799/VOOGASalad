package authoring.share.action;

import authoring.AuthoringController;
import authoring.share.exception.ShareEditException;
import authoring.view.canvas.SpriteView;

public class DeselectSpriteAction extends AbstractAction {

	private static final long serialVersionUID = 9034994951734865847L;

	public DeselectSpriteAction(long id) {
		super(id);
	}

	@Override
	public void apply(AuthoringController controller) throws ShareEditException {
		SpriteView targetView = controller.getSpriteViewWithID(this.getID());
		if (targetView == null) {
			throw new ShareEditException("Sprite View not found");
		}
		controller.deselectSpriteViewFromNetwork(targetView);
	}

}
