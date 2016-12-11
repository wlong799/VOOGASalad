package authoring.share.action;

import authoring.AuthoringController;
import authoring.share.exception.ShareEditException;
import authoring.view.canvas.SpriteView;

public class ResizeSpriteAction extends AbstractAction {
	
	private static final long serialVersionUID = 6174674715810269477L;
	private double myWidth;
	private double myHeight;

	public ResizeSpriteAction(long id, double width, double height) {
		super(id);
		myWidth = width;
		myHeight = height;
	}

	@Override
	public void apply(AuthoringController controller) throws ShareEditException {
		SpriteView targetView = controller.getSpriteViewWithID(this.getID());
		if (targetView == null) {
			throw new ShareEditException("Sprite View not found");
		}
		targetView.setDimensionWidth(myWidth, false);
		targetView.setDimensionHeight(myHeight, false);
	}

}
