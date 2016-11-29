package authoring;

import java.io.IOException;

import authoring.view.AuthoringView;
import game_object.LevelGenerator;
import game_object.framework.Game;
import game_object.level.Level;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import network.server.Coordinator;

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
			height = primaryScreenBounds.getHeight() - 20;
		}
		
		environment = initEnvironment();
		
		try {
			new Coordinator(9999);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		AuthoringController controller = new AuthoringController(environment);
		authoringView = new AuthoringView(controller);
		authoringView.setSize(width, height);
		authoringView.updateLayout();
		
		scn = initScene();
		controller.setScene(scn);
		
		initStage();
	}

	private AuthorEnvironment initEnvironment() {
		Game game = new Game();
		Level testLevel = LevelGenerator.getTestLevelB();
		game.addLevel(testLevel);
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
