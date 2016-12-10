package authoring.share.action;

import authoring.AuthoringController;
import authoring.share.exception.ShareEditException;
import authoring.view.canvas.SpriteView;

public class AddSpriteAction extends AbstractAction {
	
	private SpriteView mySpriteView;
	private double myX;
	private double myY;
	
	public AddSpriteAction(SpriteView spView, double newX, double newY) {
		super(spView.getID());
		mySpriteView = spView;
		myX = newX;
		myY = newY;
	}

	@Override
	public void apply(AuthoringController controller) throws ShareEditException {
		controller.getCanvasViewController().addSpriteView(mySpriteView, myX, myY, false);
	}
	
}
