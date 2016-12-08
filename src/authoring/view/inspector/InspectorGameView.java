package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.view.inspector.settings.ImageChangeButtonView;
import authoring.view.inspector.settings.TextInputBoxView;

/**
 * Inspector view that allows for editing of game-wide settings/metadata.
 *
 * @author Will Long
 */
public class InspectorGameView extends AbstractInspectorTabView {
    public InspectorGameView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        super.initUI();
        // TODO: 12/7/16 get title from current game
        TextInputBoxView titleInput = new TextInputBoxView(getController(), "Title", "", newValue -> {
            // TODO: 12/7/16 set game title to new text
        });
        ImageChangeButtonView imageChangeButtonView = new ImageChangeButtonView(getController(), "", newValue -> {
            // TODO: 12/7/16 fix this too
        });
        // TODO: 12/7/16 get description from current game
        TextInputBoxView descriptionInput = new TextInputBoxView(getController(), "Description", "", newValue -> {
            // TODO: 12/7/16 set game description to new text
        });
        addSettingsViews(titleInput, imageChangeButtonView, descriptionInput);
    }
}