package authoring.controller;

import java.util.ArrayList;
import java.util.List;

import authoring.AuthorEnvironment;
import authoring.constants.UIConstants;
import authoring.view.canvas.CanvasView;
import authoring.view.canvas.SpriteView;
import authoring.view.canvas.SpriteViewComparator;
import game_object.block.StaticBlock;
import game_object.core.Dimension;
import game_object.core.ISprite;
import game_object.core.Position;
import game_object.level.Level;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Rectangle;

public class CanvasViewController {
	
	private CanvasView myCanvas;
	private List<SpriteView> spriteViews;
	private ScrollPane myScrollPane;
	private Group myContent; // holder for all SpriteViews
	private Rectangle myBackground;
	private AuthorEnvironment myEnvironment;
	private double scWidth;
	private double scHeight;
	private double bgWidth;
	private double bgHeight;
	private SpriteViewComparator spViewComparator;
	
	public void init(CanvasView canvas, ScrollPane scrollPane, Group content, Rectangle background) {
		myCanvas = canvas;
		myScrollPane = scrollPane;
		myContent = content;
		myBackground = background;
		myEnvironment = canvas.getController().getEnvironment();
		
		spriteViews = new ArrayList<>();
		spViewComparator = new SpriteViewComparator();
		setOnDrag();
		
		initSpriteViews();
	}
	
	/**
	 * method to add a SpriteView to canvas
	 * used for drag and drop from components view
	 * @param spView
	 * @param x
	 * @param y
	 * @param relative if true, x and y are positions on screen instead of real positions
	 */
	public void add(SpriteView spView, double x, double y, boolean relative) {
		spriteViews.add(spView);
		spView.setCanvasView(myCanvas);
		myContent.getChildren().add(spView.getUI());
		if (relative) {
			setRelativePosition(spView, x, y);
		}
		else {
			setAbsolutePosition(spView, x, y);
		}
		this.reorderSpriteViewsWithPositionZ();
	}

	/**
	 * method to set the position of a spView
	 * @param spView to be set
	 * @param x new position X relative to top-left corner
	 * @param y new position Y relative to top-left corner
	 * x and y are not relative to the origin of content!
	 */
	public void setRelativePosition(SpriteView spView, double x, double y) {
		retrieveScrollPaneSize();
		retrieveBackgroundSize();
		double newx = 0, newy = 0;
		if (scWidth > bgWidth) {
			newx = x;
		}
		else {
			newx = toAbsoluteX(x);
		}
		if (scHeight > bgHeight) {
			newy = y;
		}
		else {
			newy = toAbsoluteY(y);
		}
		setAbsolutePosition(spView, newx, newy);
	}
	
	/**
	 * @param spView
	 * @param x
	 * @param y
	 * x and y are absolute
	 */
	public void setAbsolutePosition(SpriteView spView, double x, double y) {
		spView.setPositionX(x);
		spView.setPositionY(y);
		spView.getSprite().getPosition().setX(x);
		spView.getSprite().getPosition().setY(y);
	}
	
	public void setAbsolutePositionZ(SpriteView spView, double z) {
		spView.getSprite().getPosition().setZ(z);
		reorderSpriteViewsWithPositionZ();
	}

	public void onDragSpriteView(SpriteView spView, MouseEvent event) {
		double x = event.getSceneX() - myCanvas.getPositionX();
		double y = event.getSceneY() - myCanvas.getPositionY();
		retrieveScrollPaneSize();
		//if (canAdjustScrollPane(spView)) {
			adjustScrollPane(x, y);
		//}
		this.setRelativePosition(
				spView, 
				x - spView.getMouseOffset().getX(), 
				y - spView.getMouseOffset().getY());
	}
	
	/**
	 * @param spView
	 * @param startX top left corner X
	 * @param startY top left corner Y
	 * @param endX bottom right corner X
	 * @param endY bottom right corner Y
	 * x, y are absolute sprite positions
	 */
	public void onResizeSpriteView(SpriteView spView,
			double startX,
			double startY,
			double endX,
			double endY) {
		if (startX > endX || startY > endY) return;
		this.setAbsolutePosition(spView, startX, startY);
		spView.setDimensionWidth(endX - startX);
		spView.setDimensionHeight(endY - startY);
	}
	
	// MARK: adjuster buttons
	public void expand() {
		myBackground.setWidth(myBackground.getWidth()+UIConstants.SCREEN_CHANGE_INTERVAL);
	}
	
	public void shrink() {
		if (myBackground.getWidth() > UIConstants.CANVAS_STARTING_WIDTH){
			myBackground.setWidth(myBackground.getWidth()-UIConstants.SCREEN_CHANGE_INTERVAL);
		}
	}
	
