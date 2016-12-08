package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.ui.TextInputBox;
import authoring.view.AbstractView;
import game_object.character.Hero;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
        TextInputBox titleInput = new TextInputBox("Title", "", newValue -> {
            // TODO: 12/7/16 set game title to new text
        });
        // TODO: 12/7/16 get description from current game
        TextInputBox descriptionInput = new TextInputBox("Description", "", newValue -> {
            // TODO: 12/7/16 set game description to new text
        });
        myContent.getChildren().addAll(titleInput.getTextInputBox(), descriptionInput.getTextInputBox());
    }

    @Override
    protected void updateLayoutSelf() {
        myScrollPane.setPrefWidth(getWidth());
        myScrollPane.setPrefHeight(getHeight());
        getSubViews().forEach(subView -> subView.setWidth(getWidth()));
    }
}