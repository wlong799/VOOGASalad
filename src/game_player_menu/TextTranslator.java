package game_player_menu;

import com.sun.javafx.scene.control.skin.LabeledImpl;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TextTranslator extends NodeTranslator {

	/*public TextTranslator(ISelectable listener) {
		super(listener);
		// TODO Auto-generated constructor stub
	}
	
	
*/
	@Override
	public Node createNode(String text, boolean isSelectable, ISelectable listener) {
		Button menuButton = new Button(text);
		if(isSelectable){
			makeButtonSelectable(menuButton, listener);
		}
		return menuButton;
	}

	private void makeButtonSelectable(Button menuButton, ISelectable listener) {
		menuButton.setOnMouseClicked(e -> listener.select());

	}
}
