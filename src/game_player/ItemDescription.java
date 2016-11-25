package game_player;

import javafx.scene.layout.Pane;

public class ItemDescription  {
	
	private String myName;
	private String myDescription;
	private String myImagePath;
	
	public ItemDescription(String name, String description, String imgPath) {
		myName = name;
		myDescription = description;
		myImagePath = imgPath;
	}
	
	public String getName(){
		return myName;
	}
	
	public String getDescriptionn(){
		return myDescription;
	}
	
	public String getImagePath(){
		return myImagePath;
	}
	
	public void setName(String name){
		myName = name;
	}
	
	public void setDescription(String description){
		myDescription = description;
	}
	
	public void setImagePath(String imgPath){
		myImagePath = imgPath;
	}

}
