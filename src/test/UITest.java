package test;

import authoring.AuthoringInitializer;
import javafx.application.Application;
import javafx.stage.Stage;

public class UITest extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new AuthoringInitializer().init();
	}
	
}
