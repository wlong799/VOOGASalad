package authoring.view.inspector;

import authoring.AuthorEnvironment;
import authoring.AuthoringController;
import authoring.view.inspector.settings.ImageChangeButtonView;
import authoring.view.inspector.settings.TextInputBoxView;

import java.util.Observable;

/**
 * Inspector view that allows for editing of game-wide settings/metadata.
 *
 * @author Will Long
 */
public class InspectorGameView extends AbstractInspectorTabView {
    private TextInputBoxView titleInputView, descriptionInputView;
    private ImageChangeButtonView imageChangeButtonView;


    public InspectorGameView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        super.initUI();
        // TODO: 12/7/16 get title from current game
        titleInputView = new TextInputBoxView(getController(), "Title", "", newValue -> {
            // TODO: 12/7/16 set game title to new text
        });
        imageChangeButtonView = new ImageChangeButtonView(getController(), "", newValue -> {
            // TODO: 12/7/16 fix this too
        });
        // TODO: 12/7/16 get description from current game
        descriptionInputView = new TextInputBoxView(getController(), "Description", "", newValue -> {
            // TODO: 12/7/16 set game description to new text
        });
        addSettingsViews(titleInputView, imageChangeButtonView, descriptionInputView);
    }

    public void update(Observable o, Object arg) {
        if (o instanceof AuthorEnvironment) {
            // TODO: 12/7/16 change values within boxes
        }
    }
}