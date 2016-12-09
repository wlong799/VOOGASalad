package authoring.view.canvas;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.controller.CanvasController;
import authoring.view.AbstractView;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import resources.ResourceBundles;

public class CanvasView extends AbstractView {

    private ScrollPane myScrollPane;
    private Group myContent; // holder for all SpriteViews
    private HBox myBackground;
    private ResourceBundle myCanvasProperties;
    private CanvasController myCanvasController;

    public CanvasView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
    	myCanvasProperties = ResourceBundles.canvasProperties;
    	
    	myContent = new Group();
        myBackground = new HBox();
        myBackground.setPrefWidth(Double.parseDouble(myCanvasProperties.getString("CANVAS_STARTING_WIDTH")));
        myBackground.setPrefHeight(Double.parseDouble(myCanvasProperties.getString("CANVAS_STARTING_HEIGHT")));
        Rectangle defaultBackground = new Rectangle(0, 0, 
        		Double.parseDouble(myCanvasProperties.getString("CANVAS_STARTING_WIDTH")),
        		Double.parseDouble(myCanvasProperties.getString("CANVAS_STARTING_HEIGHT")));
        defaultBackground.setFill(Color.LIGHTCYAN);
        myBackground.getChildren().add(defaultBackground);
        myContent.getChildren().add(myBackground);
        myScrollPane = new ScrollPane(myContent);
        addUI(myScrollPane);
        CanvasAdjusterButtonsView canvasAdjusterButtonsView = new CanvasAdjusterButtonsView(getController());
        addUI(canvasAdjusterButtonsView.getUI());
        addSubView(canvasAdjusterButtonsView);

        myCanvasController = getController().getCanvasController();
        myCanvasController.init(this, myScrollPane, myContent, myBackground);
    }

    @Override
    protected void updateLayoutSelf() {
        myScrollPane.setPrefHeight(this.getHeight());
        myScrollPane.setPrefWidth(this.getWidth());
    }

}
