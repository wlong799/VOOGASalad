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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HBoxMenu extends MenuSceneGenerator{
	private ResourceBundle myResources;
	private HBox myItemsBox;
	
	public HBoxMenu(IMenuInputListener menuListener, Stage s) {
		super(menuListener,s);
		myResources = ResourceBundle.getBundle(RESOURCE_FOLDER);
	}

	@Override
	protected Node layoutDescriptions(List<DisplayableItemDescription> menuItems) {
		myItemsBox = new HBox();
		Double paneWidth = getMaxItemWidth(menuItems);
		myItemsBox.setAlignment(Pos.CENTER);
		myItemsBox.setSpacing(Double.parseDouble(myResources.getString("ItemSpacing")));
		for(DisplayableItemDescription currItem : menuItems){
			Pane itemPane = currItem.getPaneRepresentation();
			itemPane.setPrefWidth(paneWidth);
			myItemsBox.getChildren().add(currItem.getPaneRepresentation());
		}
		return myItemsBox;
	}
	
	private Double getMaxItemWidth(List<DisplayableItemDescription> menuItems){
		Double maxWidth = Double.MIN_VALUE;
		for(DisplayableItemDescription item : menuItems){
			Double width = item.getPaneRepresentation().getMaxWidth();
			if(width > maxWidth){
				maxWidth = width;
			}
		}
		return maxWidth;
	}

	@Override
	protected void displayNewItem(DisplayableItemDescription item) {
		myItemsBox.getChildren().add(item.getPaneRepresentation());
	}


}
