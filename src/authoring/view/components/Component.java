package authoring.view.components;

import game_object.GameObjectType;

public class Component {
	
	private GameObjectType myType;
	private String myTitle;
	private String myImagePath;
	
	public Component(GameObjectType type, String title, String imagePath) {
		myType = type;
		myTitle = title;
		myImagePath = imagePath;
	}
	
	public GameObjectType getType() {
		return myType;
	}
	
	public String getTitle() {
		return myTitle;
	}
	
	public String getImagePath() {
		return myImagePath;
	}
	
}
