package authoring.share.action;

import authoring.AuthoringController;
import authoring.share.exception.ShareEditException;
import authoring.view.canvas.SpriteView;

public class RemoveSpriteAction extends AbstractAction {

	private static final long serialVersionUID = -5564136472217188635L;

	public RemoveSpriteAction(long id) {
		super(id);
	}

	@Override
	public void apply(AuthoringController controller) throws ShareEditException {
		SpriteView toRemove = controller.getSpriteViewWithID(this.getID());
		controller.getCanvasViewController().delete(toRemove, false);
	}
	
}
