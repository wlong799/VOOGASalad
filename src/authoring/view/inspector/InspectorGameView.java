package authoring.view.inspector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.view.inspector.settings.ComboBoxSettingsView;
import authoring.view.inspector.settings.ImageChangeButtonView;
import authoring.view.inspector.settings.ReorderableListView;
import authoring.view.inspector.settings.TextInputBoxView;
import game_engine.enemyai.EnemyLevelTypes;
import game_object.core.Game;
import game_object.level.Level;

/**
 * Inspector view that allows for editing of game-wide settings/metadata.
 *
 * @author Will Long
 */
public class InspectorGameView extends AbstractInspectorTabView {

    private TextInputBoxView myTitleInputView, myDescriptionInputView;
    private ComboBoxSettingsView myDifficultyBox;
    private ImageChangeButtonView myImageChangeButtonView;
    private ReorderableListView<Level> myLevelReorderListView;
    private ResourceBundle myLanguageResourceBundle;
    private Game currentGame;

    public InspectorGameView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        super.initUI();
        currentGame = getController().getEnvironment().getCurrentGame();
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
        myLevelReorderListView = new ReorderableListView<>(getController(),
                myLanguageResourceBundle.getString("CHANGE_LEVEL_ORDER"),
                getController().getEnvironment().getCurrentGame().getAllLevels());
        List<String> difficulties = new ArrayList<>(Arrays.asList(
                EnemyLevelTypes.EASY.toString(),
                EnemyLevelTypes.MEDIUM.toString(),
                EnemyLevelTypes.HARD.toString()));
        myDifficultyBox = new ComboBoxSettingsView(
                getController(),
                myLanguageResourceBundle.getString("ENEMY_DIFFICULTY"),
                currentGame.getEnemyDifficulty().toString(),
                difficulties,
                (obv, oldVal, newVal) -> currentGame.setEnemyDifficulty(EnemyLevelTypes.valueOf(newVal)));
        addSettingsViews(myTitleInputView, myImageChangeButtonView, myDescriptionInputView, myLevelReorderListView,
                myDifficultyBox);
    }

    private void updateUI() {
        myDifficultyBox.setBoxSelection(currentGame.getEnemyDifficulty().toString());
        myLevelReorderListView.setList(currentGame.getAllLevels());
        updateLayout();
    }

    public void setGame(Game game) {
        currentGame = game;
        updateUI();
    }

}