package game_player_menu;

import javafx.scene.Node;

/**
 * @author samuelcurtis
 *An interface whose purpose is to translate a string of text into 
 *a node representation that can be added to a javaFX scene 
 */
public interface INodeTranslator {
	
	Node createNode(String text, boolean isSelectable, ISelectable listener);
}
