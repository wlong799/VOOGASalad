package ui;

import java.util.ArrayList;
import java.util.List;

import game_object.block.GroundBlock;
import game_object.core.ISprite;
import game_object.core.Position;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
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
	public void add(SpriteView spView, double x, double y, boolean relative) {
		spriteViews.add(spView);
		spView.setCanvasView(this);
		content.getChildren().add(spView.getUI());
		if (relative) {
			setRelativePosition(spView, x, y);
		}
		else {
			setAbsolutePosition(spView, x, y);
		}
	}
	
	/**
	 * method to set the position of a spView
	 * @param spView to be set
	 * @param x new position X relative to top-left corner
	 * @param y new position Y relative to top-left corner
	 * x and y are not relative to the origin of content!
	 */
	public void setRelativePosition(SpriteView spView, double x, double y) {
		double scWidth = scrollPane.getViewportBounds().getWidth();
		double scHeight = scrollPane.getViewportBounds().getHeight();
		double bgWidth = background.getWidth();
		double bgHeight = background.getHeight();
		double newx = 0, newy = 0;
		if (scWidth > bgWidth) {
			newx = x;
		}
		else {
			newx = scrollPane.getHvalue() * (bgWidth - scWidth) + x;
		}
		if (scHeight > bgHeight) {
			newy = y;
		}
		else {
			newy = scrollPane.getVvalue() * (bgHeight - scHeight) + y;
		}
		spView.getUI().setLayoutX(newx);
		spView.getUI().setLayoutY(newy);
		spView.getSprite().setPosition(new Position(newx, newy));
	}
	
	/**
	 * @param spView
	 * @param x
	 * @param y
	 * x and y relative to the origin of content
	 */
	public void setAbsolutePosition(SpriteView spView, double x, double y) {
		spView.setPositionX(x);
		spView.setPositionY(y);
		spView.getSprite().setPosition(new Position(x, y));
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
		setOnDrag();
		
		//debug
		scrollPane.setOnScroll(e -> {
			System.out.println(scrollPane.getViewportBounds().getWidth());
		});
		
		//more debug
		ArrayList<String> path = new ArrayList<String>();
		path.add("turtle.gif");
		ISprite block = new GroundBlock(40, 40, path);
		SpriteView testsp = new SpriteView(this.getController());
		testsp.setSprite(block);
		this.add(testsp, 40, 40, false);
	}

	@Override
	protected void layoutSelf() {
		scrollPane.setPrefHeight(this.getHeight());
		scrollPane.setPrefWidth(this.getWidth());
	}
	
	private void setOnDrag() {
		scrollPane.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getDragboard().hasString()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		scrollPane.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasString()) {
		        	//TODO: make this stable
		           double x = event.getSceneX() - UIConstants.LEFT_WIDTH; // dangerous!!
		           double y = event.getSceneY();
		           makeAndAddSpriteView(x, y);
		           success = true;
		        }
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
	}
	
	private void makeAndAddSpriteView(double x, double y) {
		ArrayList<String> path = new ArrayList<String>();
		path.add("turtle.gif");
		ISprite block = new GroundBlock(40, 40, path);
		SpriteView testsp = new SpriteView(this.getController());
		testsp.setSprite(block);
		this.add(testsp, x - 20, y - 20, true);
	}

}
