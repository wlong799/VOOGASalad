package authoring.view.inspector;

import authoring.AuthorEnvironment;
import authoring.AuthoringController;
import authoring.constants.UIConstants;
import authoring.view.AbstractView;
import authoring.view.canvas.SpriteView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

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

    private SpriteView inspectedSpriteView;

    public InspectorView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        getController().addObserver(this);
        getController().getEnvironment().addObserver(this);

        tabPane = new TabPane();
        gameInspector = new InspectorGameView(getController());
        levelInspector = new InspectorLevelView(getController());
        componentInspector = new InspectorComponentView(getController());
        spriteInspector = new InspectorSpriteView(getController());

        levelInspector.setLevel(getController().getEnvironment().getCurrentLevel());

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
            subView.setWidth(getWidth());
            subView.setHeight(getHeight() - UIConstants.OVERLAP_PIXELS_WITH_CHAT);
        });
    }

    private void updateUI() {
        spriteInspector.setInspectedSpriteView(inspectedSpriteView);
        //CRITICAL: using raw index number here
        //if anyone adds a new tab, notice the index change!!!
        // TODO: 12/5/16 FIX THIS TO WORK WITH NEW TABS
        /*if (inspectedSpriteView == null) {
            tabPane.getSelectionModel().select(0);
        } else {
            tabPane.getSelectionModel().select(1);
        }*/
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
        if (o instanceof AuthorEnvironment) {
            levelInspector.setLevel(getController().getEnvironment().getCurrentLevel());
        }
        if (o instanceof AuthoringController) {
            inspectedSpriteView = ((AuthoringController) o).getSelectedSpriteView();
        }
        updateUI();
    }
}
