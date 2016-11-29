package authoring.view.canvas;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import authoring.constants.UIConstants;
import authoring.controller.CanvasViewController;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

/**
 * Canvas AbstractView - editor UI
 * need refactor - extract resize, drag methods to a 'controller'
 * 		using composition
 */
public class CanvasView extends AbstractView {

	private ScrollPane scrollPane;
	private Group content; // holder for all SpriteViews
	private Rectangle background;
	
	private CanvasViewController canvasViewController;

	public CanvasView(AuthoringController controller) {
		super(controller);
	}

	@Override
	protected void initUI() {
		content = new Group();
		background = new Rectangle(
				0,
				0,
				UIConstants.CANVAS_STARTING_WIDTH,
				Screen.getPrimary().getVisualBounds().getHeight() -
					UIConstants.BOTTOM_HEIGHT -
					UIConstants.TOP_HEIGHT -
					40);
		background.setFill(Color.BEIGE);
		content.getChildren().add(background);
		scrollPane = new ScrollPane(content);
		this.addUI(scrollPane);
		CanvasAdjusterButtonsView canvasAdjusterButtonsView = new CanvasAdjusterButtonsView(getController());
		addUI(canvasAdjusterButtonsView.getUI());
		addSubView(canvasAdjusterButtonsView);
		this.addSubView(new CanvasAdjusterButtonsView(this.getController()));
		
		canvasViewController = this.getController().getCanvasViewController();
		canvasViewController.init(
				this, scrollPane, content, background);
	}

	@Override
	protected void updateLayoutSelf() {
		scrollPane.setPrefHeight(this.getHeight());
		scrollPane.setPrefWidth(this.getWidth());
	}

//	private boolean canAdjustScrollPane(SpriteView spView) {
//		retrieveBackgroundSize();
//		double x = spView.getPositionX();
//		double y = spView.getPositionY();
//		return 0 <= x && x <= bgWidth && 0 <= y && y <= bgHeight;
//	}

}
