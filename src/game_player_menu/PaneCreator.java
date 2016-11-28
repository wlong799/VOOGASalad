package game_player_menu;


import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sun.font.TrueTypeGlyphMapper;

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
		
		Pane pane = new VBox(15);
		/*pane.setStyle("-fx-padding: 10;" + 
                 "-fx-border-style: solid inside;" + 
                 "-fx-border-width: 2;" +
                 "-fx-border-insets: 5;" + 
                 "-fx-border-radius: 5;" + 
                 "-fx-border-color: blue;");*/
		pane.getChildren().add(myNameTranslator.createNode(name,true, listener));
		pane.getChildren().add(myDescriptionTranslator.createNode(description,true,listener));
		pane.getChildren().add(myImageTranslator.createNode(imagePath,true, listener));
		return pane;
	}

	
	
}
