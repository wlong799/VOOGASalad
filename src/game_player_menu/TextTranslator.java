package game_player_menu;

import com.sun.javafx.scene.control.skin.LabeledImpl;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class TextTranslator extends NodeTranslator {

	@Override
	public Node createNode(String text) {
		// TODO Auto-generated method stub
		return new Label(text);
	}
	
	
	
}
