package game_player_menu;

import javafx.scene.layout.Pane;

public class DisplayableItemDescription implements ISelectable{
	
	private ItemDescription myItemDescription;
	private Pane myItemDescriptionDisplay;
	private IMenuInputListener myMenuInputListener;
	
	public DisplayableItemDescription(ItemDescription itemDescription, IMenuInputListener menuListener){
		myItemDescription = itemDescription;
		myMenuInputListener = menuListener;
	}

	
	public Pane getPaneRepresentation(){
		return myItemDescriptionDisplay;
	}
	
	public void setPaneRepresentation(Pane paneRepresentation){
		myItemDescriptionDisplay = paneRepresentation;
	}

	@Override
	public void select() {
		myMenuInputListener.itemChosen(myItemDescription.getName());
	}
}	
