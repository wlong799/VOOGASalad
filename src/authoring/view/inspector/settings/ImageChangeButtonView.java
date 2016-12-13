package authoring.view.inspector.settings;

import authoring.AuthoringController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ResourceBundle;

/**
 * Provides a button that displays the current image for some setting, and allows you to change it when it is clicked
 * on.
 *
 * @author Will Long
 */
public class ImageChangeButtonView extends AbstractSettingsView {
    private static final double WIDTH_RATIO = 0.25;

    private String myImageFilename;
    private ITextChangeHandler myHandler;
    private Button myButton;
    private ImageView myImageView;
    private ResourceBundle myLangageResourceBundle;

    public ImageChangeButtonView(AuthoringController controller, String defaultFilename, ITextChangeHandler handler) {
        super(controller);
        myImageFilename = defaultFilename;
        myHandler = handler;
    }

    public boolean setImageFilename(String imageFilename) {
        try {
            myImageView.setImage(new Image(imageFilename));
            myImageFilename = imageFilename;
            return true;
        } catch (RuntimeException e) {
            myImageView.setImage(null);
            return false;
        }
    }

    @Override
    public void initializeSettings() {
        myButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle(myLangageResourceBundle.getString("chooseNewImage"));
            File file = fileChooser.showOpenDialog(new Stage());
            if (setImageFilename(file.toURI().toString())) {
                myHandler.handle(myImageFilename);
            }
        });
        this.setImageFilename(myImageFilename);
    }

    @Override
    protected void initUI() {
        super.initUI();
        myLangageResourceBundle = super.getController().getEnvironment().getLanguageResourceBundle();
        myLabel.setText(myLangageResourceBundle.getString("changeImage"));
        myButton = new Button();
        myImageView = new ImageView();
        myButton.setGraphic(myImageView);
        myContent.getChildren().add(myButton);
    }

    @Override
    protected void updateLayoutSelf() {
        myImageView.setFitWidth(getWidth() * WIDTH_RATIO);
        myImageView.setFitHeight(getWidth() * WIDTH_RATIO);
    }
}
