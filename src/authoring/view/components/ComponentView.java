package authoring.view.components;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import javafx.geometry.Insets;
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
    private static final double IMAGE_HEIGHT_RATIO = 0.25;
    private static final double IMAGE_WIDTH_RATIO = 0.5;
    private static final double PADDING = 5;
    private static final double TITLE_FONT_SIZE = 24;
    private static final double DESCRIPTION_FONT_SIZE = 12;

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
        double newImageWidth = getWidth() * IMAGE_WIDTH_RATIO;
        double newImageHeight = getHeight() * IMAGE_HEIGHT_RATIO;
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
        myTitleText = createText(TITLE_FONT_SIZE);
        myDescriptionText = createText(DESCRIPTION_FONT_SIZE);
        myTemplateSpriteImageView = new ImageView();
        myTemplateSpriteImageView.setPreserveRatio(true);

        myContentBox = new VBox(PADDING, myTitleText, myTemplateSpriteImageView, myDescriptionText);
        myContentBox.setAlignment(Pos.CENTER);

        addUI(myContentBox);
        setOnDrag();
    }

    private Text createText(double fontSize) {
        Text text = new Text();
        text.setFont(new Font(fontSize));
        text.setTextAlignment(TextAlignment.CENTER);
        return text;
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
