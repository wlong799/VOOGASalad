package authoring.view.canvas;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.controller.CanvasViewController;
import authoring.view.AbstractView;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import resources.ResourceBundles;

public class CanvasView extends AbstractView {


    private ScrollPane scrollPane;
    private Group content; // holder for all SpriteViews
    private HBox background;
    private ResourceBundle canvasProperties;

    private CanvasViewController canvasViewController;

    public CanvasView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
    	canvasProperties = ResourceBundles.canvasProperties;
    	
    	content = new Group();
        background = new HBox();
        background.setPrefWidth(Double.parseDouble(canvasProperties.getString("CANVAS_STARTING_WIDTH")));
        background.setPrefHeight(Double.parseDouble(canvasProperties.getString("CANVAS_STARTING_HEIGHT")));
        Rectangle defaultBackground = new Rectangle(0, 0, 
        		Double.parseDouble(canvasProperties.getString("CANVAS_STARTING_WIDTH")),
        		Double.parseDouble(canvasProperties.getString("CANVAS_STARTING_HEIGHT")));
        defaultBackground.setFill(Color.LIGHTCYAN);
        background.getChildren().add(defaultBackground);
        content.getChildren().add(background);
        scrollPane = new ScrollPane(content);
        addUI(scrollPane);
        CanvasAdjusterButtonsView canvasAdjusterButtonsView = new CanvasAdjusterButtonsView(getController());
        addUI(canvasAdjusterButtonsView.getUI());
        addSubView(canvasAdjusterButtonsView);

        canvasViewController = getController().getCanvasViewController();
        canvasViewController.init(this, scrollPane, content, background);
    }

    @Override
    protected void updateLayoutSelf() {
        scrollPane.setPrefHeight(this.getHeight());
        scrollPane.setPrefWidth(this.getWidth());
    }

}
