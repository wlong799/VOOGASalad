package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.settings.ImageChangeButton;
import authoring.settings.TextInputBox;
import authoring.view.AbstractView;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * Inspector view that allows for editing of game-wide settings.
 *
 * @author Will Long
 */
public class InspectorGameView extends AbstractView {
    private ScrollPane myScrollPane;
    private VBox myContent;

    public InspectorGameView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        myContent = new VBox();
        myContent.setAlignment(Pos.CENTER);
        myScrollPane = new ScrollPane();
        myScrollPane.setContent(myContent);
        myScrollPane.setFitToWidth(true);
        addUI(myScrollPane);

        // TODO: 12/7/16 get title from current game
        TextInputBox titleInput = new TextInputBox(getController(), "Title", "", newValue -> {
            // TODO: 12/7/16 set game title to new text
        });
        ImageChangeButton imageChangeButton = new ImageChangeButton(getController(), "", newValue -> {
            // TODO: 12/7/16 fix this too
        });
        // TODO: 12/7/16 get description from current game
        TextInputBox descriptionInput = new TextInputBox(getController(), "Description", "", newValue -> {
            // TODO: 12/7/16 set game description to new text
        });
        titleInput.initializeSettings();
        imageChangeButton.initializeSettings();
        descriptionInput.initializeSettings();
        addSubViews(titleInput, imageChangeButton, descriptionInput);
        myContent.getChildren().addAll(titleInput.getUI(), imageChangeButton.getUI(), descriptionInput.getUI());
    }

    @Override
    protected void updateLayoutSelf() {
        myScrollPane.setPrefWidth(getWidth());
        myScrollPane.setPrefHeight(getHeight());
        getSubViews().forEach(subView -> subView.setWidth(getWidth()));
    }
}