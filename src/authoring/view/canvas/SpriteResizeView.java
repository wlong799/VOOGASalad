package authoring.view.canvas;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.controller.CanvasController;
import authoring.view.AbstractView;
import authoring.view.IView;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import resources.ResourceBundles;

public class SpriteResizeView extends AbstractView {

    private Line myNorthBorder;
    private Line myWestBorder;
    private Line mySouthBorder;
    private Line myEastBorder;
    private Rectangle myNorthWestResizer;
    private Rectangle myNorthEastResizer;
    private Rectangle mySouthWestResizer;
    private Rectangle mySouthEastResizer;
    private SpriteView mySpriteView;
    private ResourceBundle myComponentProperties;

    public SpriteResizeView(AuthoringController controller) {
        super(controller);
    }

    @Override
    public void setParentView(IView parent) {
        mySpriteView = (SpriteView) parent;
        initUI();
    }

    @Override
    protected void initUI() {
        if (mySpriteView == null) return;
        myComponentProperties = ResourceBundles.componentProperties;
        initSelectionIndicator();
    }

    @Override
    protected void updateLayoutSelf() {
        myNorthBorder.setEndX(this.getWidth());
        myWestBorder.setEndY(this.getHeight());
        myEastBorder.setStartX(this.getWidth());
        myEastBorder.setEndX(this.getWidth());
        myEastBorder.setEndY(this.getHeight());
        mySouthBorder.setStartY(this.getHeight());
        mySouthBorder.setEndX(this.getWidth());
        mySouthBorder.setEndY(this.getHeight());
        myNorthWestResizer.setLayoutX(-Double.parseDouble(myComponentProperties.getString("RESIZE_UNIT_HALF")));
        myNorthWestResizer.setLayoutY(-Double.parseDouble(myComponentProperties.getString("RESIZE_UNIT_HALF")));
        myNorthEastResizer.setLayoutX(this.getWidth() - Double.parseDouble(myComponentProperties.getString("RESIZE_UNIT_HALF")));
        myNorthEastResizer.setLayoutY(-Double.parseDouble(myComponentProperties.getString("RESIZE_UNIT_HALF")));
        mySouthWestResizer.setLayoutX(-Double.parseDouble(myComponentProperties.getString("RESIZE_UNIT_HALF")));
        mySouthWestResizer.setLayoutY(this.getHeight() - Double.parseDouble(myComponentProperties.getString("RESIZE_UNIT_HALF")));
        mySouthEastResizer.setLayoutX(this.getWidth() - Double.parseDouble(myComponentProperties.getString("RESIZE_UNIT_HALF")));
        mySouthEastResizer.setLayoutY(this.getHeight() - Double.parseDouble(myComponentProperties.getString("RESIZE_UNIT_HALF")));
    }

    private void initSelectionIndicator() {
        initBorder();
        initResizers();
    }

    private void initBorder() {
        myNorthBorder = new Line();
        mySouthBorder = new Line();
        myWestBorder = new Line();
        myEastBorder = new Line();
        this.addUIAll(myNorthBorder, mySouthBorder, myWestBorder, myEastBorder);
    }

    private void initResizers() {
        myNorthWestResizer = initResizerRectangle();
        myNorthEastResizer = initResizerRectangle();
        mySouthWestResizer = initResizerRectangle();
        mySouthEastResizer = initResizerRectangle();
        this.addUIAll(myNorthWestResizer, myNorthEastResizer, mySouthWestResizer, mySouthEastResizer);
        setOnHover();
        setOnDishover(myNorthWestResizer, myNorthEastResizer, mySouthWestResizer, mySouthEastResizer);
        setOnDrag();
    }

    private Rectangle initResizerRectangle() {
        Rectangle result = new Rectangle(0, 0, Double.parseDouble(myComponentProperties.getString("RESIZE_UNIT")), Double.parseDouble(myComponentProperties.getString("RESIZE_UNIT")));
        result.setStrokeWidth(Double.parseDouble(myComponentProperties.getString("RESIZER_STROKE_WIDTH")));
        result.setStroke(Color.TRANSPARENT);
        return result;
    }

    private void setOnHover() {
        myNorthWestResizer.setOnMouseEntered(e -> {
            this.getController().setMouseCursor(Cursor.NW_RESIZE);
        });
        myNorthEastResizer.setOnMouseEntered(e -> {
            this.getController().setMouseCursor(Cursor.NE_RESIZE);
        });
        mySouthWestResizer.setOnMouseEntered(e -> {
            this.getController().setMouseCursor(Cursor.SW_RESIZE);
        });
        mySouthEastResizer.setOnMouseEntered(e -> {
            this.getController().setMouseCursor(Cursor.SE_RESIZE);
        });
    }

    private void setOnDishover(Rectangle... resizers) {
        for (Rectangle resizer : resizers) {
            resizer.setOnMouseExited(e -> {
                this.getController().setMouseCursor(Cursor.DEFAULT);
            });
        }
    }

    private void setOnDrag() {
        CanvasController canvasController = this.getController().getCanvasController();
        CanvasView canvas = mySpriteView.getCanvasView();
        mySouthEastResizer.setOnMouseDragged(e -> {
            canvasController.onResizeSpriteView(
                    mySpriteView,
                    mySpriteView.getPositionX(),
                    mySpriteView.getPositionY(),
                    canvasController.toAbsoluteX(e.getSceneX() - canvas.getPositionX()),
                    canvasController.toAbsoluteY(e.getSceneY() - canvas.getPositionY()));
        });
        mySouthWestResizer.setOnMouseDragged(e -> {
            canvasController.onResizeSpriteView(
                    mySpriteView,
                    canvasController.toAbsoluteX(e.getSceneX() - canvas.getPositionX()),
                    mySpriteView.getPositionY(),
                    mySpriteView.getPositionX() + mySpriteView.getWidth(),
                    canvasController.toAbsoluteY(e.getSceneY() - canvas.getPositionY()));
        });
        myNorthEastResizer.setOnMouseDragged(e -> {
            canvasController.onResizeSpriteView(
                    mySpriteView,
                    mySpriteView.getPositionX(),
                    canvasController.toAbsoluteY(e.getSceneY() - canvas.getPositionY()),
                    canvasController.toAbsoluteX(e.getSceneX() - canvas.getPositionX()),
                    mySpriteView.getPositionY() + mySpriteView.getHeight());
        });
        myNorthWestResizer.setOnMouseDragged(e -> {
            canvasController.onResizeSpriteView(
                    mySpriteView,
                    canvasController.toAbsoluteX(e.getSceneX() - canvas.getPositionX()),
                    canvasController.toAbsoluteY(e.getSceneY() - canvas.getPositionY()),
                    mySpriteView.getPositionX() + mySpriteView.getWidth(),
                    mySpriteView.getPositionY() + mySpriteView.getHeight());
        });
    }
}
