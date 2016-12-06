package authoring.view.inspector;

import authoring.AuthorEnvironment;
import authoring.AuthoringController;
import authoring.view.AbstractView;
import authoring.view.canvas.SpriteView;
import authoring.view.run.TestGameConfiguringView;
import javafx.scene.Parent;
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
    private TabPane tabs;
    private InspectorSpriteView inspectorSpriteView;
    private TestGameConfiguringView configureView;
    private SpriteView inspectedSpriteView;

    public InspectorView(AuthoringController controller) {
        super(controller);
    }

    @Override
    public Parent getUI() {
        return tabs;
    }

    @Override
    protected void initUI() {
        getController().addObserver(this);
        getController().getEnvironment().addObserver(this);
        tabs = new TabPane();
        inspectorSpriteView = new InspectorSpriteView(this.getController());
        configureView = new TestGameConfiguringView(this.getController());

        configureView.setLevel(getController().getEnvironment().getCurrentLevel());

        addViewsAsTab("Physics Settings", configureView);
        addViewsAsTab("Sprite Inspector", inspectorSpriteView);
    }

    @Override
    protected void updateLayoutSelf() {
        tabs.setPrefHeight(this.getHeight());
        tabs.setPrefWidth(this.getWidth());
        for (AbstractView subView : this.getSubViews()) {
            subView.setWidth(this.getWidth());
            subView.setHeight(this.getHeight() - 30);
        }
    }

    private void updateUI() {
        inspectorSpriteView.setInspectedSpriteView(inspectedSpriteView);
        //CRITICAL: using raw index number here
        //if anyone adds a new tab, notice the index change!!!
        if (inspectedSpriteView == null) {
            tabs.getSelectionModel().select(0);
        } else {
            tabs.getSelectionModel().select(1);
        }
    }

    private void addViewsAsTab(String tabName, AbstractView view) {
        Tab newTab = new Tab(tabName);
        newTab.setClosable(false);
        newTab.setContent(view.getUI());
        tabs.getTabs().add(newTab);
        addSubView(view);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof AuthorEnvironment) {
            configureView.setLevel(getController().getEnvironment().getCurrentLevel());
        }
        if (o instanceof AuthoringController) {
            inspectedSpriteView = ((AuthoringController) o).getSelectedSpriteView();
            updateUI();
        }
    }
}
