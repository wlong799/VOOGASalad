package game_player_menu;

import javafx.scene.layout.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ListChangeListener.Change;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author samuelcurtis
 *This is an abstract class that will generate the scene for the menu. It is abstract 
 *because there can be many different ways of laying out items on the menu. The decision 
 *of how to layout many different menu items (such as games to choose from) will be made by subclasses.
 *
 */
public abstract class MenuSceneGenerator implements IMenuSceneGenerator{
	public static final String RESOURCE_FOLDER = "game_player_resources/GamePlayMenu";
	private Scene myMenuScene;
	private List<DisplayableItemDescription> myDisplayableMenuItems;
	private ResourceBundle myResources;
	private PaneCreator myPaneCreator;
	private Stage myStage;
	private BorderPane myRoot;
	protected IMenuInputListener myMenuListener;

	public MenuSceneGenerator(IMenuInputListener menu, Stage s){
		myStage = s;
		myDisplayableMenuItems = new ArrayList<DisplayableItemDescription>();
		myResources = ResourceBundle.getBundle(RESOURCE_FOLDER);
		myMenuListener = menu;
		myPaneCreator = new PaneCreator();
	}

	private Scene generateScene(List<DisplayableItemDescription> menuItems){
		myRoot = createBorderPane();
		myMenuScene = new Scene(myRoot);
		setMenuCSS();
		HBox menuTop = createMenuTop();
		myRoot.setTop(menuTop);
		myRoot.setAlignment(menuTop, Pos.CENTER);
		myRoot.setCenter(layoutDescriptions(menuItems));
		return myMenuScene;
	}

	private HBox createMenuTop() {
		HBox menuTop = new HBox();
		Text instructions = new Text();
		Button loadButton = createLoadGameButton();
		menuTop.setSpacing(myRoot.getWidth() - loadButton.getWidth() - instructions.getStrokeWidth());
		instructions.setText(myResources.getString("MenuInstructionText"));
		instructions.setFont(new Font(Double.parseDouble(myResources.getString("MenuInstructionFontSize"))));
        instructions.getStyleClass().add("text");
        BorderPane root = createBorderPane();
        root.setAlignment(instructions, Pos.CENTER);
        root.setTop(instructions);
		setMenuCSS();
		//root.setCenter(loadGameButton());
		instructions.getStyleClass().add("text");
		loadButton.setAlignment(Pos.TOP_RIGHT);
		menuTop.getChildren().add(loadButton);
		menuTop.getChildren().add(instructions);
		return menuTop;
	}

	private Button createLoadGameButton(){
		Button load = new Button("Load A New Game");
		load.setOnAction(e -> openFileChooser());
		return load;
	}


	private void openFileChooser() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(myStage);
		myMenuListener.loadGame(file);
	}

	private BorderPane createBorderPane() {
		BorderPane root = new BorderPane();
		root.setPrefSize(Double.parseDouble(myResources.getString("MenuWidth")),
				Double.parseDouble(myResources.getString("MenuHeight")));
		root.getStyleClass().add(myResources.getString("BorderPaneStyle"));
		return root;
	}

	private void setMenuCSS() {
		File f = new File(myResources.getString("MenuLayoutCSSFile"));
		myMenuScene.getStylesheets().clear();
		myMenuScene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
	}

	protected abstract Node layoutDescriptions(List<DisplayableItemDescription> menuItems);
	
	protected abstract void displayNewItem(DisplayableItemDescription item);

	private void makeItemsDisplayable(List<ItemDescription> menuItems) {
		for(ItemDescription item : menuItems){
			DisplayableItemDescription displayableItem = generateDisplayableDescription(item);
			myDisplayableMenuItems.add(displayableItem);
		}
	}

	private DisplayableItemDescription generateDisplayableDescription(ItemDescription item){
		DisplayableItemDescription displayableItem = new DisplayableItemDescription(item, myMenuListener);
		displayableItem.setPaneRepresentation(myPaneCreator.getPaneRepresentation(item.getName(), 
				item.getDescriptionn(), item.getImagePath(),displayableItem));
		return displayableItem;
	}



	public Scene getMenuScene(List<ItemDescription> menuItems){
		makeItemsDisplayable(menuItems);
		return generateScene(myDisplayableMenuItems);
	}

	public void addItem(Change<ItemDescription> c) {
		System.out.println("Test");
		while(c.next()){
			if (c.wasPermutated()) {
				//do nothing
			} else if (c.wasUpdated()) {
				//do nothing
			} else {
				for (ItemDescription addItem : c.getAddedSubList()) {
					DisplayableItemDescription item = generateDisplayableDescription(addItem);
					displayNewItem(item);
				}
			}
		}
	}


}
