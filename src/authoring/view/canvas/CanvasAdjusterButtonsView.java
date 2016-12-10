package authoring.view.canvas;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import resources.ResourceBundles;

public class CanvasAdjusterButtonsView extends AbstractView {
    private Button myScreenWiderButton, myScreenNarrowerButton, myScreenTallerButton, myScreenShorterButton;
    private ResourceBundle myCanvasProperties;

    public CanvasAdjusterButtonsView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        myCanvasProperties = ResourceBundles.canvasProperties;

        myScreenWiderButton = new Button();
        myScreenNarrowerButton = new Button();
        myScreenTallerButton = new Button();
        myScreenShorterButton = new Button();

        setButton(myScreenWiderButton, Integer.parseInt(myCanvasProperties.getString("SCREEN_WIDER_POSITION")), "img/wider.png");
        setButton(myScreenNarrowerButton, Integer.parseInt(myCanvasProperties.getString("SCREEN_NARROWER_POSITION")), "img/thinner.png");
        setButton(myScreenTallerButton, Integer.parseInt(myCanvasProperties.getString("SCREEN_TALLER_POSITION")), "img/taller.png");
        setButton(myScreenShorterButton, Integer.parseInt(myCanvasProperties.getString("SCREEN_SHORTER_POSITION")), "img/shorter.png");

        this.addUIAll(myScreenWiderButton, myScreenNarrowerButton, myScreenTallerButton, myScreenShorterButton);
        screenAdjusterButtonInit();
    }

    private void setButton(Button button, int multiplier, String path) {
        button.setPrefWidth(Double.parseDouble(myCanvasProperties.getString("BUTTON_WIDTH")));
        button.setLayoutX(Double.parseDouble(myCanvasProperties.getString("BUTTON_WIDTH")) * multiplier);
        Image image = new Image(path);
        button.setGraphic(new ImageView(image));
        button.setPrefHeight(Double.parseDouble(myCanvasProperties.getString("BUTTON_HEIGHT")));
    }

    @Override
    protected void updateLayoutSelf() {
    }

    private void screenAdjusterButtonInit() {
        myScreenNarrowerButton.setOnAction((event) -> {
            this.getController().getCanvasController().shrink();

        });
        myScreenWiderButton.setOnAction((event) -> {
            this.getController().getCanvasController().expand();
        });
        myScreenTallerButton.setOnAction((event) -> {
            this.getController().getCanvasController().taller();
        });
        myScreenShorterButton.setOnAction((event) -> {
            this.getController().getCanvasController().shorter();
        });
    }

}
