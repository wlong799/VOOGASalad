package game_player_menu;


import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PaneCreator implements DescriptionDisplayCreator{
	
	
	NodeTranslator myNameTranslator;
	NodeTranslator myDescriptionTranslator;
	NodeTranslator myImageTranslator;
	
	
	public PaneCreator() {
		myNameTranslator = new TextTranslator();
		myDescriptionTranslator = new TextTranslator();
		myImageTranslator = new ImageTranslator();
	}
	
	
	@Override
	public Pane getPaneRepresentation(String name, String description, String imagePath, ISelectable listener) {
		Pane pane = new VBox();
		pane.getStyleClass().add("vbox");
		pane.getChildren().add(myNameTranslator.createNode(name,true, listener));
		pane.getChildren().add(myDescriptionTranslator.createNode(description,true,listener));
		pane.getChildren().add(myImageTranslator.createNode(imagePath,true, listener));
		pane.setMaxHeight(200);
		return pane;
	}

}
