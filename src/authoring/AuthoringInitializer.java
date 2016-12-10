package authoring;

import java.util.ResourceBundle;

import authoring.view.AuthoringView;
import game_object.LevelGenerator;
import game_object.core.Game;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import resources.ResourceBundles;

public class AuthoringInitializer {
	
	private Rectangle2D myPrimaryScreenBounds;
	private double myWidth;
	private double myHeight;
	private Scene myScene;
	private AuthoringView myAuthoringView;
	private AuthorEnvironment myEnvironment;
	private ResourceBundle myCanvasProperties;
	
	public void init() {
		String os = System.getProperty("os.name");
		myPrimaryScreenBounds = Screen.getPrimary().getVisualBounds();
		myHeight = 0;
		myWidth = myPrimaryScreenBounds.getWidth();
		if (os.contains("Windows")) {
			myHeight = myPrimaryScreenBounds.getHeight();
		}
		else {
			myCanvasProperties = ResourceBundles.canvasProperties;
			myHeight = myPrimaryScreenBounds.getHeight() - Double.parseDouble(myCanvasProperties.getString("DISCREPANCY_BETWEEN_MAC_AND_WINDOWS_SCREEN"));
		}
		
		myEnvironment = initEnvironment();
		
		AuthoringController controller = new AuthoringController(myEnvironment);
		myAuthoringView = new AuthoringView(controller);
		myAuthoringView.setSize(myWidth, myHeight);
		myAuthoringView.updateLayout();
		
		myScene = initScene();
		controller.setScene(myScene);
		
		initStage();
	}

	private AuthorEnvironment initEnvironment() {
		Game game = LevelGenerator.getTestGame();
		AuthorEnvironment env = new AuthorEnvironment();
		env.addGame(game);
		env.setCurrentGame(0);
		env.setCurrentLevel(0);
		return env;
	}

	private Scene initScene() {
		Scene scn = new Scene(myAuthoringView.getUI(), myWidth, myHeight);
		
		scn.widthProperty().addListener((val, oldWidth, newWidth) -> {
			myAuthoringView.setWidth(newWidth.doubleValue());
			myAuthoringView.updateLayout();
		});
		scn.heightProperty().addListener((val, oldHeight, newHeight) -> {
			myAuthoringView.setHeight(newHeight.doubleValue());
			myAuthoringView.updateLayout();
		});
		return scn;
	}

	private void initStage() {
		Stage stage = new Stage();
		stage.setX(myPrimaryScreenBounds.getMinX());
		stage.setY(myPrimaryScreenBounds.getMinY());
		stage.setWidth(myWidth);
		stage.setScene(myScene);
		stage.show();
	}

}
