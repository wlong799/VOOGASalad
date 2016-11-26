package authoring.run;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class TestGameRunningView extends AbstractView {
	
	private Pane myPane;
	private Rectangle myClip;

	public TestGameRunningView(AuthoringController controller) {
		super(controller);
	}
	
	public void clearSpriteViews() {
		myPane.getChildren().clear();
	}
	
	public void addSpriteView(ImageView spView) {
		myPane.getChildren().add(spView);
	}

	@Override
	protected void initUI() {
		myPane = new Pane();
		this.addUI(myPane);
	}

	@Override
	protected void updateLayoutSelf() {
		myClip = new Rectangle(this.getWidth(), this.getHeight());
		myPane.setClip(myClip);
	}

}
