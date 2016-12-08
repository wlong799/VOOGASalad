package authoring;

import authoring.constants.UIConstants;
import authoring.view.AuthoringView;
import game_object.LevelGenerator;
import game_object.core.Game;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AuthoringInitializer {
	
	private Rectangle2D primaryScreenBounds;
	private double width;
	private double height;
	private Scene scn;
	private AuthoringView authoringView;
	private AuthorEnvironment environment;
	
	public void init() {
		String os = System.getProperty("os.name");
		primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		height = 0;
		width = primaryScreenBounds.getWidth();
		if (os.contains("Windows")) {
			height = primaryScreenBounds.getHeight();
		}
		else {
			height = primaryScreenBounds.getHeight() - UIConstants.DISCREPANCY_BETWEEN_MAC_AND_WINDOWS_SCREEN;
		}
		
		environment = initEnvironment();
		
		AuthoringController controller = new AuthoringController(environment);
		authoringView = new AuthoringView(controller);
		authoringView.setSize(width, height);
		authoringView.updateLayout();
		
		scn = initScene();
		controller.setScene(scn);
		
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
		Scene scn = new Scene(authoringView.getUI(), width, height);
		
		scn.widthProperty().addListener((val, oldWidth, newWidth) -> {
			authoringView.setWidth(newWidth.doubleValue());
			authoringView.updateLayout();
		});
		scn.heightProperty().addListener((val, oldHeight, newHeight) -> {
			authoringView.setHeight(newHeight.doubleValue());
			authoringView.updateLayout();
		});
		return scn;
	}

	private void initStage() {
		Stage stage = new Stage();
		stage.setX(primaryScreenBounds.getMinX());
		stage.setY(primaryScreenBounds.getMinY());
		stage.setWidth(width);
		stage.setScene(scn);
		stage.show();
	}

}
