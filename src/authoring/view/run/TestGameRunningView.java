package authoring.view.run;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class TestGameRunningView extends AbstractView {
	
	private Pane myPane;
	private Rectangle myClip;

	public TestGameRunningView(AuthoringController controller) {
		super(controller);
	}
	
	public void setRoot(Group root) {
		myPane.getChildren().add(root);
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
