package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.view.inspector.settings.ImageChangeButtonView;
import authoring.view.inspector.settings.TextInputBoxView;
import game_object.core.Game;

/**
 * Inspector view that allows for editing of game-wide settings/metadata.
 *
 * @author Will Long
 */
public class InspectorGameView extends AbstractInspectorTabView {
    private Game myGame;

    private TextInputBoxView myTitleInputView, myDescriptionInputView;
    private ImageChangeButtonView myImageChangeButtonView;

    public InspectorGameView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        super.initUI();
        // TODO: 12/7/16 get title from current game
        myTitleInputView = new TextInputBoxView(getController(), "Title", "", newValue -> {
            // TODO: 12/7/16 set game title to new text
        });
        myImageChangeButtonView = new ImageChangeButtonView(getController(), "", newValue -> {
            // TODO: 12/7/16 fix this too
        });
        // TODO: 12/7/16 get description from current game
        myDescriptionInputView = new TextInputBoxView(getController(), "Description", "", newValue -> {
            // TODO: 12/7/16 set game description to new text
        });
        addSettingsViews(myTitleInputView, myImageChangeButtonView, myDescriptionInputView);
    }

    public void setGame(Game game) {
        myGame = game;
        // TODO: 12/8/16 set new values based on game
    }
}