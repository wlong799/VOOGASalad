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
    private TabPane tabPane;
    private InspectorGameView gameInspector;
    private InspectorLevelView levelInspector;
    private InspectorComponentView componentInspector;
    private InspectorSpriteView spriteInspector;
    private ResourceBundle inspectorProperties;

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

        inspectorProperties = ResourceBundles.inspectorProperties;

        tabPane = new TabPane();
        gameInspector = new InspectorGameView(getController());
        levelInspector = new InspectorLevelView(getController());
        componentInspector = new InspectorComponentView(getController());
        spriteInspector = new InspectorSpriteView(getController());

        addViewsAsTab("Game", gameInspector);
        addViewsAsTab("Level", levelInspector);
        addViewsAsTab("Component", componentInspector);
        addViewsAsTab("Sprite", spriteInspector);

        addUI(tabPane);
    }

    @Override
    protected void updateLayoutSelf() {
        tabPane.setPrefHeight(getHeight());
        tabPane.setPrefWidth(getWidth());
        getSubViews().forEach(subView -> {
            subView.setWidth(this.getWidth());
            subView.setHeight(this.getHeight() -
                    Double.parseDouble(inspectorProperties.getString("OVERLAP_PIXELS_WITH_CHAT")));
        });
    }

    private void updateUI() {
        tabPane.getTabs().clear();
        getSubViews().forEach(subView -> subView.setParentView(null));
        getSubViews().clear();
        if (selectedGame != null) {
            addViewsAsTab("Game", gameInspector);
            gameInspector.setGame(selectedGame);
        }
        if (selectedLevel != null) {
            addViewsAsTab("Level", levelInspector);
            levelInspector.setLevel(selectedLevel);
        }
        if (selectedComponent != null) {
            addViewsAsTab("Component", componentInspector);
            componentInspector.setComponent(selectedComponent);
        }
        if (selectedSpriteView != null) {
            addViewsAsTab("Sprite", spriteInspector);
            spriteInspector.setSpriteView(selectedSpriteView);
        }
        tabPane.getSelectionModel().select(tabPane.getTabs().size()-1);
    }

    private void addViewsAsTab(String tabName, AbstractView view) {
        Tab newTab = new Tab(tabName);
        newTab.getStyleClass().add("tab");
        newTab.setClosable(false);
        newTab.setContent(view.getUI());
        tabPane.getTabs().add(newTab);
        addSubView(view);
    }

    @Override
    public void update(Observable o, Object arg) {
        selectedGame = getController().getEnvironment().getCurrentGame();
        selectedLevel = getController().getEnvironment().getCurrentLevel();
        selectedComponent = getController().getSelectedComponent();
        selectedSpriteView = getController().getSelectedSpriteView();
        updateUI();
    }
}
