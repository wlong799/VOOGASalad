package authoring.view.inspector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.view.inspector.settings.ComboBoxSettingsView;
import authoring.view.inspector.settings.ImageChangeButtonView;
import authoring.view.inspector.settings.TextInputBoxView;
import game_engine.enemyai.EnemyLevelTypes;
import game_object.core.Game;

/**
 * Inspector view that allows for editing of game-wide settings/metadata.
 *
 * @author Will Long
 */
public class InspectorGameView extends AbstractInspectorTabView {
	
    private TextInputBoxView myTitleInputView, myDescriptionInputView;
    private ComboBoxSettingsView myDifficultyBox;
    private ImageChangeButtonView myImageChangeButtonView;
    private ResourceBundle myLanguageResourceBundle;
    private Game currentGame;

    public InspectorGameView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        super.initUI();
        if (currentGame == null) {
        	currentGame = this.getController().getEnvironment().getCurrentGame();
        }
        myLanguageResourceBundle = super.getController().getEnvironment().getLanguageResourceBundle();
        // TODO: 12/7/16 get title from current game
        myTitleInputView = new TextInputBoxView(getController(), myLanguageResourceBundle.getString("title"), "", newValue -> {
            // TODO: 12/7/16 set game title to new text
        });
        myImageChangeButtonView = new ImageChangeButtonView(getController(), "", newValue -> {
            // TODO: 12/7/16 fix this too
        });
        // TODO: 12/7/16 get description from current game
        myDescriptionInputView = new TextInputBoxView(getController(), myLanguageResourceBundle.getString("description"), "", newValue -> {
            // TODO: 12/7/16 set game description to new text
        });
        List<String> difficulties = new ArrayList<String>(Arrays.asList(
        		EnemyLevelTypes.EASY.toString(),
        		EnemyLevelTypes.MEDIUM.toString(),
        		EnemyLevelTypes.HARD.toString()));
        myDifficultyBox = new ComboBoxSettingsView(
                getController(),
                myLanguageResourceBundle.getString("enemyDifficulty"),
                currentGame.getEnemyDifficulty().toString(),
                difficulties,
                (obv, oldVal, newVal) -> {
                	currentGame.setEnemyDifficulty(EnemyLevelTypes.valueOf(newVal));
                });
        addSettingsViews(myTitleInputView, myImageChangeButtonView, myDescriptionInputView, myDifficultyBox);
    }
    
    public void setGame(Game game) {
    	currentGame = game;
    }
    
}