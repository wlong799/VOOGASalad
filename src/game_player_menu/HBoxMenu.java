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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import resources.ResourceBundles;

public class HBoxMenu extends MenuSceneGenerator{
	protected ResourceBundle myResources = ResourceBundles.languageProperties;
	private HBox myItemsBox;
	private ScrollPane myScrollPane;
	private List<DisplayableItemDescription> myMenuItems;
	private Double myMaxItemWidth;
	
	public HBoxMenu(IMenuInputListener menuListener, Stage s) {
		super(menuListener,s);
		myMaxItemWidth = Double.parseDouble(myResources.getString("ItemPaneMaxWidth"));
	}

	@Override
	protected Node layoutDescriptions(List<DisplayableItemDescription> menuItems) {
		myItemsBox = new HBox();
		myMenuItems = menuItems;
		myItemsBox.setAlignment(Pos.CENTER);
		myItemsBox.setSpacing(Double.parseDouble(myResources.getString("ItemSpacing")));
		myScrollPane = new ScrollPane(myItemsBox);
		myScrollPane.setFitToHeight(true);
		myScrollPane.setFitToWidth(true);
		myScrollPane.setCenterShape(true);
		myScrollPane.getStyleClass().add(myResources.getString("HorizontalScrollPaneStyle"));
		for(DisplayableItemDescription currItem : menuItems){
			Pane itemPane = currItem.getPaneRepresentation();
			itemPane.setMinWidth(myMaxItemWidth);
			myItemsBox.getChildren().add(itemPane);
		}
		return myScrollPane;
	}

	@Override
	protected void displayNewItem(DisplayableItemDescription item) {
		myMenuItems.add(item);
		Pane itemPane = item.getPaneRepresentation();
		itemPane.setMinWidth(myMaxItemWidth);
		myItemsBox.getChildren().add(itemPane);
	}


}
