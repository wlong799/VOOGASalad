package game_player_menu;


import javafx.geometry.Pos;
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
		menuButton.setAlignment(Pos.TOP_CENTER);
		menuButton.getStyleClass().add(myResources.getString("ButtonStyle"));
		if(isSelectable){
			makeButtonSelectable(menuButton, listener);
		}
		return menuButton;
	}

	private void makeButtonSelectable(Button menuButton, ISelectable listener) {
		menuButton.setOnAction(e -> listener.select());

	}
}
