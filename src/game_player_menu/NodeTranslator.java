package game_player_menu;

import java.util.ResourceBundle;

import javax.annotation.Resources;

import javafx.scene.Node;

/**
 * @author samuelcurtis
 *This is the superclass for translating any string of text into a displayable javafx node. 
 *The one method takes in a string of text that represents the content of the node, this could
 *be a name, label, description, or the path to an image. It also takes in a boolean to say 
 *whether the javafx node that is created should be able to be selected by the user (clicked for example).
 *Finally it takes in a listener whose methods can be called when the object is selected. 
 */
public abstract class NodeTranslator implements INodeTranslator {
	
	public static final String RESOURCE_FOLDER = "game_player_resources/GamePlayMenu";
	protected ResourceBundle myResources = ResourceBundle.getBundle(RESOURCE_FOLDER);
	
	public abstract Node createNode(String text, boolean isSelectable, ISelectable listener);
	
	
	
	
}
