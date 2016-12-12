import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.ResourceBundles;
import splash_screen.EngineSetUp;

public class Main extends Application {

    private static final ResourceBundle languageProperties = ResourceBundles.languageProperties;
    private static final ResourceBundle mainProperties = ResourceBundles.mainProperties;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        EngineSetUp splashScreenContent = new EngineSetUp();
        Scene s = new Scene(
                splashScreenContent.getSplashScreenContent(),
                Integer.parseInt(mainProperties.getString("sceneDim")),
                Integer.parseInt(mainProperties.getString("sceneDim")));
        primaryStage.setScene(s);
        primaryStage.setTitle(languageProperties.getString("maintitle"));
        primaryStage.show();
    }

}