	// relative positions to absolute
	public double toAbsoluteX(double x) {
		return myScrollPane.getHvalue() * (bgWidth - scWidth) + x;
	}

	public double toAbsoluteY(double y) {
		return myScrollPane.getVvalue() * (bgHeight - scHeight) + y;
	}
	
	private void initSpriteViews() {
		Level currentLevel = myEnvironment.getCurrentLevel();
		if (currentLevel == null) {
			throw new RuntimeException("no current level for canvas");
		}
		double maxRight = Double.MIN_VALUE;
		double maxBot = Double.MIN_VALUE;
		for (ISprite sp : currentLevel.getAllSprites()) {
			SpriteView spView = new SpriteView(myCanvas.getController());
			Dimension dim = new Dimension(sp.getDimension().getWidth(), sp.getDimension().getHeight());
			spView.setSprite(sp);
			this.add(spView, sp.getPosition().getX(), sp.getPosition().getY(), false);
			spView.setDimensionHeight(dim.getHeight());
			spView.setDimensionWidth(dim.getWidth());
			//TODO extract component class to save default dimension height and width for a component
			//avoid setting dimension with image width and height
			maxRight = Math.max(dim.getWidth() + sp.getPosition().getX(), maxRight);
			maxBot = Math.max(dim.getHeight() + sp.getPosition().getY(), maxBot);
		}
		myBackground.setWidth(Math.max(myBackground.getWidth(), maxRight));
		myBackground.setHeight(Math.max(myBackground.getHeight(), maxBot));
	}
	
	private void retrieveScrollPaneSize() {
		scWidth = myScrollPane.getViewportBounds().getWidth();
		scHeight = myScrollPane.getViewportBounds().getHeight();
	}

	private void retrieveBackgroundSize() {
		bgWidth = myBackground.getWidth();
		bgHeight = myBackground.getHeight();
	}
	
	private void reorderSpriteViewsWithPositionZ() {
		spriteViews.sort(spViewComparator);
		double hValue = myScrollPane.getHvalue();
		double vValue = myScrollPane.getVvalue();
		myContent.getChildren().clear();
		myContent.getChildren().add(myBackground);
		for (SpriteView spView : spriteViews) {
			myContent.getChildren().add(spView.getUI());
		}
		myScrollPane.setHvalue(hValue);
		myScrollPane.setVvalue(vValue);
	}
	
	private void setOnDrag() {
		myScrollPane.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY);
				}
				event.consume();
			}
		});
		myScrollPane.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					double x = event.getSceneX() - myCanvas.getPositionX();
					double y = event.getSceneY() - myCanvas.getPositionY();
					makeAndAddSpriteView(db.getString(), x, y);
					success = true;
				}
				event.setDropCompleted(success);
				event.consume();
			}
		});
	}

	/**
	 * @param id
	 * @param x
	 * @param y
	 * x and y are positions relative to screen
	 * TEMPORARY - block is initialized as StaticBlock
	 */
	private void makeAndAddSpriteView(String id, double x, double y) {
		ArrayList<String> path = new ArrayList<String>();
		path.add(id);
		ISprite block = new StaticBlock(new Position(40,40), new Dimension(0, 0), path);
		SpriteView spView = new SpriteView(myCanvas.getController());
		spView.setSprite(block);
		this.add(spView, x - spView.getWidth() / 2, y - spView.getHeight() / 2, true);
		myCanvas.getController().selectSpriteView(spView);
	}
	
	private void adjustScrollPane(double x, double y) {
		if (x < UIConstants.DRAG_SCROLL_THRESHOLD) {
			myScrollPane.setHvalue(Math.max(0, 
					myScrollPane.getHvalue() - UIConstants.SCROLL_VALUE_UNIT));
		}
		if (scWidth - x < UIConstants.DRAG_SCROLL_THRESHOLD) {
			myScrollPane.setHvalue(Math.min(1, 
					myScrollPane.getHvalue() + UIConstants.SCROLL_VALUE_UNIT));
		}
		if (y < UIConstants.DRAG_SCROLL_THRESHOLD) {
			myScrollPane.setVvalue(Math.max(0, 
					myScrollPane.getVvalue() - UIConstants.SCROLL_VALUE_UNIT));
		}
		if (scHeight - y < UIConstants.DRAG_SCROLL_THRESHOLD) {
			myScrollPane.setVvalue(Math.min(1, 
					myScrollPane.getVvalue() + UIConstants.SCROLL_VALUE_UNIT));
		}
	}

}
