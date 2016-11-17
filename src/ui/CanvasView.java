package ui;

import java.util.ArrayList;
import java.util.List;

import game_object.block.GroundBlock;
import game_object.core.ISprite;
import game_object.core.Position;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CanvasView extends View {
	
	private List<SpriteView> spriteViews;
	private ScrollPane scrollPane;
	private Group content; // holder for all SpriteViews
	private Rectangle background;

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
		spView.setCanvasView(this);
		content.getChildren().add(spView.getUI());
		setPosition(spView, x, y);
	}
	
	/**
	 * method to set the position of a spView
	 * @param spView to be set
	 * @param x new position X relative to top-left corner
	 * @param y new position Y relative to top-left corner
	 * x and y are not relative to the origin of content!
	 */
	public void setPosition(SpriteView spView, double x, double y) {
		double bgWidth = background.getWidth();
		double bgHeight = background.getHeight();
		double newx = 0, newy = 0;
		newx = scrollPane.getHvalue() * bgWidth + x;
		newy = scrollPane.getVvalue() * bgHeight + y;
		spView.getUI().setLayoutX(newx);
		spView.getUI().setLayoutY(newy);
		spView.getSprite().setPosition(new Position(newx, newy));
	}
	
	@Override
	protected void initUI() {
		spriteViews = new ArrayList<>();
		content = new Group();
		background = new Rectangle(
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
