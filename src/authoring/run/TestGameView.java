package authoring.run;

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

	public TestGameView(AuthoringController controller) {
		super(controller);
	}
	
	public void show() {
		System.out.println(myGroup.getChildren());
		myStage.show();
	}
	
	public Scene getScene() {
		return myScene;
	}
	
	public void clearSpriteViews() {
		myGroup.getChildren().clear();
	}
	
	public void addSpriteView(ImageView spView) {
		myGroup.getChildren().add(spView);
	}

	@Override
	protected void initUI() {
		myStage = new Stage();
		myGroup = new Group();
		myScene = new Scene(myGroup, 600, 600, Color.WHITE);
		myStage.setScene(myScene);
	}

	@Override
	protected void updateLayoutSelf() {
	}

}
