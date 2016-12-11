package splash_screen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ResourceBundle;

import authoring.AuthoringInitializer;
import game_player.GamePlayManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import resources.ResourceBundles;

public class EngineSetUp {
	private static final ResourceBundle languageProperties = ResourceBundles.languageProperties;
	private String languagesFilePathBeginning = "resources/languages/";
	private ComboBox<String> languagesComboBox;
	private VBox box;
	
	public EngineSetUp() throws IOException {
		Label title = new Label(languageProperties.getString("voogasalad"));
		title.setFont(new Font(20));
		
		box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.setSpacing(50);
		
		box.getChildren().addAll(
				title,
				initLanguageComboBox(),
				initAuthoringButton(),
				initPlayerButton());
		
	}
	
	public VBox getSplashScreenContent() {
		return box;
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
