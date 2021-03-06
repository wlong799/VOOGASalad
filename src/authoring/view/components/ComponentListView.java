package authoring.view.components;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import game_object.GameObjectType;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import resources.ResourceBundles;

/**
 * ComponentListView organizes multiple Components of the same GameObjectType within a single list, and provides options
 * for adding new components.
 *
 * @author Will Long
 * @version 11/21/16
 */
public class ComponentListView extends AbstractView {

    private GameObjectType myGameObjectType;
    private List<ComponentView> myComponentList;
    private HBox myComponentBox;
    private ResourceBundle myComponentProperties;

    private ComponentListView(AuthoringController controller, GameObjectType gameObjectType) {
        super(controller);
        myGameObjectType = gameObjectType;
        myComponentProperties = ResourceBundles.componentProperties;
    }

    public void addComponent(String imagePath, String title, String description) {
        Component component = new Component(myGameObjectType, imagePath, title, description);
        add(component);
    }
    
    public void addComponent(String imagePath, String title, String description, GameObjectType type) {
        Component component = new Component(type, imagePath, title, description);
        add(component);
    }
    
    public void add(Component component) {
    	ComponentView componentView = new ComponentView(getController());
        componentView.setComponent(component);
        myComponentList.add(componentView);
        addSubView(componentView);
        myComponentBox.getChildren().add(componentView.getUI());
    }
    
    @Override
    public Parent getUI() {
    	return myComponentBox;
    }

    @Override
    protected void initUI() {
        myComponentList = new ArrayList<>();
        myComponentBox = new HBox();
    }

    @Override
    protected void updateLayoutSelf() {
        getSubViews().forEach(subView -> {
            subView.setHeight(getHeight() * Double.parseDouble(myComponentProperties.getString("COMPONENT_HEIGHT_RATIO")));
            subView.setWidth(Math.max(getWidth() / Double.parseDouble(myComponentProperties.getString("MAX_NUMBER_COMPONENTS_VIEWED")), Double.parseDouble(myComponentProperties.getString("MIN_COMPONENT_WIDTH"))));
        });
    }
}
