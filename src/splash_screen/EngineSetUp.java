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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
		Image splashImage = new Image("img/splash.png");
		ImageView splashIV = new ImageView (splashImage);
		
		box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.setSpacing(50);
		box.setBackground(new Background(new BackgroundImage(splashImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		box.getChildren().addAll(
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
		Image createImage = new Image("img/create.png");
		ImageView createIV = new ImageView (createImage);
		Button button = new Button();
		button.setGraphic(createIV);
		button.setOnAction(e -> new AuthoringInitializer().init(languagesFilePathBeginning + languagesComboBox.getValue()));
		return button;
	}
	
	private Button initPlayerButton() {
		Image playImage = new Image("img/play.png");
		ImageView playIV = new ImageView (playImage);
		Button button = new Button();
		button.setGraphic(playIV);
		button.setOnAction(e -> {
			GamePlayManager applicationManager = new GamePlayManager();
			applicationManager.start(new Stage());
		});
		return button;
	}
	
	
	
}
