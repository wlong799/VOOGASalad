package authoring.view.components;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.ResourceBundle;

import authoring.AuthoringController;
import game_object.GameObjectType;
import game_object.constants.GameObjectConstants;

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
	private static ResourceBundle myLanguageResourceBundle;
	private enum ComponentPanelInfo {
		HERO(myLanguageResourceBundle.getString("heroes"), GameObjectType.HERO, new String[][]
				{
			{GameObjectConstants.JAY_FILE, myLanguageResourceBundle.getString("jay"), myLanguageResourceBundle.getString("jayD")},
			{GameObjectConstants.BILL_FILE, myLanguageResourceBundle.getString("bill"), myLanguageResourceBundle.getString("billD")},
			{GameObjectConstants.CHARLES_FILE, myLanguageResourceBundle.getString("charles"), myLanguageResourceBundle.getString("charlesD")},
			{GameObjectConstants.BLUE_SNAIL_FILE, myLanguageResourceBundle.getString("blueSnail"), myLanguageResourceBundle.getString("blueSnailDescription")},
			{GameObjectConstants.ELIZA_FILE, myLanguageResourceBundle.getString("eliza"), myLanguageResourceBundle.getString("elizaDescription")},
			{GameObjectConstants.BOY_FILE, myLanguageResourceBundle.getString("boy"), myLanguageResourceBundle.getString("boyDescription")},
			{GameObjectConstants.CINDERELLA_FILE, myLanguageResourceBundle.getString("cinderella"), myLanguageResourceBundle.getString("cinderellaDescription")},
			{GameObjectConstants.BOY2_FILE, myLanguageResourceBundle.getString("boy2"), myLanguageResourceBundle.getString("boy2Description")}
				}),
		ENEMY(myLanguageResourceBundle.getString("enemies"), GameObjectType.ENEMY, new String[][]
				{
			{GameObjectConstants.ORANGE_MUSHROOM_FILE, myLanguageResourceBundle.getString("mushroom"), myLanguageResourceBundle.getString("mushroomDescription")},
			{GameObjectConstants.RIBBON_PIG_FILE, myLanguageResourceBundle.getString("pig"), myLanguageResourceBundle.getString("pigDescription")},
			{GameObjectConstants.SLIME_FILE, myLanguageResourceBundle.getString("slime"), myLanguageResourceBundle.getString("slimeHero")},
			{GameObjectConstants.ANGRY_PIG_FILE, myLanguageResourceBundle.getString("angry"), myLanguageResourceBundle.getString("angryD")},
			{GameObjectConstants.BEAR_FILE, myLanguageResourceBundle.getString("bear"), myLanguageResourceBundle.getString("bearD")},
			{GameObjectConstants.SNAIL_KING_FILE, myLanguageResourceBundle.getString("king"), myLanguageResourceBundle.getString("kingD")},
			{GameObjectConstants.SNAKE_FILE, myLanguageResourceBundle.getString("snake"), myLanguageResourceBundle.getString("snakeD")},
			{GameObjectConstants.SNOWMAN_FILE, myLanguageResourceBundle.getString("snowman"), myLanguageResourceBundle.getString("snowmanD")},
			{GameObjectConstants.STONEMAN_FILE, myLanguageResourceBundle.getString("stoneman"), myLanguageResourceBundle.getString("stonemanD")},
			{GameObjectConstants.WOOD_FILE, myLanguageResourceBundle.getString("wood"), myLanguageResourceBundle.getString("woodD")}
				}),
		STATIC_BLOCK(myLanguageResourceBundle.getString("blocks"), GameObjectType.STATIC_BLOCK, new String[][]
				{
			{GameObjectConstants.STONE_BLOCK_FILE, myLanguageResourceBundle.getString("stone"), myLanguageResourceBundle.getString("stoneDescription")},
			{GameObjectConstants.BUSH_FILE, myLanguageResourceBundle.getString("bush"), myLanguageResourceBundle.getString("bushDescription")},
			{GameObjectConstants.BRICK_FILE, myLanguageResourceBundle.getString("brick"), myLanguageResourceBundle.getString("brickDescription")},
			{GameObjectConstants.BAR_FILE, myLanguageResourceBundle.getString("bar"), myLanguageResourceBundle.getString("barDescription")},
			{GameObjectConstants.BLACK_AND_GREY_FILE, myLanguageResourceBundle.getString("blackAndGrey"), myLanguageResourceBundle.getString("blackAndGreyDescription")},
			{GameObjectConstants.BLACK_SQUARE_FILE, myLanguageResourceBundle.getString("square"), myLanguageResourceBundle.getString("squareDescription")},
			{GameObjectConstants.BLUE_LIGHT_FILE, myLanguageResourceBundle.getString("blueLight"), myLanguageResourceBundle.getString("blueLightDescription")},
			{GameObjectConstants.BLUE_METAL_FILE, myLanguageResourceBundle.getString("blueMetal"), myLanguageResourceBundle.getString("blueMetalDescription")},
			{GameObjectConstants.CAUTION_ORANGE_FILE, myLanguageResourceBundle.getString("cautionOrange"), myLanguageResourceBundle.getString("cautionOrangeDescription")}, 
			{GameObjectConstants.CAUTION_FILE, myLanguageResourceBundle.getString("cautionOrange2"), myLanguageResourceBundle.getString("cautionOrange2Description")},
			{GameObjectConstants.DIAGONAL_METAL_FILE, myLanguageResourceBundle.getString("diagonal"), myLanguageResourceBundle.getString("diagonalDescription")},
			{GameObjectConstants.DIAGONAL_WITH_BORDER_FILE, myLanguageResourceBundle.getString("diagonalBorder"),myLanguageResourceBundle.getString("diagonalBorderDescription")},
			{GameObjectConstants.DIAGONAL_YELLOW_WITH_BORDER_FILE, myLanguageResourceBundle.getString("diagonalYellow"), myLanguageResourceBundle.getString("diagonalYellowDescription")}
				}),
		POWER_UP("Power Up", GameObjectType.WEAPON_POWER_UP, new String[][]
				{
			{GameObjectConstants.NEW_WEAPON_POWER_UP_FILE, myLanguageResourceBundle.getString("weapon"), myLanguageResourceBundle.getString("weaponDescription"), GameObjectType.WEAPON_POWER_UP.toString()},
			{GameObjectConstants.SPEEDUP_POWER_UP_FILE, myLanguageResourceBundle.getString("speedup"), myLanguageResourceBundle.getString("speedupDescription"), GameObjectType.SPEED_POWER_UP.toString()},
			{GameObjectConstants.HEALTH_POWER_UP_FILE, myLanguageResourceBundle.getString("health"), myLanguageResourceBundle.getString("healthDescription"), GameObjectType.HEALTH_POWER_UP.toString()}
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
		myLanguageResourceBundle = controller.getEnvironment().getLanguageResourceBundle();
		try {
			Constructor<ComponentPanelView> componentPanelViewConstructor =
					ComponentPanelView.class.getDeclaredConstructor(AuthoringController.class);
			componentPanelViewConstructor.setAccessible(true);
			componentPanelView = componentPanelViewConstructor.newInstance(controller);
		} catch (Exception e) {
			System.err.println(myLanguageResourceBundle.getString("reflectionError"));
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
			System.err.println(myLanguageResourceBundle.getString("reflectionError"));
			e.printStackTrace();
			return null;
		}
		Arrays.stream(componentPanelInfo.getComponentInfo()).forEach(componentInfo -> {
			String imagePath = componentInfo[0];
			String title = componentInfo[1];
			String description = componentInfo[2];
			if (componentInfo.length < 4) {
				componentListView.addComponent(imagePath, title, description);
			} else {
				componentListView.addComponent(imagePath, title, description, GameObjectType.valueOf(componentInfo[3]));
			}
		});
		return componentListView;
	}
}
