package authoring.view.canvas;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import game_object.core.ISprite;
import game_object.core.ImageStyle;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        if (mySprite.getImgStyle() == ImageStyle.TILE) {
            tileImages();
        } else {
            fitImage();
        }
    }

    private void fitImage() {
        ImageView imageView = new ImageView(myImage);
        imageView.setFitHeight(getHeight());
        imageView.setFitWidth(getWidth());
        myContent.getChildren().add(imageView);
    }

    private void tileImages() {
        double adjustedWidth = getHeight() * (myImage.getWidth() / myImage.getHeight());
        double width;
        for (width = adjustedWidth; width < getWidth(); width += adjustedWidth) {
            ImageView imageView = new ImageView(myImage);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(adjustedWidth);
            myContent.getChildren().add(imageView);
        }
        ImageView imageView = new ImageView(myImage);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(getHeight());
        imageView.setViewport(new Rectangle2D(0, 0, (1 - ((width - getWidth()) / adjustedWidth)) * myImage.getWidth(),
                myImage.getHeight()));
        myContent.getChildren().add(imageView);
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
