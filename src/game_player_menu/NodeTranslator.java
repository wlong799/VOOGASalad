package game_player_menu;

import java.util.ResourceBundle;

import javax.annotation.Resources;

import javafx.scene.Node;

public abstract class NodeTranslator implements INodeTranslator {
	
	public static final String RESOURCE_FOLDER = "game_player_resources/GamePlayMenu";
	protected ResourceBundle myResources = ResourceBundle.getBundle(RESOURCE_FOLDER);
	
	public abstract Node createNode(String text, boolean isSelectable, ISelectable listener);
	
	
	
	
}
