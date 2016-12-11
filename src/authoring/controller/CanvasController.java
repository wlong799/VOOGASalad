package authoring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import authoring.AuthorEnvironment;
import authoring.AuthoringController;
import authoring.share.ShareEditController;
import authoring.view.canvas.CanvasView;
import authoring.view.canvas.SpriteView;
import authoring.view.canvas.SpriteViewComparator;
import game_object.core.Dimension;
import game_object.core.ISprite;
import game_object.level.Level;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import resources.ResourceBundles;

/**
 * @author billyu Controller for canvas
 */
public class CanvasController implements Observer {

    private AuthoringController myController;
    private ShareEditController myShareEditor;
    private CanvasView myCanvas;
    private Map<Long, SpriteView> spriteViews;
    private ScrollPane myScrollPane;
    private Group myContent; // holder for all SpriteViews
    private HBox myBackground;
    private AuthorEnvironment myEnvironment;
    private double mySceneWidth;
    private double mySceneHeight;
    private double myBackgroundWidth;
    private double myBackgroundHeight;
    private boolean myDoesSnap;
    private SpriteViewComparator mySpriteViewComparator;
    private ResourceBundle myCanvasProperties;
    private ResourceBundle myLanguageProperties;
  
    private static final double BLOCK_SIZE = 50;

    public void init(CanvasView canvas, ScrollPane scrollPane, Group content, HBox background) {
        myController = canvas.getController();
        myShareEditor = myController.getNetworkController().getShareEditor();
        myCanvasProperties = ResourceBundles.canvasProperties;
    	myCanvas = canvas;
        myScrollPane = scrollPane;
        myContent = content;
        myBackground = background;
        myEnvironment = canvas.getController().getEnvironment();
        myEnvironment.addObserver(this);
        myLanguageProperties = myEnvironment.getLanguageResourceBundle();

        spriteViews = new HashMap<>();
        mySpriteViewComparator = new SpriteViewComparator();
        setOnDrag();

        initSpriteViews();
    }

    /**
     * refresh sprite views
     * called after user selects a different level
     */
    public void refresh() {
        initSpriteViews();
    }
    
    /**
     * @param x
     * @param y x and y are positions relative to screen
     * @param share if the add action should be passed to the network
     */
    public void addSpriteView(ISprite sprite, double x, double y, long id, boolean share) {
    	SpriteView spView = myController.getComponentController().makeSpriteViewFromSprite(
    			myCanvas,
    			sprite,
    			id);
        myEnvironment.getCurrentLevel().addSprite(sprite);
        if (share) {
        	myShareEditor.add(spView, x, y);
        }
        add(spView, x - spView.getWidth() / 2, y - spView.getHeight() / 2, true);
    }

    /**
     * @param spView to delete
     * @param share if the add action should be passed to the network
     * delete a SpriteView
     */
    public void delete(SpriteView spView, boolean share) {
        if (spView == null) return;
        spriteViews.remove(spView.getID());
        myEnvironment.getCurrentLevel().removeSprite(spView.getSprite());
        this.reorderSpriteViewsWithPositionZ();
        if (share) {
        	myShareEditor.remove(spView);
        }
    }

    /**
     * method to set the position of a spView
     *
     * @param spView to be set
     * @param x      new position X relative to top-left corner
     * @param y      new position Y relative to top-left corner
     *               x and y are not relative to the origin of content!
     */
    public void setRelativePosition(SpriteView spView, double x, double y) {
        retrieveScrollPaneSize();
        retrieveBackgroundSize();
        double newx = 0, newy = 0;
        if (mySceneWidth > myBackgroundWidth) {
            newx = x;
        } else {
            newx = toAbsoluteX(x);
        }
        if (mySceneHeight > myBackgroundHeight) {
            newy = y;
        } else {
            newy = toAbsoluteY(y);
        }
        setAbsolutePosition(spView, newx, newy, true);
    }

    /**
     * @param spView
     * @param x
     * @param y      x and y are absolute
     * @param share if the action should be passed to network
     */
    public void setAbsolutePosition(SpriteView spView, double x, double y, boolean share) {
        spView.setAbsolutePositionX(x);
        spView.setAbsolutePositionY(y);
        if (share) {
        	myShareEditor.move(spView, x, y);
        }
    }

    public void setAbsolutePositionZ(SpriteView spView, double z) {
        spView.setAbsolutePositionZ(z);
    }

