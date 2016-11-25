package game_player_menu;

import javafx.scene.layout.Pane;

public class DisplayableItemDescription {
	
	private ItemDescription myItemDescription;
	private Pane myItemDescriptionDisplay;
	private PaneCreator myPaneCreator;
	
	public DisplayableItemDescription(ItemDescription itemDescription, PaneCreator paneCreator){
		myItemDescription = itemDescription;
		myPaneCreator = paneCreator;
		representItemAsPane();
	}
	
	private void representItemAsPane(){
		myItemDescriptionDisplay = myPaneCreator.getPaneRepresentation(
				myItemDescription.getName(), 
				myItemDescription.getDescriptionn(),
				myItemDescription.getImagePath());
	}
	
	public Pane getPaneRepresentation(){
		return myItemDescriptionDisplay;
	}
}	
