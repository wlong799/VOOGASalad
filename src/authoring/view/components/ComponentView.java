package authoring.view.components;

import authoring.AuthoringController;
import authoring.constants.UIConstants;
import authoring.view.AbstractView;
import authoring.view.canvas.SpriteView;
import game_object.core.ISprite;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * ComponentView displays the important information from Component, including the template sprite's image, the title of
 * the component, and its description.
 */
public class ComponentView extends AbstractView {

    private VBox myContentBox;
    private ImageView myTemplateSpriteImageView;
    private Text myTitleText, myDescriptionText;
    private Component myComponent;

    public ComponentView(AuthoringController controller) {
        super(controller);
    }

    public void setComponent(Component component) {
        myComponent = component;
        myTemplateSpriteImageView.setImage(new Image(myComponent.getImagePath()));
        myTitleText.setText(myComponent.getTitle());
        myDescriptionText.setText(myComponent.getDescription());
    }

    @Override
    protected void updateLayoutSelf() {
        myContentBox.setPrefHeight(getHeight());
        myContentBox.setPrefWidth(getWidth());
        myTitleText.setWrappingWidth(getWidth());
        myDescriptionText.setWrappingWidth(getWidth());
        double newImageWidth = getWidth() * UIConstants.IMAGE_WIDTH_RATIO;
        double newImageHeight = getHeight() * UIConstants.IMAGE_HEIGHT_RATIO;
        if (myTemplateSpriteImageView.getImage().getWidth() / newImageWidth >
                myTemplateSpriteImageView.getImage().getHeight() / newImageHeight) {
            myTemplateSpriteImageView.setFitHeight(0);
            myTemplateSpriteImageView.setFitWidth(newImageWidth);
        } else {
            myTemplateSpriteImageView.setFitWidth(0);
            myTemplateSpriteImageView.setFitHeight(newImageHeight);
        }
    }

    @Override
    protected void initUI() {
        myTitleText = createText(UIConstants.TITLE_FONT_SIZE);
        myDescriptionText = createText(UIConstants.DESCRIPTION_FONT_SIZE);
        myTemplateSpriteImageView = new ImageView();
        myTemplateSpriteImageView.setPreserveRatio(true);

        myContentBox = new VBox(UIConstants.COMPONENT_PADDING, myTitleText, myTemplateSpriteImageView, myDescriptionText);
        myContentBox.setAlignment(Pos.CENTER);

        addUI(myContentBox);
        setOnClick();
        setOnDrag();
    }

    private Text createText(double fontSize) {
        Text text = new Text();
        text.setFont(new Font(fontSize));
        text.setTextAlignment(TextAlignment.CENTER);
        return text;
    }

    private void setOnClick() {
        getUI().setOnMouseClicked(event -> {
            SpriteView spriteView = new SpriteView(getController());
            spriteView.setSprite(myComponent.getTemplateSprite());
            getController().selectSpriteView(spriteView);
        });
    }

    private void setOnDrag() {
        AuthoringController controller = getController();
        getUI().setOnDragDetected(event -> {
            controller.getComponentController().setCurrentlyCopiedSprite(myComponent.copySpriteFromTemplate());

            Dragboard db = myTemplateSpriteImageView.startDragAndDrop(TransferMode.COPY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(myTemplateSpriteImageView.getImage());
            db.setContent(content);
            event.consume();
        });
    }

}
