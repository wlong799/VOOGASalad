package authoring.view.run;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestGameView extends AbstractView {

	private Stage myStage;
	private Scene myScene;
	private Group myGroup;

	private TestGameRunningView myRunningView;
	private TestGameConfiguringView myConfiguringView;

	public TestGameView(AuthoringController controller) {
		super(controller);
	}

	public void show() {
		myStage.show();
	}

	public Scene getScene() {
		return myScene;
	}

	public void clearSpriteViews() {
		myRunningView.clearSpriteViews();
	}

	public void addSpriteView(ImageView spView) {
		myRunningView.addSpriteView(spView);
	}

	@Override
	protected void initUI() {
		myStage = new Stage();
		myGroup = new Group();
		myRunningView = new TestGameRunningView(this.getController());
		myConfiguringView = new TestGameConfiguringView(this.getController());
		this.addSubViews(myRunningView, myConfiguringView);
		myGroup.getChildren().addAll(myRunningView.getUI(), myConfiguringView.getUI());

		myScene = new Scene(myGroup, 900, 600, Color.WHITE);
		this.setWidth(900);
		this.setHeight(600);
		myScene.heightProperty().addListener((obv, oldVal, newVal) -> {
			this.setHeight(newVal.doubleValue());
			this.updateLayout();
		});
		myScene.widthProperty().addListener((obv, oldVal, newVal) -> {
			this.setWidth(newVal.doubleValue());
			this.updateLayout();
		});
		myStage.setScene(myScene);
	}

	@Override
	protected void updateLayoutSelf() {
		myRunningView.setSize(this.getWidth() - 200, this.getHeight());
		myConfiguringView.setSize(200, this.getHeight());
		myConfiguringView.setPositionX(this.getWidth() - 200);
	}

}
