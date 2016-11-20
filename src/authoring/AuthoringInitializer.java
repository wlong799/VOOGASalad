package authoring;

import authoring.view.MainView;
import game_object.LevelGenerator;
import game_object.framework.Game;
import game_object.level.Level;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AuthoringInitializer {
	
	private Rectangle2D primaryScreenBounds;
	private double width;
	private double height;
	private Scene scn;
	private MainView mainView;
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
		
		AuthoringController controller = new AuthoringController(environment);
		mainView = new MainView(controller);
		mainView.setPositionAndSize(0, 0, width, height);
		mainView.layout();
		
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
		Scene scn = new Scene(mainView.getUI());
		
		scn.widthProperty().addListener((val, oldWidth, newWidth) -> {
			mainView.setWidth(newWidth.doubleValue());
			mainView.layout();
		});
		scn.heightProperty().addListener((val, oldHeight, newHeight) -> {
			mainView.setHeight(newHeight.doubleValue());
			mainView.layout();
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
