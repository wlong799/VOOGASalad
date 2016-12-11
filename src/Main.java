import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ResourceBundle;

import authoring.AuthoringInitializer;
import game_player.GamePlayManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import resources.ResourceBundles;
import splash_screen.EngineSetUp;

public class Main extends Application {
	
	private static final ResourceBundle languageProperties = ResourceBundles.languageProperties;
	private String languagesFilePathBeginning = "resources/languages/";
	private ComboBox<String> languagesComboBox;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		EngineSetUp splashScreenContent = new EngineSetUp();
		
		Scene s = new Scene(splashScreenContent.getSplashScreenContent(), 500, 500);
		primaryStage.setScene(s);
		primaryStage.setTitle(languageProperties.getString("maintitle"));
		primaryStage.show();
	}
	
}
