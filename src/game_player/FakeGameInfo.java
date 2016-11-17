package game_player;

/**
 * @author samuelcurtis
 *A fake GameInfo class used for testing the gameplay view. A GameInfo class
 *will eventually contain a name, image, and description for each game.
 * 
 */
public class FakeGameInfo {
	private String myImagePath;
	private String myName;
	private String myDescription;
	
	public FakeGameInfo(String path, String name, String description){
		myImagePath = path;
		myName = name;
		myDescription = description;
	}
	
	public String getImagePath(){
		return myImagePath;
	}
	
	public String getName(){
		return myName;
	}
	
	public String getDescription(){
		return myDescription;
	}

}
