package game_player_menu;


import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PaneCreator implements DescriptionDisplayCreator {
	
	NodeTranslator myNameTranslator;
	NodeTranslator myDescriptionTranslator;
	NodeTranslator myImageTranslator;
	
	
	public PaneCreator() {
		myNameTranslator = new TextTranslator();
		myDescriptionTranslator = new TextTranslator();
		myImageTranslator = new ImageTranslator();
	}
	
	
	@Override
	public Pane getPaneRepresentation(String name, String description, String imagePath) {
		Pane pane = new VBox();
		pane.getChildren().add(myNameTranslator.createNode(name));
		pane.getChildren().add(myDescriptionTranslator.createNode(description));
		pane.getChildren().add(myImageTranslator.createNode(imagePath));
		return pane;
	}
	
	
}
