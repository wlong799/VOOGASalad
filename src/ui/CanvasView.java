package ui;

import java.util.ArrayList;
import java.util.List;

import game_object.block.GroundBlock;
import game_object.core.ISprite;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CanvasView extends View {
	
	private List<SpriteView> spriteViews;
	private ScrollPane scrollPane;
	private Group content; // holder for all SpriteViews

	public CanvasView(AuthoringController controller) {
		super(controller);
	}
	
	/**
	 * method to add a SpriteView to canvas
	 * used for drag and drop from components view
	 * @param spView SpriteView to add to display
	 * @param x position X of spView to add relative to top-left corner of CanvasView
	 * @param y position Y of spView to add relative to top-left corner of CanvasView
	 * x and y are not relative to the origin of content!
	 */
	public void add(SpriteView spView, double x, double y) {
		spriteViews.add(spView);
		content.getChildren().add(spView.getUI());
		spView.setPositionX(x);
		spView.setPositionY(y);
	}
	
	@Override
	protected void initUI() {
		spriteViews = new ArrayList<>();
		content = new Group();
		Rectangle background = new Rectangle(
				0,
				0,
				UIConstants.CANVAS_STARTING_WIDTH,
				UIConstants.CANVAS_HEIGHT);
		background.setFill(Color.BEIGE);
		content.getChildren().add(background);
		scrollPane = new ScrollPane(content);
		this.addUI(scrollPane);
		
		//debug
		scrollPane.setOnScroll(e -> {
			//System.out.println(scrollPane.getHvalue());
			//System.out.println(scrollPane.getVvalue());
		});
		
		//more debug
		ArrayList<String> path = new ArrayList<String>();
		path.add("turtle.gif");
		ISprite block = new GroundBlock(40, 40, path);
		SpriteView testsp = new SpriteView(this.getController());
		testsp.setSprite(block);
		this.add(testsp, 40, 40);
		
	}

	@Override
	protected void layoutSelf() {
		scrollPane.setPrefHeight(this.getHeight());
		scrollPane.setPrefWidth(this.getWidth());
	}

}
