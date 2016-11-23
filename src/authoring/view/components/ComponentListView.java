package authoring.view.components;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import game_object.GameObjectType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

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

    private static final class ComponentViewCell extends ListCell<ComponentView> {
        @Override
        public void updateItem(ComponentView item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setGraphic(item.getUI());
            }
        }
    }

    private GameObjectType myGameObjectType;
    private ListView<ComponentView> myListView;
    private ObservableList<ComponentView> myComponentList;

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
    }

    @Override
    protected void initUI() {
        myComponentList = FXCollections.observableArrayList();
        myListView = new ListView<>();
        myListView.setItems(myComponentList);
        myListView.setOrientation(Orientation.HORIZONTAL);
        myListView.setCellFactory(list -> new ComponentViewCell());
        addUI(myListView);
    }

    @Override
    protected void updateLayoutSelf() {
        myListView.setPrefWidth(getWidth());
        myListView.setPrefHeight(getHeight());
        getSubViews().forEach(subView -> {
            subView.setHeight(getHeight() * COMPONENT_HEIGHT_RATIO);
            subView.setWidth(Math.max(getWidth() / MAX_NUMBER_COMPONENTS_VIEWED, MIN_COMPONENT_WIDTH));
        });
    }
}
