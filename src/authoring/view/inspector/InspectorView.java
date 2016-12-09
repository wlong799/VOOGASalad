package authoring.view.inspector;

import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import authoring.view.canvas.SpriteView;
import authoring.view.components.Component;
import game_object.core.Game;
import game_object.level.Level;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import resources.ResourceBundles;

import java.util.Observable;
import java.util.Observer;

/**
 * Inspector view allows users to edit various settings within the game environment, including overall game settings,
 * level settings, component-wide settings, and individual sprite settings. The settings available depend on what
 * element in the environment is currently selected.
 */
public class InspectorView extends AbstractView implements Observer {
    private TabPane myTabPane;
    private InspectorGameView myGameInspector;
    private InspectorLevelView myLevelInspector;
    private InspectorComponentView myComponentInspector;
    private InspectorSpriteView mySpriteInspector;
    private ResourceBundle myInspectorProperties;

    private Game selectedGame;
    private Level selectedLevel;
    private Component selectedComponent;
    private SpriteView selectedSpriteView;

    public InspectorView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        getController().addObserver(this);
        getController().getEnvironment().addObserver(this);

        myInspectorProperties = ResourceBundles.inspectorProperties;

        myTabPane = new TabPane();
        myGameInspector = new InspectorGameView(getController());
        myLevelInspector = new InspectorLevelView(getController());
        myComponentInspector = new InspectorComponentView(getController());
        mySpriteInspector = new InspectorSpriteView(getController());

        addUI(myTabPane);
    }

    @Override
    protected void updateLayoutSelf() {
        myTabPane.setPrefHeight(getHeight());
        myTabPane.setPrefWidth(getWidth());
        getSubViews().forEach(subView -> {
            subView.setWidth(getWidth());
            subView.setHeight(getHeight() -
                    Double.parseDouble(myInspectorProperties.getString("OVERLAP_PIXELS_WITH_CHAT")));
        });
    }

    private void updateUI() {
        myTabPane.getTabs().clear();
        getSubViews().forEach(subView -> subView.setParentView(null));
        getSubViews().clear();
        if (selectedGame != null) {
            addViewsAsTab("Game", myGameInspector);
            myGameInspector.setGame(selectedGame);
        }
        if (selectedLevel != null) {
            addViewsAsTab("Level", myLevelInspector);
            myLevelInspector.setLevel(selectedLevel);
        }
        if (selectedComponent != null) {
            addViewsAsTab("Component", myComponentInspector);
            myComponentInspector.setComponent(selectedComponent);
        }
        if (selectedSpriteView != null) {
            addViewsAsTab("Sprite", mySpriteInspector);
            mySpriteInspector.setSpriteView(selectedSpriteView);
        }
        myTabPane.getSelectionModel().select(myTabPane.getTabs().size() - 1);
        updateLayout();
    }

    private void addViewsAsTab(String tabName, AbstractView view) {
        Tab newTab = new Tab(tabName);
        newTab.getStyleClass().add("tab");
        newTab.setClosable(false);
        newTab.setContent(view.getUI());
        myTabPane.getTabs().add(newTab);
        addSubView(view);
    }

    @Override
    public void update(Observable o, Object arg) {
        selectedGame = getController().getEnvironment().getCurrentGame();
        selectedLevel = getController().getEnvironment().getCurrentLevel();
        selectedComponent = getController().getSelectedComponent();
        selectedSpriteView = getController().getMySelectedSpriteView();

        updateUI();
    }
}
