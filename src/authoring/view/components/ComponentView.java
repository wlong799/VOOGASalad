package authoring.view.components;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.view.AbstractView;
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
import resources.ResourceBundles;

/**
 * ComponentView displays the important information from Component, including the template sprite's image, the title of
 * the component, and its description.
 */
public class ComponentView extends AbstractView implements Observer {

    private VBox myContentBox;
    private ImageView myTemplateSpriteImageView;
    private Text myTitleText, myDescriptionText;
    private Component myComponent;
    private ResourceBundle myComponentProperties;

    public ComponentView(AuthoringController controller) {
        super(controller);
    }

    public void setComponent(Component component) {
        if (myComponent != null) {
            myComponent.deleteObserver(this);
        }
        myComponent = component;
        myComponent.addObserver(this);
        updateUI();

    }

    @Override
    protected void updateLayoutSelf() {
        myContentBox.setPrefHeight(getHeight());
        myContentBox.setPrefWidth(getWidth());
        myTitleText.setWrappingWidth(getWidth());
        myDescriptionText.setWrappingWidth(getWidth());
        double newImageWidth = getWidth() * Double.parseDouble(myComponentProperties.getString("IMAGE_WIDTH_RATIO"));
        double newImageHeight = getHeight() * Double.parseDouble(myComponentProperties.getString("IMAGE_HEIGHT_RATIO"));
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
    	myComponentProperties = ResourceBundles.componentProperties;
    	myTitleText = createComponentText(Double.parseDouble(myComponentProperties.getString("TITLE_FONT_SIZE")));
        myDescriptionText = createComponentText(Double.parseDouble(myComponentProperties.getString("DESCRIPTION_FONT_SIZE")));

        myTemplateSpriteImageView = new ImageView();
        myTemplateSpriteImageView.setPreserveRatio(true);

        myContentBox = new VBox(Double.parseDouble(myComponentProperties.getString("COMPONENT_PADDING")), myTitleText, myTemplateSpriteImageView, myDescriptionText);
        myContentBox.setAlignment(Pos.CENTER);

        addUI(myContentBox);
        setOnClick();
        setOnDrag();
    }

    private void updateUI() {
        myTemplateSpriteImageView.setImage(new Image(myComponent.getImagePath()));
        myTitleText.setText(myComponent.getTitle());
        myDescriptionText.setText(myComponent.getDescription());
    }

    private Text createComponentText(double fontSize) {
        Text text = new Text();
        text.setFont(new Font(fontSize));
        text.setTextAlignment(TextAlignment.CENTER);
        return text;
    }

    private void setOnClick() {
        getUI().setOnMouseClicked(event -> getController().selectComponent(myComponent));
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

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Component) {
            updateUI();
        }
    }
}
