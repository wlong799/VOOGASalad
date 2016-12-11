package authoring.share.action;

import authoring.AuthoringController;
import authoring.share.exception.ShareEditException;
import authoring.view.canvas.SpriteView;
import game_object.core.ISprite;

public class AddSpriteAction extends AbstractAction {
	
	private static final long serialVersionUID = -4226578689808573094L;
	private ISprite mySprite;
	private double myX;
	private double myY;
	
	public AddSpriteAction(SpriteView spView, double newX, double newY) {
		super(spView.getID());
		mySprite = spView.getSprite();
		myX = newX;
		myY = newY;
	}

	@Override
	public void apply(AuthoringController controller) throws ShareEditException {
		controller.getCanvasController().addSpriteView(
				mySprite, 
				myX, 
				myY, 
				this.getID(), 
				false);
	}
	
}
