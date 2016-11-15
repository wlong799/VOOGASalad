package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UITest extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		double width = 500;
		double height = 500;
		MainView mainView = new MainView();
		mainView.setPositionAndSize(0, 0, width, height);
		mainView.layout();
		Scene scn = new Scene(mainView.getUI());
		Stage stage = new Stage();
		stage.setWidth(width);
		stage.setHeight(height);
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
	}
	
}
