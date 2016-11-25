package game_player_menu;


import javafx.scene.layout.Pane;

public interface DescriptionDisplayCreator {
	
	Pane getPaneRepresentation(String name, String description, String imagePath);
}
