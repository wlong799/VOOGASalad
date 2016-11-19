package authoring.view.canvas;

import java.util.ArrayList;
import java.util.List;

import authoring.AuthoringController;
import authoring.View;
import authoring.constants.UIConstants;
import game_object.block.StaticBlock;
import game_object.core.ISprite;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

/**
 * Canvas View - editor UI
 * need refactor - extract resize, drag methods to a 'controller'
 * 		using composition
 */
public class CanvasView extends View {

	private List<SpriteView> spriteViews;
	private ScrollPane scrollPane;
	private Group content; // holder for all SpriteViews
	private Rectangle background;
	private double scWidth;
	private double scHeight;
	private double bgWidth;
	private double bgHeight;
	private SpriteViewComparator spViewComparator;

	public CanvasView(AuthoringController controller) {
		super(controller);
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
		double x = event.getSceneX() - this.getPositionX();
		double y = event.getSceneY() - this.getPositionY();
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

	public Rectangle getBackground() {
		return background;
	}

	public double toAbsoluteX(double x) {
		return scrollPane.getHvalue() * (bgWidth - scWidth) + x;
	}

	public double toAbsoluteY(double y) {
		return scrollPane.getVvalue() * (bgWidth - scWidth) + y;
	}

	@Override
	protected void initUI() {
		spriteViews = new ArrayList<>();
		spViewComparator = new SpriteViewComparator();
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
		this.addSubView(new CanvasAdjusterButtonsView(this.getController()));
		setOnDrag();

		//debug
		ArrayList<String> path = new ArrayList<String>();
		path.add("turtle.gif");
		ISprite block = new StaticBlock(40, 40, path);
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
		CanvasView t = this;
		scrollPane.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY);
				}
				event.consume();
			}
		});
		scrollPane.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					double x = event.getSceneX() - t.getPositionX();
					double y = event.getSceneY() - t.getPositionY();
					makeAndAddSpriteView(db.getString(), x, y);
					success = true;
				}
				event.setDropCompleted(success);

				event.consume();
			}
		});
	}

	private void makeAndAddSpriteView(String id, double x, double y) {
		ArrayList<String> path = new ArrayList<String>();
		path.add(id);
		ISprite block = new StaticBlock(40, 40, path);
		SpriteView spView = new SpriteView(this.getController());
		spView.setSprite(block);
		this.add(spView, x - spView.getWidth() / 2, y - spView.getHeight() / 2, true);
		this.getController().selectSpriteView(spView);
	}

	private void retrieveScrollPaneSize() {
		scWidth = scrollPane.getViewportBounds().getWidth();
		scHeight = scrollPane.getViewportBounds().getHeight();
	}

	private void retrieveBackgroundSize() {
		bgWidth = background.getWidth();
		bgHeight = background.getHeight();
	}

	private void adjustScrollPane(double x, double y) {
		if (x < UIConstants.DRAG_SCROLL_THRESHOLD) {
			scrollPane.setHvalue(Math.max(0, 
					scrollPane.getHvalue() - UIConstants.SCROLL_VALUE_UNIT));
		}
		if (scWidth - x < UIConstants.DRAG_SCROLL_THRESHOLD) {
			scrollPane.setHvalue(Math.min(1, 
					scrollPane.getHvalue() + UIConstants.SCROLL_VALUE_UNIT));
		}
		if (y < UIConstants.DRAG_SCROLL_THRESHOLD) {
			scrollPane.setVvalue(Math.max(0, 
					scrollPane.getVvalue() - UIConstants.SCROLL_VALUE_UNIT));
		}
		if (scHeight - y < UIConstants.DRAG_SCROLL_THRESHOLD) {
			scrollPane.setVvalue(Math.min(1, 
					scrollPane.getVvalue() + UIConstants.SCROLL_VALUE_UNIT));
		}
	}

	private boolean canAdjustScrollPane(SpriteView spView) {
		retrieveBackgroundSize();
		double x = spView.getPositionX();
		double y = spView.getPositionY();
		return 0 <= x && x <= bgWidth && 0 <= y && y <= bgHeight;
	}
	
	private void reorderSpriteViewsWithPositionZ() {
		spriteViews.sort(spViewComparator);
		content.getChildren().clear();
		content.getChildren().add(background);
		for (SpriteView spView : spriteViews) {
			content.getChildren().add(spView.getUI());
			System.out.println(spView.getSprite().getPosition().getZ());
		}
	}

}
