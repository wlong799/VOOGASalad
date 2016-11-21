package authoring.view.components;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import authoring.constants.UIConstants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * ComponentView displays the important information from Component, including the template sprite's image, the title of
 * the component, and its description.
 */
public class ComponentView extends AbstractView {
    private static final double INNER_PADDING = 5;
    private static final double OUTER_PADDING = 10;
    private static final double TITLE_FONT_SIZE = 20;
    private static final double DESCRIPTION_FONT_SIZE = 12;

    private HBox myContentBox;
    private ImageView myTemplateSpriteImageView;
    private Text myTitleText, myDescriptionText;
    private Component myComponent;

    public ComponentView(AuthoringController controller) {
        super(controller);
    }

    public void setComponent(Component component) {
        myComponent = component;
        updateLayoutSelf();
    }

    @Override
    protected void updateLayoutSelf() {
        myTemplateSpriteImageView.setImage(new Image(myComponent.getImagePath()));
        myTitleText.setText(myComponent.getTitle());
        myDescriptionText.setText(myComponent.getDescription());
    }

    @Override
    protected void initUI() {
        VBox vBox = initializeTextBoxes();
        myTemplateSpriteImageView = new ImageView();
        myContentBox = new HBox(INNER_PADDING, myTemplateSpriteImageView, vBox);
        myContentBox.setAlignment(Pos.CENTER);
        myContentBox.setPrefWidth(getWidth());
        myContentBox.setPrefHeight(getHeight());
        myContentBox.setPadding(new Insets(OUTER_PADDING));
        myTemplateSpriteImageView.setPreserveRatio(true);
        myTemplateSpriteImageView.setFitWidth(
                UIConstants.COMPONENT_WIDTH * UIConstants.COMPONENT_WIDTH_PORTION);
        this.addUI(myContentBox);
        setOnDrag();
    }

    private VBox initializeTextBoxes() {
        myTitleText = new Text();
        myTitleText.setFont(new Font(20));
        myDescriptionText = new Text();
        myDescriptionText.setFont(new Font(12));
        VBox vBox = new VBox(INNER_PADDING, myTitleText, myDescriptionText);
        return vBox;
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
