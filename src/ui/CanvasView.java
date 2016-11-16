package ui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CanvasView extends View {
	
	private List<SpriteView> spriteViews;
	private ScrollPane scrollPane;
	private Group content;

	public CanvasView(AuthoringController controller) {
		super(controller);
	}
	
	@Override
	protected void initUI() {
		spriteViews = new ArrayList<>();
		content = new Group();
		Rectangle rect = new Rectangle(0,0,
				UIConstants.CANVAS_STARTING_WIDTH,UIConstants.CANVAS_HEIGHT);
		rect.setFill(Color.BEIGE);
		content.getChildren().add(rect);
		scrollPane = new ScrollPane(rect);
		this.addUI(scrollPane);
	}

	@Override
	protected void layoutSelf() {
		scrollPane.setPrefHeight(this.getHeight());
		scrollPane.setPrefWidth(this.getWidth());
	}

}
