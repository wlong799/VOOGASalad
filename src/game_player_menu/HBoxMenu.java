package game_player_menu;

import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class HBoxMenu extends MenuSceneGenerator{
	private ResourceBundle myResources;
	
	public HBoxMenu(IMenuInputListener menuListener) {
		super(menuListener);
		myResources = ResourceBundle.getBundle(RESOURCE_FOLDER);
	}

	@Override
	protected Node layoutDescriptions(List<DisplayableItemDescription> menuItems) {
		HBox itemsBox = new HBox();
		itemsBox.setAlignment(Pos.CENTER);
		itemsBox.setSpacing(50);
		for(DisplayableItemDescription currItem : menuItems){
			itemsBox.getChildren().add(currItem.getPaneRepresentation());
		}
		return itemsBox;
	}


}
