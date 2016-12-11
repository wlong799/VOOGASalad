import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

public class Main extends Application {
	
	private static final ResourceBundle languageProperties = ResourceBundles.languageProperties;
	private String languagesFilePathBeginning = "resources/languages/";
	private ComboBox<String> languagesComboBox;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.setSpacing(50);
		Label title = new Label(languageProperties.getString("voogasalad"));
		title.setFont(new Font(20));
		box.getChildren().addAll(
				title,
				initLanguageComboBox(),
				initAuthoringButton(),
				initPlayerButton());
		Scene s = new Scene(box, 500, 500);
		primaryStage.setScene(s);
		primaryStage.setTitle(languageProperties.getString("maintitle"));
		primaryStage.show();
	}
	
	private ComboBox<String> initLanguageComboBox() throws IOException {
		ObservableList<String> languageOptions = FXCollections.observableArrayList();
		BufferedReader br = new BufferedReader(new FileReader("src/resources/languages/LanguagesList"));
		String language;
		
		while ((language = br.readLine()) != null) {
		   {
			   languageOptions.add(language);
		   }
		}
		br.close();
		
		languagesComboBox = new ComboBox<>(languageOptions);	
		languagesComboBox.setValue(languageProperties.getString("selectLanguage"));
		return languagesComboBox;
	}
	
	private Button initAuthoringButton() {
		Button button = new Button(languageProperties.getString("authoring"));
		button.setOnAction(e -> new AuthoringInitializer().init(languagesFilePathBeginning + languagesComboBox.getValue()));
		return button;
	}
	
	private Button initPlayerButton() {
		Button button = new Button(languageProperties.getString("player"));
		button.setOnAction(e -> {
			GamePlayManager applicationManager = new GamePlayManager();
			applicationManager.start(new Stage());
		});
		return button;
	}
	
}
