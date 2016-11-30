package authoring.view.run;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import game_object.core.Dimension;
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
		myStage.setResizable(false);
		myGroup = new Group();
		myRunningView = new TestGameRunningView(this.getController());
		myConfiguringView = new TestGameConfiguringView(this.getController());
		this.addSubViews(myRunningView, myConfiguringView);
		myGroup.getChildren().addAll(myRunningView.getUI(), myConfiguringView.getUI());
		Dimension gameScreenSize = 
				this.getController().getEnvironment().getCurrentGame().getScreenSize();
		myScene = new Scene(
			myGroup,
			gameScreenSize.getWidth(),
			gameScreenSize.getHeight(),
			Color.WHITE
		);
		this.setWidth(gameScreenSize.getWidth());
		this.setHeight(gameScreenSize.getHeight());
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
