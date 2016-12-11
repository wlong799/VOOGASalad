package authoring.share.action;

import authoring.AuthoringController;
import authoring.share.exception.ShareEditException;
import authoring.view.canvas.SpriteView;

public class SelectSpriteAction extends AbstractAction {

	private static final long serialVersionUID = -4016031177731195568L;
	private String myName;

	public SelectSpriteAction(long id, String name) {
		super(id);
		myName = name;
	}

	@Override
	public void apply(AuthoringController controller) throws ShareEditException {
		SpriteView targetView = controller.getSpriteViewWithID(this.getID());
		if (targetView == null) {
			throw new ShareEditException("Sprite View not found");
		}
		controller.selectSpriteViewFromNetwork(targetView, myName);
	}

}
