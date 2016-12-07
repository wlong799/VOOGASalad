package authoring.view.canvas;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import authoring.constants.UIConstants;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CanvasAdjusterButtonsView extends AbstractView {

    private Button screenWider;
    private Button screenNarrower;
    private Button screenTaller;
    private Button screenShorter;

    public CanvasAdjusterButtonsView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        screenWider = new Button();
        screenNarrower = new Button();
        screenTaller = new Button();
        screenShorter = new Button();

        setButton(screenWider, 0, "img/wider.png");
        setButton(screenNarrower, 1, "img/thinner.png");
        setButton(screenTaller, 2, "img/taller.png");
        setButton(screenShorter, 3, "img/shorter.png");

        this.addUIAll(screenWider, screenNarrower, screenTaller, screenShorter);
        screenAdjusterButtonInit();
    }

    private void setButton(Button button, int multiplier, String path) {
        button.setPrefWidth(UIConstants.BUTTON_WIDTH);
        button.setLayoutX(UIConstants.BUTTON_WIDTH * multiplier);
        Image image = new Image(path);
        button.setGraphic(new ImageView(image));
        button.setPrefHeight(UIConstants.BUTTON_HEIGHT);
    }

    @Override
    protected void updateLayoutSelf() {
    }

    private void screenAdjusterButtonInit() {
        screenNarrower.setOnAction((event) -> {
            this.getController().getCanvasController().shrink();

        });
        screenWider.setOnAction((event) -> {
            this.getController().getCanvasController().expand();
        });
        screenTaller.setOnAction((event) -> {
            this.getController().getCanvasController().taller();
        });
        screenShorter.setOnAction((event) -> {
            this.getController().getCanvasController().shorter();
        });
    }

}
