package authoring.view.canvas;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.controller.CanvasViewController;
import authoring.view.AbstractView;
import authoring.view.IView;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import resources.ResourceBundles;

public class SpriteResizeView extends AbstractView {

    private Line borderN;
    private Line borderW;
    private Line borderS;
    private Line borderE;
    private Rectangle resizeNW;
    private Rectangle resizeNE;
    private Rectangle resizeSW;
    private Rectangle resizeSE;
    private SpriteView spView;
    private ResourceBundle componentProperties;

    public SpriteResizeView(AuthoringController controller) {
        super(controller);
    }

    @Override
    public void setParentView(IView parent) {
        spView = (SpriteView) parent;
        initUI();
    }

    @Override
    protected void initUI() {
        if (spView == null) return;
        componentProperties = ResourceBundles.componentProperties;
        initSelectionIndicator();
    }

    @Override
    protected void updateLayoutSelf() {
        borderN.setEndX(this.getWidth());
        borderW.setEndY(this.getHeight());
        borderE.setStartX(this.getWidth());
        borderE.setEndX(this.getWidth());
        borderE.setEndY(this.getHeight());
        borderS.setStartY(this.getHeight());
        borderS.setEndX(this.getWidth());
        borderS.setEndY(this.getHeight());
        resizeNW.setLayoutX(-Double.parseDouble(componentProperties.getString("RESIZE_UNIT_HALF")));
        resizeNW.setLayoutY(-Double.parseDouble(componentProperties.getString("RESIZE_UNIT_HALF")));
        resizeNE.setLayoutX(this.getWidth() - Double.parseDouble(componentProperties.getString("RESIZE_UNIT_HALF")));
        resizeNE.setLayoutY(-Double.parseDouble(componentProperties.getString("RESIZE_UNIT_HALF")));
        resizeSW.setLayoutX(-Double.parseDouble(componentProperties.getString("RESIZE_UNIT_HALF")));
        resizeSW.setLayoutY(this.getHeight() - Double.parseDouble(componentProperties.getString("RESIZE_UNIT_HALF")));
        resizeSE.setLayoutX(this.getWidth() - Double.parseDouble(componentProperties.getString("RESIZE_UNIT_HALF")));
        resizeSE.setLayoutY(this.getHeight() - Double.parseDouble(componentProperties.getString("RESIZE_UNIT_HALF")));
    }

    private void initSelectionIndicator() {
        initBorder();
        initResizers();
    }

    private void initBorder() {
        borderN = new Line();
        borderS = new Line();
        borderW = new Line();
        borderE = new Line();
        this.addUIAll(borderN, borderS, borderW, borderE);
    }

    private void initResizers() {
        resizeNW = initResizerRectangle();
        resizeNE = initResizerRectangle();
        resizeSW = initResizerRectangle();
        resizeSE = initResizerRectangle();
        this.addUIAll(resizeNW, resizeNE, resizeSW, resizeSE);
        setOnHover();
        setOnDishover(resizeNW, resizeNE, resizeSW, resizeSE);
        setOnDrag();
    }

    private Rectangle initResizerRectangle() {
        Rectangle result = new Rectangle(0, 0, Double.parseDouble(componentProperties.getString("RESIZE_UNIT")), Double.parseDouble(componentProperties.getString("RESIZE_UNIT")));
        result.setStrokeWidth(Double.parseDouble(componentProperties.getString("RESIZER_STROKE_WIDTH")));
        result.setStroke(Color.TRANSPARENT);
        return result;
    }

    private void setOnHover() {
        resizeNW.setOnMouseEntered(e -> {
            this.getController().setMouseCursor(Cursor.NW_RESIZE);
        });
        resizeNE.setOnMouseEntered(e -> {
            this.getController().setMouseCursor(Cursor.NE_RESIZE);
        });
        resizeSW.setOnMouseEntered(e -> {
            this.getController().setMouseCursor(Cursor.SW_RESIZE);
        });
        resizeSE.setOnMouseEntered(e -> {
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
        CanvasViewController canvasController = this.getController().getCanvasViewController();
        CanvasView canvas = spView.getCanvasView();
        resizeSE.setOnMouseDragged(e -> {
            canvasController.onResizeSpriteView(
                    spView,
                    spView.getPositionX(),
                    spView.getPositionY(),
                    canvasController.toAbsoluteX(e.getSceneX() - canvas.getPositionX()),
                    canvasController.toAbsoluteY(e.getSceneY() - canvas.getPositionY()));
        });
        resizeSW.setOnMouseDragged(e -> {
            canvasController.onResizeSpriteView(
                    spView,
                    canvasController.toAbsoluteX(e.getSceneX() - canvas.getPositionX()),
                    spView.getPositionY(),
                    spView.getPositionX() + spView.getWidth(),
                    canvasController.toAbsoluteY(e.getSceneY() - canvas.getPositionY()));
        });
        resizeNE.setOnMouseDragged(e -> {
            canvasController.onResizeSpriteView(
                    spView,
                    spView.getPositionX(),
                    canvasController.toAbsoluteY(e.getSceneY() - canvas.getPositionY()),
                    canvasController.toAbsoluteX(e.getSceneX() - canvas.getPositionX()),
                    spView.getPositionY() + spView.getHeight());
        });
        resizeNW.setOnMouseDragged(e -> {
            canvasController.onResizeSpriteView(
                    spView,
                    canvasController.toAbsoluteX(e.getSceneX() - canvas.getPositionX()),
                    canvasController.toAbsoluteY(e.getSceneY() - canvas.getPositionY()),
                    spView.getPositionX() + spView.getWidth(),
                    spView.getPositionY() + spView.getHeight());
        });
    }
}
