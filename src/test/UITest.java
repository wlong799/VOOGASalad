package test;

import authoring.AuthoringController;
import authoring.view.MainView;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import voogasalad_overwatch.AuthorEnvironment;

public class UITest extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		String os = System.getProperty("os.name");
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		double height = 0;
		double width = primaryScreenBounds.getWidth();
		if (os.contains("Windows")) {
			height = primaryScreenBounds.getHeight();
		}
		else {
			height = primaryScreenBounds.getHeight() - 20;
		}
		
		AuthorEnvironment env = new AuthorEnvironment();
		AuthoringController controller = new AuthoringController(env);
		MainView mainView = new MainView(controller);
		mainView.setPositionAndSize(0, 0, width, height);
		mainView.layout();
		Scene scn = new Scene(mainView.getUI());
		Stage stage = new Stage();
		stage.setX(primaryScreenBounds.getMinX());
		stage.setY(primaryScreenBounds.getMinY());
		stage.setWidth(width);
		stage.setScene(scn);
		stage.show();
		
		scn.widthProperty().addListener((val, oldWidth, newWidth) -> {
			mainView.setWidth(newWidth.doubleValue());
			mainView.layout();
		});
		scn.heightProperty().addListener((val, oldHeight, newHeight) -> {
			mainView.setHeight(newHeight.doubleValue());
			mainView.layout();
		});
		
		controller.setScene(scn);
	}
	
}
