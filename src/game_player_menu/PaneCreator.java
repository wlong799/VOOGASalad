package game_player_menu;


import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import resources.ResourceBundles;

/**
 * @author samuelcurtis
 *This class is responsible for creating a singular pane representation of a name, description, and image
 *so that they can be grouped together when displayed on the screen.
 *
 */
public class PaneCreator implements DescriptionDisplayCreator{
	
	private ResourceBundle myResources = ResourceBundles.languageProperties;
	
	NodeTranslator myNameTranslator;
	NodeTranslator myDescriptionTranslator;
	NodeTranslator myImageTranslator;
	
	
	public PaneCreator() {
		myNameTranslator = new TextTranslator();
		myDescriptionTranslator = new DescriptionTranslator();
		myImageTranslator = new ImageTranslator();
	}
	
	
	@Override
	public Pane getPaneRepresentation(String name, String description, String imagePath, ISelectable listener) {
		VBox pane = new VBox();
		pane.setAlignment(Pos.CENTER);
		pane.getStyleClass().add(myResources.getString("ItemPaneCSSStyle"));
		pane.getChildren().add(myNameTranslator.createNode(name,true, listener));
		pane.getChildren().add(myDescriptionTranslator.createNode(description,false,listener));
		pane.getChildren().add(myImageTranslator.createNode(imagePath,false, listener));
		pane.setMaxHeight(Double.parseDouble(myResources.getString("ItemPaneMaxHeight")));
		pane.setMaxWidth(Double.parseDouble(myResources.getString("ItemPaneMaxWidth")));
		return pane;
	}

}
