package splash_screen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import authoring.AuthoringInitializer;
import authoring.ui.DialogFactory;
import game_player.GamePlayManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import resources.ResourceBundles;

public class EngineSetUp {
    private static final ResourceBundle languageProperties = ResourceBundles.languageProperties;
    private String languagesFilePathBeginning = "resources/languages/";
    private ComboBox<String> languagesComboBox;
    private GridPane grid;

    public EngineSetUp() throws IOException {
        Image splashImage = new Image("img/splash.png");

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.setBackground(new Background(new BackgroundImage(splashImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        initLanguageComboBox();
        initAuthoringButton();
        initPlayerButton();
    }

    public GridPane getSplashScreenContent() {
        return grid;
    }

    private void initLanguageComboBox() throws IOException {
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
        languagesComboBox.setMinWidth(200.0);
        languagesComboBox.setPrefWidth(languagesComboBox.getMinWidth());
        languagesComboBox.getSelectionModel().select(0);
        grid.add(languagesComboBox, 2, 28);
    }

    private void initAuthoringButton() {
        Image createImage = new Image("img/create.png");
        ImageView createIV = new ImageView(createImage);
        Button button = new Button();
        button.setGraphic(createIV);
        button.setOnAction(e -> {
            try {
                new AuthoringInitializer().init(languagesFilePathBeginning + languagesComboBox.getValue());
                ((Stage) grid.getScene().getWindow()).close();
            } catch (MissingResourceException r) {
                DialogFactory.showErrorDialog(
                        languageProperties.getString("stop"),
                        languageProperties.getString("noLanguage"),
                        languageProperties.getString("tryAgain"));
            }
        });

        grid.add(button, 2, 30);
    }

    private void initPlayerButton() {
        Image playImage = new Image("img/play.png");
        ImageView playIV = new ImageView(playImage);
        Button button = new Button();
        button.setGraphic(playIV);
        button.setOnAction(e -> {
            GamePlayManager applicationManager = new GamePlayManager();
            applicationManager.start(new Stage());
            ((Stage) grid.getScene().getWindow()).close();
        });
        grid.add(button, 6, 30);
    }


}
