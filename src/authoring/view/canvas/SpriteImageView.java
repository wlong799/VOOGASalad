package authoring.view.canvas;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import game_object.core.ISprite;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

public class SpriteImageView extends AbstractView {

    private SpriteView mySpriteView;
    private ISprite mySprite;
    private HBox myContent;
    private Image myImage;

    public SpriteImageView(AuthoringController controller) {
        super(controller);
    }

    @Override
    public void setParentView(AbstractView parent) {
        mySpriteView = (SpriteView) parent;
        mySprite = mySpriteView.getSprite();
        initUI();
    }

    @Override
    protected void initUI() {
        myContent = new HBox();
        addUI(myContent);
        if (mySpriteView != null) {
            String path = mySprite.getImagePath();
            initImageAndSprite(path);
        }
    }

    @Override
    protected void updateLayoutSelf() {
        myContent.setPrefWidth(getWidth());
        myContent.setPrefHeight(getHeight());
        myContent.getChildren().clear();
        Node img = this.getController().getRenderer().
        		render(myImage, mySprite.getImageStyle(), this.getWidth(), this.getHeight());
        myContent.getChildren().add(img);
    }

    private void initImageAndSprite(String path) {
        myImage = new Image(path);
        setWidth(myImage.getWidth());
        setHeight(myImage.getHeight());
        updateLayout();

        mySprite.getDimension().setWidth(myImage.getWidth());
        mySprite.getDimension().setHeight(myImage.getHeight());
    }

    public void setDragMove() {
        this.getUI().setOnMousePressed(event -> {
        	this.getController().selectSpriteView(mySpriteView);
            CanvasView canvas = mySpriteView.getCanvasView();
            mySpriteView.getMouseOffset().setX(
                    this.getController().getCanvasViewController()
                            .toAbsoluteX(event.getSceneX() - canvas.getPositionX()) - mySpriteView.getPositionX());
            mySpriteView.getMouseOffset().setY(
                    this.getController().getCanvasViewController()
                            .toAbsoluteY(event.getSceneY() - canvas.getPositionY()) - mySpriteView.getPositionY());
        });
        this.getUI().setOnMouseDragged(event -> {
            this.getController().getCanvasViewController().onDragSpriteView(mySpriteView, event);
        });
    }

}
