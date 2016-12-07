package authoring.view.canvas;

import authoring.AuthoringController;
import authoring.constants.UIConstants;
import authoring.controller.CanvasController;
import authoring.view.AbstractView;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CanvasView extends AbstractView {


    private ScrollPane scrollPane;
    private Group content; // holder for all SpriteViews
    private HBox background;

    private CanvasController canvasController;

    public CanvasView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        content = new Group();
        background = new HBox();
        background.setPrefWidth(UIConstants.CANVAS_STARTING_WIDTH);
        background.setPrefHeight(UIConstants.CANVAS_STARTING_HEIGHT);
        Rectangle defaultBackground = new Rectangle(0, 0, 
        		UIConstants.CANVAS_STARTING_WIDTH,
        		UIConstants.CANVAS_STARTING_HEIGHT);
        defaultBackground.setFill(Color.LIGHTCYAN);
        background.getChildren().add(defaultBackground);
        content.getChildren().add(background);
        scrollPane = new ScrollPane(content);
        addUI(scrollPane);
        CanvasAdjusterButtonsView canvasAdjusterButtonsView = new CanvasAdjusterButtonsView(getController());
        addUI(canvasAdjusterButtonsView.getUI());
        addSubView(canvasAdjusterButtonsView);

        canvasController = getController().getCanvasController();
        canvasController.init(this, scrollPane, content, background);
    }

    @Override
    protected void updateLayoutSelf() {
        scrollPane.setPrefHeight(this.getHeight());
        scrollPane.setPrefWidth(this.getWidth());
    }

}
