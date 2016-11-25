package game_player_menu;

import javax.swing.colorchooser.AbstractColorChooserPanel;

import javafx.scene.layout.Pane;

public interface DescriptionDisplayCreator {
	
	Pane getPaneRepresentation(String name, String description, String imagePath);
}
