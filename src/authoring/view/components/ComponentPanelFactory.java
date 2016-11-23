package authoring.view.components;

import authoring.AuthoringController;
import game_object.GameObjectType;
import game_object.constants.GameObjectConstants;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * ComponentPanelFactory is responsible for creation of the main ComponentPanelView at the bottom of the application. It
 * is responsible for adding the default tabs to appear within the ComponentPanelView, as well as which Components are
 * present in each tab. Designed to increase ease at which the default appearance can be changed.
 *
 * @author Will Long
 * @version 11/21/16
 */
public class ComponentPanelFactory {
    /**
     * ComponentPanelInfo enum holds the information that should be loaded into the ComponentPanelView by default. Each
     * element in the enum represents a specific tab to add to the view. This includes the tab name, the GameObjectType
     * it contains, and information necessary to create a few default options.
     */
    private enum ComponentPanelInfo {
        HERO("Heroes", GameObjectType.HERO, new String[][]
                {
                        {GameObjectConstants.BLUE_SNAIL_FILE, "Blue Snail", "A blue snail hero."},
                        {GameObjectConstants.ELIZA_FILE, "Eliza", "Eliza, a hero."}
                }),
        ENEMY("Enemies", GameObjectType.ENEMY, new String[][]
                {
                        {GameObjectConstants.ORANGE_MUSHROOM_FILE, "Orange Mushroom", "An orange mushroom hero."},
                        {GameObjectConstants.RIBBON_PIG_FILE, "Ribbon Pig", "A ribbon pig hero."},
                        {GameObjectConstants.SLIME_FILE, "Slime", "A slime hero."}
                }),
        STATIC_BLOCK("Blocks", GameObjectType.STATIC_BLOCK, new String[][]
                {
                        {GameObjectConstants.STONE_BLOCK_FILE, "Stone", "A stony block."},
                        {GameObjectConstants.BUSH_FILE, "Bush", "A bush block."},
                        {GameObjectConstants.BRICK_FILE, "Brick", "A brick block."}
                });
        private String myTabName;
        private GameObjectType myGameObjectType;
        private String[][] myComponentInfo;

        ComponentPanelInfo(String tabName, GameObjectType gameObjectType, String[][] componentInfo) {
            myTabName = tabName;
            myGameObjectType = gameObjectType;
            myComponentInfo = componentInfo;
        }

        String getTabName() {
            return myTabName;
        }

        GameObjectType getGameObjectType() {
            return myGameObjectType;
        }

        String[][] getComponentInfo() {
            return myComponentInfo;
        }
    }

    public static ComponentPanelView createComponentPanelView(AuthoringController controller) {
        ComponentPanelView componentPanelView;
        try {
            Constructor<ComponentPanelView> componentPanelViewConstructor =
                    ComponentPanelView.class.getDeclaredConstructor(AuthoringController.class);
            componentPanelViewConstructor.setAccessible(true);
            componentPanelView = componentPanelViewConstructor.newInstance(controller);
        } catch (Exception e) {
            System.err.println("Reflection error.");
            e.printStackTrace();
            return null;
        }
        Arrays.stream(ComponentPanelInfo.values())
                .forEach(componentPanelInfo -> {
                    ComponentListView componentListView = createComponentListView(componentPanelInfo, controller);
                    if (componentListView != null) {
                        componentPanelView.addTab(componentPanelInfo.getTabName(), componentListView);
                    }
                });
        return componentPanelView;
    }

    private static ComponentListView createComponentListView(ComponentPanelInfo componentPanelInfo,
                                                             AuthoringController controller) {
        ComponentListView componentListView;
        try {
            Constructor<ComponentListView> componentListViewConstructor =
                    ComponentListView.class.getDeclaredConstructor(AuthoringController.class, GameObjectType.class);
            componentListViewConstructor.setAccessible(true);
            componentListView = componentListViewConstructor.newInstance(controller,
                    componentPanelInfo.getGameObjectType());
        } catch (Exception e) {
            System.err.println("Reflection error.");
            e.printStackTrace();
            return null;
        }
        Arrays.stream(componentPanelInfo.getComponentInfo()).forEach(componentInfo -> {
            String imagePath = componentInfo[0];
            String title = componentInfo[1];
            String description = componentInfo[2];
            componentListView.addComponent(imagePath, title, description);
        });
        return componentListView;
    }
}
