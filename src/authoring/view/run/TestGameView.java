package authoring.view.run;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import game_object.constants.DefaultConstants;
import game_object.core.Dimension;
import game_object.level.Level;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestGameView extends AbstractView {

	private Stage myStage;
	private Scene myScene;
	private Group myGroup;

	private TestGameRunningView myRunningView;
	private InspectorLevelView myConfiguringView;

	public TestGameView(AuthoringController controller) {
		super(controller);
	}

	public void show() {
		myStage.show();
	}

	public Scene getScene() {
		return myScene;
	}
	
	public void setRunningRoot(Group root) {
		myRunningView.setRoot(root);
	}
	
	public void updateUI(Level level) {
		myConfiguringView.setLevel(level);
	}

	@Override
	protected void initUI() {
		myStage = new Stage();
		myStage.setResizable(false);
		myGroup = new Group();
		myRunningView = new TestGameRunningView(this.getController());
		myConfiguringView = new InspectorLevelView(this.getController());
		
		this.addSubViews(myRunningView, myConfiguringView);
		myGroup.getChildren().addAll(myRunningView.getUI(), myConfiguringView.getUI());
		Dimension gameScreenSize = 
				this.getController().getEnvironment().getCurrentGame().getScreenSize();
		initScene(gameScreenSize);
		this.setWidth(gameScreenSize.getWidth() + DefaultConstants.TEST_CONFIGURE_WIDTH);
		this.setHeight(gameScreenSize.getHeight());
		myStage.setScene(myScene);
	}

	@Override
	protected void updateLayoutSelf() {
		myRunningView.setSize(
				this.getWidth() - DefaultConstants.TEST_CONFIGURE_WIDTH, this.getHeight());
		myConfiguringView.setSize(
				DefaultConstants.TEST_CONFIGURE_WIDTH, this.getHeight());
		myConfiguringView.setPositionX(
				this.getWidth() - DefaultConstants.TEST_CONFIGURE_WIDTH);
	}
	
	private void initScene(Dimension gameScreenSize) {
		myScene = new Scene(
			myGroup,
			gameScreenSize.getWidth() + DefaultConstants.TEST_CONFIGURE_WIDTH,
			gameScreenSize.getHeight(),
			Color.WHITE
		);
		
		myScene.heightProperty().addListener((obv, oldVal, newVal) -> {
			this.setHeight(newVal.doubleValue());
			this.updateLayout();
		});
		myScene.widthProperty().addListener((obv, oldVal, newVal) -> {
			this.setWidth(newVal.doubleValue());
			this.updateLayout();
		});
	}

}