    public void onDragSpriteView(SpriteView spView, MouseEvent event) {
        double x = event.getSceneX() - myCanvas.getPositionX();
        double y = event.getSceneY() - myCanvas.getPositionY();
        retrieveScrollPaneSize();
        adjustScrollPane(x, y);
        this.setRelativePosition(
                spView,
                x - spView.getMouseOffset().getX(),
                y - spView.getMouseOffset().getY());
    }

    /**
     * @param spView
     * @param startX top left corner X
     * @param startY top left corner Y
     * @param endX   bottom right corner X
     * @param endY   bottom right corner Y
     *               x, y are absolute sprite positions
     */
    public void onResizeSpriteView(SpriteView spView,
                                   double startX,
                                   double startY,
                                   double endX,
                                   double endY) {
        if (startX > endX || startY > endY) return;
        this.setAbsolutePosition(spView, startX, startY, true);
        spView.setDimensionWidth(endX - startX, true);
        spView.setDimensionHeight(endY - startY, true);
    }

    public void reorderSpriteViewsWithPositionZ() {
    	List<SpriteView> views = spriteViews.values()
    			.stream()
    			.sorted(mySpriteViewComparator)
    			.collect(Collectors.toList());
        double hValue = myScrollPane.getHvalue();
        double vValue = myScrollPane.getVvalue();
        clearSpriteViews(false);
        for (SpriteView spView : views) {
            myContent.getChildren().add(spView.getUI());
        }
        myScrollPane.setHvalue(hValue);
        myScrollPane.setVvalue(vValue);
    }

    public void expand() {
        double width = myEnvironment.getCurrentLevel().getLevelDimension().getWidth();
        width += Double.parseDouble(myCanvasProperties.getString("SCREEN_CHANGE_INTERVAL"));
        myEnvironment.getCurrentLevel().getLevelDimension().setWidth(width);
        updateBackground();
    }

    public void shrink() {
        double width = myEnvironment.getCurrentLevel().getLevelDimension().getWidth();
        width -= Double.parseDouble(myCanvasProperties.getString("SCREEN_CHANGE_INTERVAL"));
        myEnvironment.getCurrentLevel().getLevelDimension().setWidth(width);
        updateBackground();
    }

    public void taller() {
        double height = myEnvironment.getCurrentLevel().getLevelDimension().getHeight();
        height += Double.parseDouble(myCanvasProperties.getString("SCREEN_CHANGE_INTERVAL"));
        myEnvironment.getCurrentLevel().getLevelDimension().setHeight(height);
        updateBackground();
    }

    public void shorter() {
        double height = myEnvironment.getCurrentLevel().getLevelDimension().getHeight();
        height -= Double.parseDouble(myCanvasProperties.getString("SCREEN_CHANGE_INTERVAL"));
        myEnvironment.getCurrentLevel().getLevelDimension().setHeight(height);
        updateBackground();
    }

    // relative positions to absolute
    public double toAbsoluteX(double x) {
        return myScrollPane.getHvalue() * (myBackgroundWidth - mySceneWidth) + x;
    }

    public double toAbsoluteY(double y) {
        return myScrollPane.getVvalue() * (myBackgroundHeight - mySceneHeight) + y;
    }
    
    public double convertToNearestBlockValue(double value) {
        return Math.round(value / BLOCK_SIZE) * BLOCK_SIZE;
    }
    
    //ID
    public SpriteView getSpriteViewWithID(long id) {
    	return spriteViews.get(id);
    }

    private void initSpriteViews() {
        clearSpriteViews(true);
        Level currentLevel = myEnvironment.getCurrentLevel();
        if (currentLevel == null) {
            throw new RuntimeException(myLanguageProperties.getString("noLevel"));
        }
        for (ISprite sp : currentLevel.getAllSprites()) {
        	long newID = myController.getNetworkController().getIDManager().getNextID();
            SpriteView spView = new SpriteView(
            		myCanvas.getController(), 
            		newID);
            Dimension dim = new Dimension(sp.getDimension().getWidth(), sp.getDimension().getHeight());
            spView.setSprite(sp);
            spView.setDimensionHeight(dim.getHeight(), false);
            spView.setDimensionWidth(dim.getWidth(), false);
            add(spView, sp.getPosition().getX(), sp.getPosition().getY(), false);
        }
        updateBackground();
    }

    private void retrieveScrollPaneSize() {
        mySceneWidth = myScrollPane.getViewportBounds().getWidth();
        mySceneHeight = myScrollPane.getViewportBounds().getHeight();
    }

    private void retrieveBackgroundSize() {
        myBackgroundWidth = myBackground.getWidth();
        myBackgroundHeight = myBackground.getHeight();
    }

