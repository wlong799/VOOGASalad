package test;

import authoring.AuthoringInitializer;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author billyu
 * Tests:
 * 1. drag from components view and drop it on canvas, notice z position and inspector updates
 * 2. drag a SpriteView from one place to another, notice inspector updates and z position
 * 3. change the x, y, z position of a SpriteView
 * 4. change the width or height of a SpriteView
 * 5. resize a SpriteView using the four dots
 * 6. play around!!!
 */
public class UITest extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new AuthoringInitializer().init();
	}
	
}
