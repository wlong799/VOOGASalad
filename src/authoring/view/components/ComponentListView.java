package authoring.view.components;

import java.util.ArrayList;
import java.util.List;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import game_object.GameObjectType;
import javafx.scene.layout.HBox;

/**
 * ComponentListView organizes multiple Components of the same GameObjectType within a single list, and provides options
 * for adding new components.
 *
 * @author Will Long
 * @version 11/21/16
 */
public class ComponentListView extends AbstractView {
    private static final int MAX_NUMBER_COMPONENTS_VIEWED = 10;
    private static final double MIN_COMPONENT_WIDTH = 125;
    private static final double COMPONENT_HEIGHT_RATIO = 0.9;

    private GameObjectType myGameObjectType;
    private List<ComponentView> myComponentList;
    private HBox myComponentBox;

    private ComponentListView(AuthoringController controller, GameObjectType gameObjectType) {
        super(controller);
        myGameObjectType = gameObjectType;
    }

    public void addComponent(String imagePath, String title, String description) {
        Component component = new Component(myGameObjectType, imagePath, title, description);
        ComponentView componentView = new ComponentView(getController());
        componentView.setComponent(component);
        myComponentList.add(componentView);
        addSubView(componentView);
        myComponentBox.getChildren().add(componentView.getUI());
    }

    @Override
    protected void initUI() {
        myComponentList = new ArrayList<>();
        myComponentBox = new HBox();
        addUI(myComponentBox);
    }

    @Override
    protected void updateLayoutSelf() {
        myComponentBox.setPrefWidth(getWidth());
        myComponentBox.setPrefHeight(getHeight());
        getSubViews().forEach(subView -> {
            subView.setHeight(getHeight() * COMPONENT_HEIGHT_RATIO);
            subView.setWidth(Math.max(getWidth() / MAX_NUMBER_COMPONENTS_VIEWED, MIN_COMPONENT_WIDTH));
        });
    }
}