    private void setOnDrag() {
        myScrollPane.setOnDragOver(event -> {
            if (event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });
        myScrollPane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasImage()) {
                double x = event.getSceneX() - myCanvas.getPositionX();
                double y = event.getSceneY() - myCanvas.getPositionY();
                ISprite copiedSprite = myController.getComponentController().getCurrentlyCopiedSprite();
                SpriteView spView = myController.getComponentController().makeSpriteViewFromSprite(
                		myCanvas, 
                		copiedSprite, 
                		myController.getNetworkController().getIDManager().getNextID());
                addSpriteView(copiedSprite, x, y, spView.getID(), true);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }
    
    /**
     * method to add a SpriteView to canvas
     * purely frontend (does not modify current level)
     * used for drag and drop from components view
     *
     * @param spView
     * @param x
     * @param y
     * @param relative if true, x and y are positions on screen instead of real positions
     */
    private void add(SpriteView spView, double x, double y, boolean relative) {
        spriteViews.put(spView.getID(), spView);
        spView.setCanvasView(myCanvas);
        myContent.getChildren().add(spView.getUI());
        if (relative) {
            setRelativePosition(spView, x, y);
        } else {
            setAbsolutePosition(spView, x, y, true);
        }
        reorderSpriteViewsWithPositionZ();
        if (myDoesSnap) {
            spView.snapToGrid();
        }
    }

    private void adjustScrollPane(double x, double y) {
        if (x < Double.parseDouble(myCanvasProperties.getString("DRAG_SCROLL_THRESHOLD"))) {
            myScrollPane.setHvalue(Math.max(0,
                    myScrollPane.getHvalue() - Double.parseDouble(myCanvasProperties.getString("SCROLL_VALUE_UNIT"))));
        }
        if (mySceneWidth - x < Double.parseDouble(myCanvasProperties.getString("DRAG_SCROLL_THRESHOLD"))) {
            myScrollPane.setHvalue(Math.min(1,
                    myScrollPane.getHvalue() + Double.parseDouble(myCanvasProperties.getString("SCROLL_VALUE_UNIT"))));
        }
        if (y < Double.parseDouble(myCanvasProperties.getString("DRAG_SCROLL_THRESHOLD"))) {
            myScrollPane.setVvalue(Math.max(0,
                    myScrollPane.getVvalue() - Double.parseDouble(myCanvasProperties.getString("SCROLL_VALUE_UNIT"))));
        }
        if (mySceneHeight - y < Double.parseDouble(myCanvasProperties.getString("DRAG_SCROLL_THRESHOLD"))) {
            myScrollPane.setVvalue(Math.min(1,
                    myScrollPane.getVvalue() + Double.parseDouble(myCanvasProperties.getString("SCROLL_VALUE_UNIT"))));
        }
    }

    /**
     * @param data if spriteViews get cleared also
     */
    private void clearSpriteViews(boolean data) {
        myContent.getChildren().clear();
        myContent.getChildren().add(myBackground);
        if (data) {
            spriteViews.clear();
        }
    }

    private void updateBackground() {
        myBackground.getChildren().clear();
        double width = myEnvironment.getCurrentLevel().getLevelDimension().getWidth();
        double height = myEnvironment.getCurrentLevel().getLevelDimension().getHeight();
        if (myEnvironment.getCurrentLevel().getBackground().getImagePaths().size() == 0) {
            Rectangle rectangle = new Rectangle(0, 0, width, height);
            rectangle.setFill(Color.LIGHTCYAN);
            myBackground.getChildren().add(rectangle);
        } else {
            Image backgroundImage = new Image(myEnvironment.getCurrentLevel().getBackground().getImagePaths().get(0));
            double adjustedWidth = height * (backgroundImage.getWidth() / backgroundImage.getHeight());
            double usedWidth;
            for (usedWidth = adjustedWidth; usedWidth < width; usedWidth += adjustedWidth) {
                ImageView imageView = new ImageView(backgroundImage);
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(adjustedWidth);
                myBackground.getChildren().add(imageView);
            }
            ImageView imageView = new ImageView(backgroundImage);
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(height);
            imageView.setViewport(new Rectangle2D(0, 0,
                    (1 - ((usedWidth - width) / adjustedWidth)) * backgroundImage.getWidth(),
                    backgroundImage.getHeight()));
            myBackground.getChildren().add(imageView);
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        refresh();
    }

    public void setSnapToGrid(boolean doesSnap) {
        myDoesSnap = doesSnap;
    }

    public boolean getSnapToGrid() {
        return myDoesSnap;
    }
    
}
